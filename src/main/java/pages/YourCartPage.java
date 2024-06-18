package pages;

import entity.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YourCartPage extends BaseWebPage {

    private static List<WebElement> addedToCartItems;
    @FindBy(name = "checkout")
    WebElement checkout;

    public YourCartPage(WebDriver driver) {
        super(driver);
        addedToCartItems = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> removeThirdItemFromTheCart() {
        logger.info("Remove third item from the cart");
        String thirdItem = addedToCartItems.get(2).getText();
        addedToCartItems.remove(2);
        findByXpath(String.format("//*[div[text()='%s']]//ancestor::div[@class='cart_item_label']//button[text()='Remove']", thirdItem))
                .click();
        return addedToCartItems;
    }

    public void verifyItemAdded(String itemText) {
        logger.info("Verify item added: {}", itemText);
        List<String> addedItemsTextsCart = addedToCartItems.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertEquals(addedItemsTextsCart.size(), 1, "Verify only one item added");
        Assert.assertTrue(addedItemsTextsCart.contains(itemText), "Verify name of item added");
    }

    public CheckoutYourInformationPage navigateToCheckout() {
        logger.info("Navigate to checkout");
        checkout.click();
        return new CheckoutYourInformationPage(driver);
    }

    public List<CartItem> getCartItems() {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        ArrayList<CartItem> listOfItems = new ArrayList<CartItem>();
        for (WebElement cartItem : cartItems) {
            cartItem.findElements(By.xpath(""));
            //find elements for price and name
            //create new item from price and name and add to list
        }
        return listOfItems;
    }
}
