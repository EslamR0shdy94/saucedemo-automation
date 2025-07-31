package Tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class PerformanceUserTest extends BaseTest {

    @Test(description = "Performance test for performance_glitch_user")
    public void testPerformanceGlitchUserLoginTime() {
        LoginPage loginPage = new LoginPage(driver);

        long start = System.currentTimeMillis();
        loginPage.login("performance_glitch_user", "secret_sauce");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("inventory"));

        long end = System.currentTimeMillis();
        long duration = end - start;

        saveScreenshotWithStep(" Login finished for performance_glitch_user - duration: " + duration + "ms");

        assertTrue(duration < 5000, "Login took too long: " + duration + " ms");
    }
}
