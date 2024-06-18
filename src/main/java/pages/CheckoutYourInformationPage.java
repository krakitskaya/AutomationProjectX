package pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CheckoutYourInformationPage extends BaseWebPage {

    @FindBy(name = "firstName")
    WebElement firtName;
    @FindBy(name = "lastName")
    WebElement lastName;
    @FindBy(name = "postalCode")
    WebElement zipCode;
    @FindBy(id = "continue")
    WebElement continueButton;

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage fillCheckoutForm() {
        logger.info("Fill checkout form");
        firtName.sendKeys(RandomStringUtils.randomAlphabetic(12));
        lastName.sendKeys(RandomStringUtils.randomAlphabetic(12));
        zipCode.sendKeys(String.valueOf(10000 + new Random().nextInt(89999)));
        continueButton.click();
        return new CheckoutOverviewPage(driver);
    }
}