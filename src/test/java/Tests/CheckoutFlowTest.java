package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.*;

public class CheckoutFlowTest extends BaseTest {

    @BeforeMethod
    public void loginAndAddToCart() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");

        // Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickAllAddToCartButtons();
        saveScreenshotWithStep("Added product to cart");

        inventoryPage.clickCartIcon();
        assertTrue(driver.getCurrentUrl().contains("cart"), "Did not navigate to cart");
        saveScreenshotWithStep("Cart page and product on it");
    }

    @Test(description = "Validate checkout info with various invalid inputs")
    public void testCheckoutInfoValidation() {
        CartPage cartPage = new CartPage(driver);
        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);

        cartPage.clickCheckoutButton();
        assertTrue(infoPage.isAtCheckoutInfoPage(), "Did not navigate to Shipping info Page");
        saveScreenshotWithStep("Shipping info Page");

        // Case 1: Leave all fields empty
        infoPage.fillCustomerInfo("", "", "");
        infoPage.clickContinue();

        saveScreenshotWithStep("All fields empty");
        assertTrue(infoPage.isErrorDisplayed("Error: First Name is required"));
        ensureOnCheckoutInfoPage();

        // Case 2: Only first name filled
        infoPage.fillCustomerInfo("Eslam", "", "");
        infoPage.clickContinue();

        saveScreenshotWithStep("Only first name filled");
        assertTrue(infoPage.isErrorDisplayed("Error: Last Name is required"));
        ensureOnCheckoutInfoPage();

        // Case 3: First and last name filled, postal empty
        infoPage.fillCustomerInfo("Eslam", "Roshdy", "");
        infoPage.clickContinue();

        saveScreenshotWithStep("Postal Code empty");
        assertTrue(infoPage.isErrorDisplayed("Error: Postal Code is required"));
        ensureOnCheckoutInfoPage();

        // Case 4: Numbers in name fields
        infoPage.fillCustomerInfo("123", "456", "78910");
        infoPage.clickContinue();

        saveScreenshotWithStep("Numbers in name fields");
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Did not proceed with numeric names");

        // Return to check out info page
        ensureOnCheckoutInfoPage();

        // Case 5: First Name is empty
        infoPage.fillCustomerInfo("", "roshdy", "123");
        infoPage.clickContinue();

        saveScreenshotWithStep("First Name is empty");
        assertTrue(infoPage.isErrorDisplayed("Error: First Name is required"));
        ensureOnCheckoutInfoPage();

        // Case 6: Valid input for reference
        infoPage.fillCustomerInfo("Ali", "Hassan", "11223");
        infoPage.clickContinue();

        saveScreenshotWithStep("Valid input");
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Valid input did not proceed");
    }

    @Test(description = "Complete checkout flow with standard user")
    public void testCompleteCheckoutFlow() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.fillCustomerInfo("Eslam", "Roshdy", "12345");
        infoPage.clickContinue();

        saveScreenshotWithStep("Checkout info filled");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Not on overview page");
        saveScreenshotWithStep("Checkout overview");

        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        assertTrue(completePage.isThankYouMessageDisplayed(), "Order not completed");
        saveScreenshotWithStep("Order complete");

        String confirmation = completePage.getThankYouText();
        assertEquals(confirmation, "Thank you for your order!", "Unexpected confirmation message");
    }

    private void ensureOnCheckoutInfoPage() {
        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        if (!infoPage.isAtCheckoutInfoPage()) {
            driver.navigate().back();
        }

        assertTrue(infoPage.isAtCheckoutInfoPage(), "Failed to return to checkout info page");
    }
}
