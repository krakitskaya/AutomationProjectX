package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BaseWebPage {

    private static List<WebElement> addToCartButtonList;
    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
        addToCartButtonList = driver.findElements(By.xpath("//*[ contains(@id, 'add-to-cart')]"));
        PageFactory.initElements(driver, this);
    }

    public ProductsPage addAllItemsToCart() {
        addToCartButtonList.forEach(WebElement::click);
        return this;
    }

    public ProductOverviewPage addItemToCart(String itemName) {
        logger.info("Add item to cart: {}", itemName);
        driver.findElement(By.xpath(String.format("//div[text()='%s']", itemName)))
                .click();
        return new ProductOverviewPage(driver);
    }

    public YourCartPage navigateToCart() {
        logger.info("Navigate to cart");
        cartButton.click();
        return new YourCartPage(driver);
    }

    public void sortByNameAndVerifySorted() {
        logger.info("Sort products by name");
        new Select(driver.findElement(By.className("product_sort_container"))).selectByValue("az");
        List<String> sortedActual = getProductNames();
        List<String> sortedExpected = getProductNames();
        Collections.sort(sortedExpected);
        Assert.assertEquals(sortedActual, sortedExpected, "Verify products sorted by Name A to Z. First product is " + sortedActual.get(0));

        new Select(driver.findElement(By.className("product_sort_container"))).selectByValue("za");
        sortedActual = getProductNames();
        Collections.reverse(sortedExpected);
        Assert.assertEquals(sortedActual, sortedExpected, "Verify products sorted by Name Z to A. First product is " + sortedActual.get(0));
    }

    public void sortByPriceAndVerifySorted() {
        logger.info("Sort products by price");
        new Select(driver.findElement(By.className("product_sort_container"))).selectByValue("lohi");
        List<Double> sortedActual = getProductPrices();
        List<Double> sortedExpected = getProductPrices();
        Collections.sort(sortedExpected);
        Assert.assertEquals(sortedActual, sortedExpected, "Verify products sorted by Price Low to High. First price is " + sortedActual.get(0));

        new Select(driver.findElement(By.className("product_sort_container"))).selectByValue("hilo");
        sortedActual = getProductPrices();
        Collections.reverse(sortedExpected);
        Assert.assertEquals(sortedActual, sortedExpected, "Verify products sorted by Price High to Low. First price is " + sortedActual.get(0));
    }

    private List<String> getProductNames(){
        return driver.findElements(By.className("inventory_item_name"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private List<Double> getProductPrices(){
        return driver.findElements(By.className("inventory_item_price"))
                .stream().map(WebElement::getText).map(el -> el.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
