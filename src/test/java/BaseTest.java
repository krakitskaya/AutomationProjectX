
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseTest {

    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    public static WebDriver driver;
    protected static final String password = "secret_sauce";
    protected static final String appUrl = "https://www.saucedemo.com/";
    private final String SCREENSHOTS_DIRECTORY = System.getProperty("user.dir") + "\\test-output\\screenshot\\";

    @BeforeSuite
    public void beforeSuite() throws IOException {
        File file = new File(SCREENSHOTS_DIRECTORY);
        FileUtils.deleteDirectory(file);
    }

    @BeforeClass
    public void beforeClass(){
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, getFailedScreenshotFile(testResult.getInstanceName()));
        }
        driver.close();
    }

    protected LoginPage getLoginPage() {
        driver.get(appUrl);
        driver.manage().window().maximize();
        return new LoginPage(driver);
    }

    private File getFailedScreenshotFile(String testName){
        String dateTime = LocalDateTime.now().toString().replaceAll("[^\\sa-zA-Z0-9]", "");
        return new File(SCREENSHOTS_DIRECTORY + testName + "_" + dateTime + ".png");
    }
}
