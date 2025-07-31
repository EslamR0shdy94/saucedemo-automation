package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
//    public void loginAndWaitForInventory(String username, String password) {
//        long startTime = System.currentTimeMillis();
//
//        enterUsername(username);
//        enterPassword(password);
//        clickLogin();
//
//        // Explicit Wait Inventory
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.urlContains("inventory"));
//
//        long endTime = System.currentTimeMillis();
//        long loadTime = endTime - startTime;
//
//        System.out.println("Login load time: " + loadTime + " ms");
//    }

}
