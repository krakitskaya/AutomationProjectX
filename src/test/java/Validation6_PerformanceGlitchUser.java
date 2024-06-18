import enums.Users;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.YourCartPage;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Validation6_PerformanceGlitchUser extends BaseTest {

    @Test
    void validation6_PerformanceGlitchUser() {

        logger.info(String.format("login with username %s and password %s", Users.PERFORMANCE_GLITCH_USER.getUsername(), password));

        List<WebElement> addedItems = getLoginPage()
                .login(Users.PERFORMANCE_GLITCH_USER.getUsername(), password)
                .addAllItemsToCart()
                .navigateToCart()
                .removeThirdItemFromTheCart();

        HashSet<String> expectedItems = addedItems.stream().map(WebElement::getText).collect(Collectors.toCollection(HashSet::new));

        new YourCartPage(driver).navigateToCheckout()
                .fillCheckoutForm()
                .verifyTheListOfItemsCorrect(expectedItems)
                .verifyItemTotal()
                .finishOrderAndVerifySuccessMessage();
    }
}
