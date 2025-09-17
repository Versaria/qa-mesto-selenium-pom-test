package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Фабрика для создания экземпляров WebDriver.
 * В зависимости от системного свойства "browser" создает экземпляр ChromeDriver или FirefoxDriver.
 * По умолчанию используется ChromeDriver.
 */
public class WebDriverFactory {
    private WebDriverFactory() {
        // utility class
    }

    /**
     * Создает и возвращает экземпляр WebDriver.
     * @return WebDriver экземпляр WebDriver
     */
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase();

        if ("firefox".equals(browser)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            return new FirefoxDriver(firefoxOptions);
        } else {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            return new ChromeDriver(chromeOptions);
        }
    }
}