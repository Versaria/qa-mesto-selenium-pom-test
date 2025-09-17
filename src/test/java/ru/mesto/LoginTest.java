package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Интеграционный тест для проверки авторизации пользователя.
 * Проверяет, что после авторизации email пользователя отображается в заголовке страницы.
 */
public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get("https://qa-mesto.praktikum-services.ru/");
    }

    @Test
    public void checkEmailInHeaderAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String email = "versaria.o@yandex.ru";
        String password = "qaswef-ketped-3motKo";
        loginPage.login(email, password);

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitForLoadHeader();

        String actualEmail = headerPage.getEmailFromHeader();
        assertThat(actualEmail, is(email));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}