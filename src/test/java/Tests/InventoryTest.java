package Tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class InventoryTest extends BaseTest {

    InventoryPage inventoryPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void loginAsErrorUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for error_user");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Test Add and Remove functionality for fixed locators")
    public void testCartAddRemoveFlow() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        SoftAssert softAssert = new SoftAssert();
        List<String> added;
        List<String> removed;
        added = inventoryPage.clickAllAddToCartButtons();
        Allure.step("Clicking all Add to Cart buttons...");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        saveScreenshotWithStep("After clicking Add to Cart buttons");
        System.out.println("✅ Products added successfully: " + added.size());

        removed = inventoryPage.clickAllRemoveButtons();
        Allure.step("Clicking all Remove buttons...");
        saveScreenshotWithStep("After clicking Remove buttons");
        System.out.println("✅ Products removed successfully: " + removed.size());

        softAssert.assertEquals(removed.size(), added.size(), "all products were added.");
        softAssert.assertEquals(removed.size(), added.size(), "all added products were removed.");
        softAssert.assertAll();
    }

}
