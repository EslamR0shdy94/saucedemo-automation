
package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void loginAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");

    }

    @Test(description = "Logout from Inventory page")
    public void testLogoutFromInventory() {
        driver.get("https://www.saucedemo.com/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openMenu();
        inventoryPage.clickLogout();
        saveScreenshotWithStep("Logout from inventory");
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/"), "Logout from inventory failed");
    }

    @Test(description = "Logout from Cart page")
    public void testLogoutFromCart() {
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/cart.html");
        cartPage.openMenu();
        cartPage.clickLogout();
        saveScreenshotWithStep("Logout from Cart");
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/"), "Logout from cart failed");
    }

    @Test(description = "Logout from Checkout Info page")
    public void testLogoutFromCheckoutInfo() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.openMenu();
        infoPage.clickLogout();
        saveScreenshotWithStep("Logout from Check Out Info");
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/"), "Logout from checkout info failed");
    }

    @Test(description = "Logout from Checkout Overview page")
    public void testLogoutFromCheckoutOverview() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.openMenu();
        overviewPage.clickLogout();
        saveScreenshotWithStep("Logout from Check Out Overview");
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/"), "Logout from checkout overview failed");
    }

    @Test(description = "Logout from Checkout Complete page")
    public void testLogoutFromCheckoutComplete() {
        driver.get("https://www.saucedemo.com/checkout-complete.html");
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        completePage.openMenu();
        completePage.clickLogout();
        saveScreenshotWithStep("Logout from Check Out complete");
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/"), "Logout from checkout complete failed");
    }
}

