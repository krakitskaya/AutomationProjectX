package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BaseWebPage {

    @FindBy(name = "user-name")
    WebElement usernameInput;
    @FindBy(name = "password")
    WebElement passwordInput;
    @FindBy(name = "login-button")
    WebElement loginButton;
    private static final String LOCKED_OUT_ERROR_MESSAGE = "Sorry, this user has been locked out";

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login(String username, String password) {
        fillAndSubmit(username, password);
        return new ProductsPage(driver);
    }

    public void loginAndVerifyLockedOut(String username, String password) {
        fillAndSubmit(username, password);
        logger.info("Verify error message present");
        WebElement errorMessage = findByXpath("//h3[@data-test='error']");
        Assert.assertTrue(errorMessage.getText().contains(LOCKED_OUT_ERROR_MESSAGE));
    }

    private void fillAndSubmit(String username, String password) {
        logger.info("Fill and submit login form");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
