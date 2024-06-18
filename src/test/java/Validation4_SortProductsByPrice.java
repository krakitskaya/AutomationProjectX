import enums.Users;
import org.testng.annotations.Test;

public class Validation4_SortProductsByPrice extends BaseTest {

    @Test
    void validation4_SortProductsByPrice() {

        logger.info(String.format("login with username %s and password %s", Users.STANDARD_USER.getUsername(), password));

        getLoginPage()
                .login(Users.STANDARD_USER.getUsername(), password)
                .sortByPriceAndVerifySorted();
    }
}
