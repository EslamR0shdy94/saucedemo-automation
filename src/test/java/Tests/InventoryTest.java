package Tests;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class InventoryTest extends BaseTest {

    InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for standard_user");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Verify standard_user can add and remove all products correctly")
    public void testStandardUserCartAddRemoveFlow() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Clicking all 'Add to Cart' buttons...");
        List<String> addedProducts = inventoryPage.clickAllAddToCartButtons();
        saveScreenshotWithStep("After clicking Add to Cart buttons");
        System.out.println(" Products added successfully: " + addedProducts.size());

        Allure.step("Clicking all 'Remove' buttons...");
        List<String> removedProducts = inventoryPage.clickAllRemoveButtons();
        saveScreenshotWithStep("After clicking Remove buttons");
        System.out.println(" Products removed successfully: " + removedProducts.size());

        softAssert.assertEquals(addedProducts.size(), removedProducts.size(),
                " Not all added products were removed");
        softAssert.assertAll();
    }
}
