package ru.mesto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для заголовка страницы.
 * Содержит методы для работы с элементом заголовка, включая ожидание его загрузки и получение email пользователя.
 */
public class HeaderPage {
    private final WebDriver driver;
    private final By headerUser = By.className("header__user");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Ожидает загрузки заголовка страницы.
     */
    public void waitForLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(headerUser));
    }

    /**
     * Возвращает email пользователя из заголовка страницы.
     * @return String email пользователя
     */
    public String getEmailFromHeader() {
        return driver.findElement(headerUser).getText();
    }
}