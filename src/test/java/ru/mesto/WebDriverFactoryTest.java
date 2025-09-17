package ru.mesto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

/**
 * Юнит-тесты для WebDriverFactory.
 * Проверяют создание корректных экземпляров WebDriver в зависимости от системных свойств.
 */
public class WebDriverFactoryTest {
    private String originalBrowserProperty;

    @Before
    public void saveOriginalProperty() {
        originalBrowserProperty = System.getProperty("browser");
    }

    @After
    public void restoreOriginalProperty() {
        if (originalBrowserProperty != null) {
            System.setProperty("browser", originalBrowserProperty);
        } else {
            System.clearProperty("browser");
        }
    }

    @Test
    public void testGetChromeDriver() {
        System.setProperty("browser", "chrome");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("Должен вернуться ChromeDriver", driver instanceof ChromeDriver);
        driver.quit();
    }

    @Test
    public void testGetFirefoxDriver() {
        System.setProperty("browser", "firefox");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("Должен вернуться FirefoxDriver", driver instanceof FirefoxDriver);
        driver.quit();
    }

    @Test
    public void testGetDefaultDriver() {
        System.clearProperty("browser");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("По умолчанию должен вернуться ChromeDriver", driver instanceof ChromeDriver);
        driver.quit();
    }

    @Test
    public void testGetUnknownBrowserUsesChrome() {
        System.setProperty("browser", "safari");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("Для неизвестного браузера должен вернуться ChromeDriver", driver instanceof ChromeDriver);
        driver.quit();
    }

    @Test
    public void testGetEmptyBrowserUsesChrome() {
        System.setProperty("browser", "");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("Для пустого браузера должен вернуться ChromeDriver", driver instanceof ChromeDriver);
        driver.quit();
    }

    @Test
    public void testGetBrowserWithSpacesUsesChrome() {
        System.setProperty("browser", "  chrome  ");
        WebDriver driver = WebDriverFactory.getDriver();
        assertTrue("Для браузера с пробелами должен вернуться ChromeDriver", driver instanceof ChromeDriver);
        driver.quit();
    }
}