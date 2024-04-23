package com.netflix.netflixtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BrowseByLanguage {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void browse_by_languages() throws InterruptedException {
        //test page navigation
        driver.get("https://www.netflix.com/browse");
        Thread.sleep(3000);
        WebElement browse_by_languages_button = driver.findElement(By.cssSelector("li.navigation-tab:nth-child(7) > a:nth-child(1)"));
        browse_by_languages_button.click();
        Thread.sleep(3000);
        WebElement browse_by_languages = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(browse_by_languages.getText(), "Browse by Languages", "The page title did not match expected.");
        Thread.sleep(3000);
    }

    @Test
    public void subtitles_filter() throws InterruptedException {
        //test subtitles filter
        driver.get("https://www.netflix.com/browse/original-audio");
        Thread.sleep(3000);
        WebElement select_original_language = driver.findElement(By.cssSelector(".categoryDropDown > div:nth-child(1) > div:nth-child(1)"));
        select_original_language.click();
        Thread.sleep(3000);
        WebElement select_subtitles = driver.findElement(By.cssSelector("li.sub-menu-item:nth-child(3) > a:nth-child(1)"));
        select_subtitles.click();
        Thread.sleep(3000);
        WebElement firstresult = driver.findElement(By.cssSelector("#row-0 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
        firstresult.click();
        Thread.sleep(3000);
        WebElement subtitles_info = driver.findElement(By.cssSelector(".ltr-x1hvkl > div:nth-child(2) > svg:nth-child(1) > path:nth-child(1)"));
        subtitles_info.click();
        Assert.assertNotNull(subtitles_info, "Test failed: Subtitles button is not available.");
        Thread.sleep(3000);
    }

    @Test
    public void language_filter() throws InterruptedException {
        //test language selector
        driver.get("https://www.netflix.com/browse/original-audio");
        Thread.sleep(3000);
        WebElement select_english_dropdown = driver.findElement(By.cssSelector(".languageDropDown > div:nth-child(1) > div:nth-child(1)"));
        select_english_dropdown.click();
        Thread.sleep(3000);
        WebElement select_spanish = driver.findElement(By.cssSelector("li.sub-menu-item:nth-child(24) > a:nth-child(1)"));
        select_spanish.click();
        Thread.sleep(3000);
        WebElement firstvideo = driver.findElement(By.cssSelector("#row-0 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
        firstvideo.click();
        Thread.sleep(3000);
        WebElement element_genre = driver.findElement(By.cssSelector(".previewModal--detailsMetadata-right > div:nth-child(2)"));
        String info = element_genre.getText();
        String[] parts = info.split(",");
        String firstItem = parts[0].trim();
        Assert.assertEquals(firstItem, "Genres: Spanish", "The language did not match expected.");

    }
}
