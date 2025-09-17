package ru.mesto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object для страницы авторизации.
 * Содержит методы для ввода email, пароля, нажатия кнопки входа и комплексный метод авторизации.
 */
public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By signInButton = By.className("auth-form__button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Вводит email в поле ввода.
     * @param username email пользователя
     */
    public void setUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
    }

    /**
     * Вводит пароль в поле ввода.
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Нажимает кнопку входа.
     */
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    /**
     * Выполняет авторизацию пользователя.
     * @param username email пользователя
     * @param password пароль пользователя
     */
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickSignInButton();
    }
}