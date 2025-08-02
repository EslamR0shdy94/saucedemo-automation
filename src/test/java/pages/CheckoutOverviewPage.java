package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By finishButton = By.id("finish");
    private final By totalLabel = By.className("summary_total_label");

    // Actions
    public void clickFinish() {
        find(finishButton).click();
    }

    public String getTotalAmount() {
        return find(totalLabel).getText();
    }
}

