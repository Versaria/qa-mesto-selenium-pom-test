package ru.mesto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для страницы редактирования профиля.
 * Содержит методы для установки новой активности и сохранения изменений.
 */
public class ProfilePage {
    private final WebDriver driver;
    private final By activity = By.id("owner-description");
    private final By save = By.className("popup__button");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Устанавливает новое значение активности пользователя.
     * Ожидает доступности поля, очищает его и вводит новое значение.
     * @param newActivity новое значение активности
     */
    public void setNewActivity(String newActivity) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(activity));
        driver.findElement(activity).clear();
        driver.findElement(activity).sendKeys(newActivity);
    }

    /**
     * Нажимает кнопку сохранения изменений профиля.
     * Ожидает доступности кнопки, нажимает ее и ожидает исчезновения (в продакшн среде).
     */
    public void clickSaveButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(save));
        driver.findElement(save).click();
        // В тестовой среде ожидание исчезновения отключено
        if (!System.getProperty("test.env", "production").equals("test")) {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(save));
        }
    }
}