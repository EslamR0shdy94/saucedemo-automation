package Tests;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ErrorUserCartTest extends BaseTest {

    InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAsErrorUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for error_user");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Test Add and Remove functionality for fixed locators")
    public void testCartAddRemoveFlow() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Clicking all Add to Cart buttons...");
        List<String> added = inventoryPage.clickAllAddToCartButtons();
        List<String> removed = inventoryPage.clickAllRemoveButtons();

        saveScreenshotWithStep("After clicking Add to Cart buttons");
        System.out.println("✅ Products added successfully: " + added.size());
        Allure.step("Clicking all Remove buttons...");
        saveScreenshotWithStep("After clicking Remove buttons");
        System.out.println("✅ Products removed successfully: " + removed.size());

        softAssert.assertEquals(removed.size(), added.size(), "❌ Not all products were added.");
        softAssert.assertEquals(removed.size(), added.size(), "❌ Not all added products were removed.");
        softAssert.assertAll();
    }

}
