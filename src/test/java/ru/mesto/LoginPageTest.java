package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Юнит-тесты для методов LoginPage.
 * Проверяют функциональность методов LoginPage в изоляции.
 */
public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSetUsername() {
        driver.get("data:text/html;charset=utf-8,<html><body><input id='email'></body></html>");

        loginPage.setUsername("test@example.com");
        String value = driver.findElement(org.openqa.selenium.By.id("email")).getAttribute("value");
        assertEquals("test@example.com", value);
    }

    @Test
    public void testSetPassword() {
        driver.get("data:text/html;charset=utf-8,<html><body><input id='password'></body></html>");

        loginPage.setPassword("password123");
        String value = driver.findElement(org.openqa.selenium.By.id("password")).getAttribute("value");
        assertEquals("password123", value);
    }

    @Test
    public void testClickSignInButton() {
        driver.get("data:text/html;charset=utf-8,<html><body><button class='auth-form__button'>Sign In</button></body></html>");
        // Этот вызов не должен вызывать исключение
        loginPage.clickSignInButton();
    }

    @Test
    public void testLogin() {
        driver.get("data:text/html;charset=utf-8,<html><body><input id='email'><input id='password'><button class='auth-form__button'>Sign In</button></body></html>");

        loginPage.login("test@example.com", "password123");

        String emailValue = driver.findElement(org.openqa.selenium.By.id("email")).getAttribute("value");
        String passwordValue = driver.findElement(org.openqa.selenium.By.id("password")).getAttribute("value");

        assertEquals("test@example.com", emailValue);
        assertEquals("password123", passwordValue);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}