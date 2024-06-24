import enums.Users;

import org.testng.annotations.Test;

public class Validation2_ProblemUser extends BaseTest {
    private static final String itemToAdd = "Sauce Labs Fleece Jacket";

    @Test
    void validation2_ProblemUser() {

        logger.info(String.format("login with username %s and password %s", Users.PROBLEM_USER.getUsername(), password));

        getLoginPage()
                .login(Users.PROBLEM_USER.getUsername(), password)
                .addItemToCart(itemToAdd)
                .addToCart()
                .navigateToCart()
                .verifyItemAdded(itemToAdd);
    }
}
