package com.netflix.netflixtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ShowSelection {
    private WebDriver driver;
    private String netflixBrowseUrl = "https://www.netflix.com/browse";;
    private String netflixMyListUrl = "https://www.netflix.com/browse/my-list";;

    private String firstTrendingShowSelector = "#row-1 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)";
    private String wishlistButtonSelector = "div.ltr-bjn8wh:nth-child(2) > div:nth-child(1) > button:nth-child(1) > div:nth-child(1) > div:nth-child(1) > svg:nth-child(1)";
    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testChooseShows() {
        // Click on the first trending show
        WebElement trendingShow = driver.findElement(By.cssSelector(firstTrendingShowSelector));
        trendingShow.click();
        String pageTitle = driver.getTitle();
        // gets the details
        WebElement synopsisElement = driver.findElement(By.cssSelector("p.preview-modal-synopsis[data-uia='preview-modal-synopsis'] > div.ptrack-content"));
        String itemDetails = synopsisElement.getText();

        // Print the details to the console
        System.out.println("Title: " + pageTitle);
        System.out.println("Item Details:");
        System.out.println(itemDetails);
    }

    @Test
    public void testPlayShows() {
        // Click on the first trending show
        driver.get(netflixBrowseUrl);
        WebElement trendingShow = driver.findElement(By.cssSelector(firstTrendingShowSelector));
        trendingShow.click();
        String pageTitle = driver.getTitle();
        // gets the details
        WebElement playButton = driver.findElement(By.cssSelector(".primary-button > button:nth-child(1) > span:nth-child(3)"));
        playButton.click();
        String newPageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, newPageTitle, "Page titles before and after clicking the most recently added item are different.");
    }

    @Test
    public void testAddToWishlistShows() throws InterruptedException {
        driver.get(netflixBrowseUrl);
        Thread.sleep(3000);

        WebElement trendingShow = driver.findElement(By.cssSelector(firstTrendingShowSelector));
        trendingShow.click();

        String pageTitle = driver.getTitle();
        System.out.println("\nTitle to add to wishlist: " + pageTitle);

        // Click on the wishlist button
        WebElement wishlistButton = driver.findElement(By.cssSelector(wishlistButtonSelector));
        wishlistButton.click();

        driver.get(netflixMyListUrl);
        Thread.sleep(3000);

        WebElement mostRecentlyAdded = driver.findElement(By.cssSelector("#row-0 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
        mostRecentlyAdded.click();
        Thread.sleep(5000);

        String newPageTitle = driver.getTitle();

        System.out.println("\nTitle new page: " + newPageTitle);
        Assert.assertEquals(pageTitle, newPageTitle, "Page titles before and after clicking the most recently added item are different.");
    }

    @Test
    public void testRemoveFromWishlistShows() throws InterruptedException {

        driver.get(netflixMyListUrl);

        WebElement mostRecentlyAdded = driver.findElement(By.cssSelector("#row-0 > div > div > div > div > div > div.slider-item.slider-item-0"));
        mostRecentlyAdded.click();
        Thread.sleep(3000);

        String showToRemove = driver.getTitle();
        System.out.println("\nRemove: " + showToRemove);
        // Click on the remove from wishlist button
        WebElement removeFromWishlistButton = driver.findElement(By.cssSelector(wishlistButtonSelector));
        removeFromWishlistButton.click();
        driver.get(netflixMyListUrl);
        Thread.sleep(3000);
        WebElement newMostRecentlyAdded = driver.findElement(By.cssSelector("#row-0 > div > div > div > div > div > div.slider-item.slider-item-0"));
        newMostRecentlyAdded.click();

        String newShowToCompare = driver.getTitle();
        Assert.assertNotEquals(showToRemove, newShowToCompare, "Page titles before and after removing the most recently added item are the same.");

    }


    @AfterClass
    public void tearDown() {
        // No need to quit the driver here, it will be handled globally
    }
}
