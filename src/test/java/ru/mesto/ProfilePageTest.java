package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Юнит-тесты для методов ProfilePage.
 * Проверяют функциональность методов ProfilePage в изоляции.
 */
public class ProfilePageTest {
    private WebDriver driver;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        // Устанавливаем свойство для тестовой среды
        System.setProperty("test.env", "test");
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void testSetNewActivity() {
        driver.get("data:text/html;charset=utf-8,<html><body><input id='owner-description' value='Old Activity'></body></html>");

        profilePage.setNewActivity("New Activity");
        String newValue = driver.findElement(org.openqa.selenium.By.id("owner-description")).getAttribute("value");
        assertEquals("New Activity", newValue);
    }

    @Test
    public void testClickSaveButton() {
        driver.get("data:text/html;charset=utf-8,<html><body><button class='popup__button'>Save</button></body></html>");
        // Этот вызов не должен вызывать исключение
        profilePage.clickSaveButton();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        // Очищаем свойство после теста
        System.clearProperty("test.env");
    }
}