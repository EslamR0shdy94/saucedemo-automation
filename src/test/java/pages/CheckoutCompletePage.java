package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    // Locator
    private final By thankYouMessage = By.className("complete-header");

    // Assertion helper
    public String getThankYouText() {
        return find(thankYouMessage).getText();
    }
    public boolean isThankYouMessageDisplayed() {
         List<WebElement> elements = driver.findElements(By.cssSelector(".complete-header"));
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

}
