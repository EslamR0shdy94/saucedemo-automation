package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider(name = "users")
    public Object[][] userData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"problem_user", "secret_sauce", true},
                {"visual_user", "secret_sauce", true}
        };
    }

    @Test(dataProvider = "users", description = "Test login for multiple users")
    public void testLoginForAllUsers(String username, String password, boolean shouldLoginSucceed) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (shouldLoginSucceed) {
            assertTrue(driver.getCurrentUrl().contains("inventory"),
                    "Expected successful login for user: " + username);

            saveScreenshotWithStep(" Login successful for user: " + username);

            // Special check for 'problem_user'
            if (username.equals("problem_user")) {
                InventoryPage inventoryPage = new InventoryPage(driver);
                boolean imagesCorrect = inventoryPage.doAllProductsHaveCorrectImages();
                saveScreenshotWithStep(" Verifying product images for problem_user");

                assertTrue(imagesCorrect, "Product images do not match expected images for 'problem_user'");
            }

        } else {
            String error = loginPage.getErrorMessage();
            saveScreenshotWithStep(" Error message shown for user: " + username);
            assertTrue(error.toLowerCase().contains("locked out"),
                    "Expected error message for user: " + username);
        }
    }
}
