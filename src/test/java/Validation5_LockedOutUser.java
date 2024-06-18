import enums.Users;
import org.testng.annotations.Test;

public class Validation5_LockedOutUser extends BaseTest {

    @Test
    void validation5_LockedOutUser() {

        logger.info(String.format("login with username %s and password %s", Users.LOCKED_OUT_USER.getUsername(), password));

        getLoginPage()
                .loginAndVerifyLockedOut(Users.LOCKED_OUT_USER.getUsername(), password);
    }
}
