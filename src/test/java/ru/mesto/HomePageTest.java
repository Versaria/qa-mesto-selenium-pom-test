package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Юнит-тесты для методов HomePage.
 * Проверяют функциональность методов HomePage в изоляции.
 */
public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
    }

    @Test
    public void testWaitForLoadProfileDataWithEmptyThenFilledText() {
        driver.get("data:text/html;charset=utf-8,<html><body>" +
                "<div class='profile__title'></div>" +
                "<script>" +
                "setTimeout(function() {" +
                "  document.querySelector('.profile__title').textContent = 'Test User';" +
                "}, 100);" +
                "</script>" +
                "</body></html>");

        homePage.waitForLoadProfileData();
        // Если метод не выбросил исключение, тест пройден
    }

    @Test
    public void testWaitForLoadProfileDataWithImmediateText() {
        driver.get("data:text/html;charset=utf-8,<html><body>" +
                "<div class='profile__title'>Immediate Text</div>" +
                "</body></html>");

        homePage.waitForLoadProfileData();
        // Если метод не выбросил исключение, тест пройден
    }

    @Test
    public void testGetActivity() {
        driver.get("data:text/html;charset=utf-8,<html><body><div class='profile__description'>Test Activity</div></body></html>");

        String activity = homePage.getActivity();
        assertEquals("Test Activity", activity);
    }

    @Test
    public void testGetActivityWithEmptyText() {
        driver.get("data:text/html;charset=utf-8,<html><body><div class='profile__description'></div></body></html>");

        String activity = homePage.getActivity();
        assertEquals("", activity);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}