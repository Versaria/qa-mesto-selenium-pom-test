package ru.mesto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

/**
 * Тесты крайних случаев для HomePage.
 * Проверяют поведение методов при таймаутах и отсутствии ожидаемых элементов.
 */
public class HomePageEdgeCaseTest {
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
    public void testWaitForLoadProfileDataWithEmptyText() {
        driver.get("data:text/html;charset=utf-8,<html><body><div class='profile__title'></div></body></html>");

        try {
            homePage.waitForLoadProfileData();
            fail("Expected timeout exception");
        } catch (Exception e) {
            // Ожидаемое исключение из-за пустого текста
        }
    }

    @Test
    public void testWaitForChangedActivityWithMissingElement() {
        driver.get("data:text/html;charset=utf-8,<html><body></body></html>");

        try {
            homePage.waitForChangedActivity("Test");
            fail("Expected timeout exception");
        } catch (Exception e) {
            // Ожидаемое исключение из-за отсутствующего элемента
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}