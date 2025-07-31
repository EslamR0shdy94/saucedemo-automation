package Tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // ⛔ هنا كان لازم options تجهز قبل ما نستخدمها في new ChromeDriver
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        // options.addArguments("--headless=new"); //  شغّله فقط لو محتاج لو شغال على سيرفر

        driver = new ChromeDriver(options); // ✅ بعد ضبط الخيارات
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        // ✅ تأكد إن العنصر جاهز قبل أي حاجة (اختياري but best practice)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshotWithStep(" Test Failed Screenshot");
            attachTextLog("Failure Reason: " + result.getThrowable());
        } else {
            saveScreenshotWithStep(" Test Passed Screenshot");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public void saveScreenshotWithStep(String stepName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(stepName, new ByteArrayInputStream(screenshot));
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String attachTextLog(String message) {
        return message;
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
