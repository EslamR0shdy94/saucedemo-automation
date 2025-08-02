package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.*;

public class ErrorUserCheckOutFlowTest extends BaseTest {

    @BeforeMethod
    public void loginAndAddToCart() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");

        // Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickAllAddToCartButtons();
        saveScreenshotWithStep("Added product to cart");

        inventoryPage.clickCartIcon();
        assertTrue(driver.getCurrentUrl().contains("cart"), "Did not navigate to cart");
        saveScreenshotWithStep("Cart page and product on it");
    }

    @Test(description = "Complete checkout flow with error user")
    public void testCompleteCheckoutFlow() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.fillCustomerInfo("Eslam", "Roshdy", "12345");
        saveScreenshotWithStep("before click continue when all Checkout info filled");

        // Bug check: verify if last name field is actually filled
        String lastNameValue = infoPage.getLastNameFieldValue();
        if (lastNameValue == null || lastNameValue.trim().isEmpty()) {
            saveScreenshotWithStep("BUG: Last name input is empty after trying to fill it");
            System.out.println(" BUG: Last name field could not be filled.");
        } else {
            saveScreenshotWithStep("Last name was filled with: " + lastNameValue);
        }

        infoPage.clickContinue();
        saveScreenshotWithStep("after all Checkout info filled");

        // Expecting a bug: even if last name is empty, it proceeds
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Not on overview page");
        saveScreenshotWithStep("Checkout overview");

        overviewPage.clickFinish();
        saveScreenshotWithStep("after click Finish");

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

        //  Bug check: finish doesn't work, so thank you message shouldn't appear
        boolean isOrderCompleted = completePage.isThankYouMessageDisplayed();

        if (isOrderCompleted) {
            saveScreenshotWithStep(" BUG: Order completed when it shouldn't have been");
            String confirmation = completePage.getThankYouText();
            assertEquals(confirmation, "Thank you for your order!", "Unexpected confirmation message");
        } else {
            saveScreenshotWithStep(" Expected behavior: Order not completed due to Finish button issue");
            assertFalse(isOrderCompleted, "BUG: Thank you message is displayed even though order shouldn't complete");
        }
    }

}
