import enums.Users;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.YourCartPage;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Validation1_FinishPurchase extends BaseTest {

    @Test
    void validation1_Finish_Purchase() {

        logger.info(String.format("login with username %s and password %s", Users.STANDARD_USER.getUsername(), password));

        List<WebElement> addedItems = getLoginPage()
                .login(Users.STANDARD_USER.getUsername(), password)
                .addAllItemsToCart()
                .navigateToCart()
                .removeThirdItemFromTheCart();

        //TODO: getItemsFromCart - get Item Objects
        HashSet<String> expectedItems = addedItems.stream().map(WebElement::getText).collect(Collectors.toCollection(HashSet::new));

        new YourCartPage(driver).navigateToCheckout()
                .fillCheckoutForm()
                .verifyTheListOfItemsCorrect(expectedItems)
                .verifyItemTotal()
                .finishOrderAndVerifySuccessMessage();
    }
}
