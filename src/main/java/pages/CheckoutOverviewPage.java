package pages;

import entity.ProductItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage extends BaseWebPage {

    @FindBy(xpath = "//*[contains(@class, 'summary_subtotal_label')]")
    WebElement itemsTotal;
    @FindBy(id = "finish")
    WebElement finish;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage verifyTheListOfItemsCorrect(List<ProductItem> expectedItems) {
        logger.info("Verify list of items is similar to: {}", expectedItems.stream().map(ProductItem::getName).collect(Collectors.toList()));
        List<ProductItem> actualItems = getItemsFromPage();
        boolean isSame;
        if (actualItems.size() != expectedItems.size()) {
            isSame = false;
        }
        {
            isSame = expectedItems.containsAll(actualItems);
        }
        Assert.assertTrue(isSame, "Verify the list of items on Checkout Overview is correct");
        return this;
    }

    public CheckoutOverviewPage verifyItemTotal() {
        logger.info("Verify item total is correct");
        double totalActual = Double.parseDouble(itemsTotal.getText().replace("Item total: $", ""));
        double totalExpected = getItemsFromPage().stream().mapToDouble(ProductItem::getPrice).sum();
        Assert.assertEquals(totalActual, totalExpected, "Verify total amount is correct");
        return this;
    }

    public void finishOrderAndVerifySuccessMessage() {
        logger.info("Finish order and verify success message");
        finish.click();
        WebElement successMessage = findByXpath("//h2[text()='Thank you for your order!']");
        Assert.assertTrue(successMessage.isDisplayed());
    }
}
