package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Интеграционный тест для проверки редактирования профиля пользователя.
 * Проверяет изменение активности пользователя после редактирования.
 */
public class EditProfileTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get("https://qa-mesto.praktikum-services.ru/");
    }

    @Test
    public void checkActivityAfterEdit() {
        // Логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("versaria.o@yandex.ru", "qaswef-ketped-3motKo");
        // Редактируем профиль
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadProfileData();
        homePage.clickEditProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        String newActivity = "Тестировщик";
        profilePage.setNewActivity(newActivity);
        profilePage.clickSaveButton();
        // Проверяем изменение
        homePage.waitForChangedActivity(newActivity);
        String actualActivity = homePage.getActivity();
        assertEquals(newActivity, actualActivity);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}