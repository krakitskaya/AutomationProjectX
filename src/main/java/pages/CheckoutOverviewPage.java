package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage extends BaseWebPage {

    private static List<WebElement> checkoutItems;
    private static List<WebElement> checkoutPrices;
    @FindBy(xpath = "//*[contains(@class, 'summary_subtotal_label')]")
    WebElement itemsTotal;
    @FindBy(id = "finish")
    WebElement finish;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        checkoutItems = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        checkoutPrices = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_price')]"));
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage verifyTheListOfItemsCorrect(HashSet<String> expectedItems) {
        logger.info("Verify list of items is similar to: {}", expectedItems);
        Assert.assertEquals(expectedItems, getTextSet(checkoutItems), "Verify the list of items on Checkout Overview is correct");
        return this;
    }

    public CheckoutOverviewPage verifyItemTotal() {
        logger.info("Verify item total is correct");
        double totalActual = Double.parseDouble(itemsTotal.getText().replace("Item total: $", ""));
        double totalExpected = countTotalExpectedFromThePage();
        Assert.assertEquals(totalActual, totalExpected, "Verify total amount is correct");
        return this;
    }

    private static HashSet<String> getTextSet(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).collect(Collectors.toCollection(HashSet::new));
    }

    private static double countTotalExpectedFromThePage() {
        List<String> checkoutPricesTexts = checkoutPrices.stream()
                .map(WebElement::getText)
                .map(el -> el.replace("$", ""))
                .collect(Collectors.toList());
        return checkoutPricesTexts.stream().mapToDouble(Double::parseDouble).sum();
    }

    public void finishOrderAndVerifySuccessMessage() {
        logger.info("Finish order and verify success message");
        finish.click();
        WebElement successMessage = findByXpath("//h2[text()='Thank you for your order!']");
        Assert.assertTrue(successMessage.isDisplayed());
    }
}
