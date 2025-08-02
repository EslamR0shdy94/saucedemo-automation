package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInfoPage extends BasePage {

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By pageTitle = By.cssSelector("span.title");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // Actions
    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        clearAndType(firstNameField, firstName);
        clearAndType(lastNameField, lastName);
        clearAndType(postalCodeField, postalCode);
    }

    public void clickContinue() {
        find(continueButton).click();
    }

    public boolean isErrorDisplayed(String expectedMessage) {
        try {
            WebElement error = find(errorMessage);
            return error.getText().equals(expectedMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAtCheckoutInfoPage() {
        return find(pageTitle).getText().equalsIgnoreCase("Checkout: Your Information");
    }

    public String getLastNameFieldValue() {
        return driver.findElement(lastNameField).getAttribute("value");
    }


}
