package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductOverviewPage extends BaseWebPage {

    @FindBy(id = "add-to-cart")
    WebElement addToCart;
    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    public ProductOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductOverviewPage addToCart() {
        logger.info("Add product to cart");
        addToCart.click();
        return this;
    }

    public YourCartPage navigateToCart() {
        logger.info("Navigate to cart");
        cartButton.click();
        return new YourCartPage(driver);
    }
}
