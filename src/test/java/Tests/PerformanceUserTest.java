package Tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class PerformanceUserTest extends BaseTest {

    @Test(description = "Performance test for performance_glitch_user")
    public void testPerformanceGlitchUserLoginTime() {
        LoginPage loginPage = new LoginPage(driver);

        Allure.step(" Measuring login time for performance_glitch_user...");
        long loginStartTime = System.currentTimeMillis();

        loginPage.login("performance_glitch_user", "secret_sauce");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("inventory"));

        long loginEndTime = System.currentTimeMillis();
        long loginDuration = loginEndTime - loginStartTime;

        saveScreenshotWithStep("📸 After login - duration: " + loginDuration + "ms");

        assertTrue(loginDuration < 5000,
                " Login took too long: " + loginDuration + " ms");

        Allure.step(" Login completed in " + loginDuration + " ms");

        // Additional performance step: Measure inventory page readiness (e.g., wait for product list to be visible)
        InventoryPage inventoryPage = new InventoryPage(driver);

        long inventoryLoadStart = System.currentTimeMillis();
        inventoryPage.waitForPageToLoad(); // You need to implement this in InventoryPage if not already
        long inventoryLoadEnd = System.currentTimeMillis();

        long inventoryLoadDuration = inventoryLoadEnd - inventoryLoadStart;
        saveScreenshotWithStep("📸 Inventory page fully loaded - duration: " + inventoryLoadDuration + "ms");

        Allure.step(" Inventory page loaded in " + inventoryLoadDuration + " ms");

        assertTrue(inventoryLoadDuration < 3000,
                " Inventory page load time too long: " + inventoryLoadDuration + " ms");
    }
}
