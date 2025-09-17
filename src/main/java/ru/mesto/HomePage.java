package ru.mesto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для главной страницы.
 * Содержит методы для ожидания загрузки данных профиля, нажатия кнопки редактирования профиля,
 * ожидания изменения активности и получения текста активности.
 */
public class HomePage {
    private final WebDriver driver;
    private final By profileTitle = By.className("profile__title");
    private final By editProfileButton = By.className("profile__edit-button");
    private final By activity = By.className("profile__description");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Ожидает загрузки данных профиля на главной странице.
     * Ожидает видимость элемента с заголовком профиля и наличие непустого текста в нем.
     */
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTitle));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.textToBe(profileTitle, "")));
    }

    /**
     * Нажимает кнопку редактирования профиля.
     */
    public void clickEditProfileButton() {
        driver.findElement(editProfileButton).click();
    }

    /**
     * Ожидает изменения активности пользователя на указанное значение.
     * @param changed ожидаемое значение активности
     */
    public void waitForChangedActivity(String changed) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(activity, changed));
    }

    /**
     * Возвращает текущее значение активности пользователя.
     * @return String активность пользователя
     */
    public String getActivity() {
        return driver.findElement(activity).getText();
    }
}