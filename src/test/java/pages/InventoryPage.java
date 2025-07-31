package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryPage {
    private final WebDriverWait wait;
    private WebDriver driver;


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By productNames = By.className("inventory_item_name");
    private By productImages = By.cssSelector(".inventory_item_img img");

    private By product1 = By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']");
    private By product2 = By.cssSelector("button[data-test='add-to-cart-sauce-labs-bike-light']");
    private By product3 = By.cssSelector("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    private By product4 = By.cssSelector("button[data-test='add-to-cart-sauce-labs-fleece-jacket']");
    private By product5 = By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']");
    private By product6 = By.cssSelector("button[data-test='add-to-cart-test.allthethings()-t-shirt-(red)']");
    // Map of expected images for each product
    private static final Map<String, String> expectedImageSources = new HashMap<>() {{
        put("Sauce Labs Backpack", "/static/media/sauce-backpack-1200x1500.34e7aa42.jpg");
        put("Sauce Labs Bike Light", "/static/media/bike-light-1200x1500.a0c9caae.jpg");
        put("Sauce Labs Bolt T-Shirt", "/static/media/bolt-shirt-1200x1500.c2599ac5.jpg");
        put("Sauce Labs Fleece Jacket", "/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg");
        put("Sauce Labs Onesie", "/static/media/red-onesie-1200x1500.2ec615b2.jpg");
        put("Test.allTheThings() T-Shirt (Red)", "/static/media/red-tatt-1200x1500.30dadef4.jpg");
    }};

    public boolean doAllProductsHaveCorrectImages() {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> images = driver.findElements(productImages);

        if (names.size() != images.size()) {
            return false;
        }

        for (int i = 0; i < names.size(); i++) {
            String productName = names.get(i).getText().trim();
            String imgSrc = images.get(i).getAttribute("src");

            // Extract image file name from src
            String actualImage = imgSrc.substring(imgSrc.indexOf("/static/media"));

            String expectedImage = expectedImageSources.get(productName);
            if (expectedImage == null || !actualImage.contains(expectedImage)) {
                System.out.println("Mismatch for product: " + productName);
                System.out.println("Expected: " + expectedImage);
                System.out.println("Actual: " + actualImage);
                return false;
            }
        }
        return true;
    }


    public List<WebElement> getAllProductElements() {
        return driver.findElements(By.className("inventory_item"));
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // إضافة كل المنتجات التي يمكن إضافتها (التي أزرارها مكتوب عليها Add to cart)
    public List<String> clickAllAddToCartButtons() {
        List<String> addedProducts = new ArrayList<>();
        List<WebElement> buttons = driver.findElements(By.cssSelector("button.btn"));

        for (WebElement button : buttons) {
            try {
                String text = button.getText().trim();
                if (text.equalsIgnoreCase("Add to cart")) {
                    button.click();
                    addedProducts.add(getProductNameFromButton(button));
                    Thread.sleep(100); // optional delay
                }
            } catch (StaleElementReferenceException | ElementClickInterceptedException | InterruptedException ignored) {}
        }
        return addedProducts;
    }

    // إزالة كل المنتجات التي أزرارها مكتوب عليها Remove
    public List<String> clickAllRemoveButtons() {
        List<String> removedProducts = new ArrayList<>();
        List<WebElement> buttons = driver.findElements(By.cssSelector("button.btn"));

        for (WebElement button : buttons) {
            try {
                String text = button.getText().trim();
                if (text.equalsIgnoreCase("Remove")) {
                    button.click();
                    removedProducts.add(getProductNameFromButton(button));
                    Thread.sleep(100); // optional delay
                }
            } catch (StaleElementReferenceException | ElementClickInterceptedException | InterruptedException ignored) {}
        }
        return removedProducts;
    }

    // استخراج اسم المنتج المرتبط بزر Add/Remove
    private String getProductNameFromButton(WebElement button) {
        try {
            WebElement productNameElement = button.findElement(By.xpath("./ancestor::div[@class='inventory_item']//div[@class='inventory_item_name']"));
            return productNameElement.getText();
        } catch (Exception e) {
            return "Unknown Product";
        }
    }

}


