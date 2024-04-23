package com.netflix.netflixtesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

public class OptionSelection {
    private WebDriver driver;
    //private String firstTrendingShowSelector = "#row-1 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)";

    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TVshowTest() throws InterruptedException {
        driver.get("https://www.netflix.com/browse");
        WebElement tv_shows_button = driver.findElement(By.cssSelector("a[href=\"/browse/genre/83\"]"));
        tv_shows_button.click();
        Thread.sleep(3000);
        WebElement genres_dropdown = driver.findElement(By.cssSelector("div[label=\"Genres\"]"));
        genres_dropdown.click();
        Thread.sleep(3000);
        WebElement drama_genre = driver.findElement(By.cssSelector("a[href=\"/browse/genre/11714?bc=83\"]"));
        drama_genre.click();
        Thread.sleep(3000);
        String genreTitle = driver.getTitle();
        Assert.assertEquals(genreTitle, "TV Dramas - Netflix", "The genre title did not match expected.");
        Thread.sleep(3000);
    }

    @Test
    public void MoviesTest() throws InterruptedException {
        driver.get("https://www.netflix.com/browse");
        Thread.sleep(3000);
        WebElement movies_button = driver.findElement(By.cssSelector("li.navigation-tab:nth-child(4) > a:nth-child(1)"));
        movies_button.click();
        Thread.sleep(3000);
        WebElement genres_dropdown = driver.findElement(By.cssSelector("div[label=\"Genres\"]"));
        genres_dropdown.click();
        Thread.sleep(3000);
        WebElement drama_genre = driver.findElement(By.cssSelector("ul.sub-menu-list:nth-child(1) > li:nth-child(8) > a:nth-child(1)"));
        drama_genre.click();
        Thread.sleep(3000);
        WebElement genreTitle = driver.findElement(By.cssSelector("span.genreTitle"));
        Assert.assertEquals(genreTitle.getText(), "Drama Movies", "The genre title did not match expected.");
        Thread.sleep(3000);
    }

    @Test
    public void New_and_popularTest() throws InterruptedException{
        driver.get("https://www.netflix.com/browse");
        Thread.sleep(3000);
        WebElement new_and_popular_button = driver.findElement(By.cssSelector("li.navigation-tab:nth-child(5) > a:nth-child(1)"));
        new_and_popular_button.click();
        Thread.sleep(3000);
        String drama_genre = driver.getCurrentUrl();
        Assert.assertEquals(drama_genre, "https://www.netflix.com/latest", "The url did not match expected.");
        Thread.sleep(3000);
    }

}
