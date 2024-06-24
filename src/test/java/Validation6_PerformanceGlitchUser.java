import entity.ProductItem;
import enums.Users;
import org.testng.annotations.Test;
import pages.YourCartPage;

import java.util.List;

public class Validation6_PerformanceGlitchUser extends BaseTest {

    @Test
    void validation6_PerformanceGlitchUser() {

        logger.info(String.format("login with username %s and password %s", Users.PERFORMANCE_GLITCH_USER.getUsername(), password));

        List<ProductItem> addedItems = getLoginPage()
                .login(Users.PERFORMANCE_GLITCH_USER.getUsername(), password)
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
