package pages;

import entity.ProductItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class YourCartPage extends BaseWebPage {

    @FindBy(name = "checkout")
    WebElement checkout;

    public YourCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public YourCartPage removeThirdItemFromTheCart() {
        logger.info("Remove third item from the cart");
        String thirdItem = getItemsFromPage().get(2).getName();
        findByXpath(String.format("//*[div[text()='%s']]//ancestor::div[@class='cart_item_label']//button[text()='Remove']", thirdItem))
                .click();
        return this;
    }

    public void verifyItemAdded(String itemText) {
        logger.info("Verify item added: {}", itemText);
        List<String> addedItemsTextsCart = getItemsFromPage().stream().map(ProductItem::getName).collect(Collectors.toList());
        Assert.assertEquals(addedItemsTextsCart.size(), 1, "Verify only one item added");
        Assert.assertTrue(addedItemsTextsCart.contains(itemText), "Verify name of item added");
    }

    public CheckoutYourInformationPage navigateToCheckout() {
        logger.info("Navigate to checkout");
        checkout.click();
        return new CheckoutYourInformationPage(driver);
    }
}
