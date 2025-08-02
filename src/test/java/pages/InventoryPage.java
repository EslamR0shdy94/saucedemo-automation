package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class InventoryPage extends BasePage {
    private final WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By productNames = By.className("inventory_item_name");
    private final By productImages = By.cssSelector(".inventory_item_img img");
    private final By cartIcon = By.className("shopping_cart_link");

    private static final Map<String, String> expectedImageSources = Map.of(
            "Sauce Labs Backpack", "/static/media/sauce-backpack-1200x1500.34e7aa42.jpg",
            "Sauce Labs Bike Light", "/static/media/bike-light-1200x1500.a0c9caae.jpg",
            "Sauce Labs Bolt T-Shirt", "/static/media/bolt-shirt-1200x1500.c2599ac5.jpg",
            "Sauce Labs Fleece Jacket", "/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg",
            "Sauce Labs Onesie", "/static/media/red-onesie-1200x1500.2ec615b2.jpg",
            "Test.allTheThings() T-Shirt (Red)", "/static/media/red-tatt-1200x1500.30dadef4.jpg"
    );

    public boolean doAllProductsHaveCorrectImages() {
        List<WebElement> names = findAll(productNames);
        List<WebElement> images = findAll(productImages);

        if (names.size() != images.size()) return false;

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).getText().trim();
            String actualImage = images.get(i).getAttribute("src");
            actualImage = actualImage.substring(actualImage.indexOf("/static/media"));

            String expectedImage = expectedImageSources.get(name);
            if (expectedImage == null || !actualImage.contains(expectedImage)) {
                System.out.println("Mismatch: " + name);
                System.out.println("Expected: " + expectedImage);
                System.out.println("Actual: " + actualImage);
                return false;
            }
        }
        return true;
    }

    public List<String> clickAllAddToCartButtons() {
        List<String> added = new ArrayList<>();
        for (WebElement button : findAll(By.cssSelector("button.btn"))) {
            try {
                if (button.getText().trim().equalsIgnoreCase("Add to cart")) {
                    button.click();
                    added.add(getProductNameFromButton(button));
                    sleep(100);
                }
            } catch (Exception ignored) {}
        }
        return added;
    }

    public List<String> clickAllRemoveButtons() {
        List<String> removed = new ArrayList<>();
        for (WebElement button : findAll(By.cssSelector("button.btn"))) {
            try {
                if (button.getText().trim().equalsIgnoreCase("Remove")) {
                    button.click();
                    removed.add(getProductNameFromButton(button));
                    sleep(100);
                }
            } catch (Exception ignored) {}
        }
        return removed;
    }

    public void clickCartIcon() {
        clickWhenClickable(cartIcon, 5);
    }
    public void waitForPageToLoad() {
        By inventoryContainer = By.className("inventory_list");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
    }

}


