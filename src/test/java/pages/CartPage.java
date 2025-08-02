package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By pageTitle = By.className("title"); // should contain "Your Cart"
    private final By checkoutButton = By.id("checkout");
    private final By cartItems = By.className("cart_item");

    // Methods
    public boolean isAtCartPage() {
        return find(pageTitle).getText().equalsIgnoreCase("Your Cart");
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = findAll(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clickCheckoutButton() {
        clickWhenClickable(checkoutButton, 5);
    }
}
