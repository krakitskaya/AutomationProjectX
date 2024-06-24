import entity.ProductItem;
import enums.Users;
import org.testng.annotations.Test;
import pages.YourCartPage;

import java.util.List;

public class Validation1_FinishPurchase extends BaseTest {

    @Test
    void validation1_Finish_Purchase() {

        logger.info(String.format("login with username %s and password %s", Users.STANDARD_USER.getUsername(), password));

        List<ProductItem> addedItems = getLoginPage()
                .login(Users.STANDARD_USER.getUsername(), password)
                .addAllItemsToCart()
                .navigateToCart()
                .removeThirdItemFromTheCart()
                .getItemsFromPage();

        new YourCartPage(driver).navigateToCheckout()
                .fillCheckoutForm()
                .verifyTheListOfItemsCorrect(addedItems)
                .verifyItemTotal()
                .finishOrderAndVerifySuccessMessage();
    }
}
