package com.netflix.netflixtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Authorization {
    private WebDriver driver;
    private String netflixHomeUrl = "https://www.netflix.com";
    private String netflixUsername;
    private String netflixPassword;

    private final By USERNAME_FIELD = By.cssSelector("input[name='userLoginId']");
    private final By PASSWORD_FIELD = By.cssSelector("input[name='password']");
    private final By SUBMIT_BUTTON = By.cssSelector("button.e1ax5wel1:nth-child(3)");
    private final By PROFILE_ICON = By.cssSelector("li.profile:nth-child(5) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
    private final By SIGN_IN_BUTTON = By.id("signIn");

    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("config.properties not found in classpath");
            }
            prop.load(input);
            netflixUsername = prop.getProperty("netflixUsername");
            netflixPassword = prop.getProperty("netflixPassword");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void testSignIn() {
        driver.get(netflixHomeUrl);

        WebElement signInButton = driver.findElement(SIGN_IN_BUTTON);
        signInButton.click();

        WebElement emailInput = driver.findElement(USERNAME_FIELD);
        emailInput.sendKeys(netflixUsername);

        WebElement passwordInput = driver.findElement(PASSWORD_FIELD);
        passwordInput.sendKeys(netflixPassword);

        WebElement submitButton = driver.findElement(SUBMIT_BUTTON);
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_ICON));

        WebElement profileIcon = driver.findElement(PROFILE_ICON);
        profileIcon.click();
    }
}
