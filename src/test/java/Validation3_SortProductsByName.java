import enums.Users;
import org.testng.annotations.Test;

public class Validation3_SortProductsByName extends BaseTest {

    @Test
    void validation3_SortProductsByName() {

        logger.info(String.format("login with username %s and password %s", Users.STANDARD_USER.getUsername(), password));

        getLoginPage()
                .login(Users.STANDARD_USER.getUsername(), password)
                .sortByNameAndVerifySorted();
    }
}
