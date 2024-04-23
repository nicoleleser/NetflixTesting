package com.netflix.netflixtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            //System.setProperty("webdriver.gecko.driver", "C:\\Users\\Tara\\Downloads\\geckodriver-v0.34.0-win64");
            System.setProperty("webdriver.gecko.driver", "/Users/nikki/Downloads/geckodriver");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
