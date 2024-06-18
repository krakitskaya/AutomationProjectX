package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BaseWebPage {
    protected final WebDriver driver;
    protected static Logger logger = LoggerFactory.getLogger(BaseWebPage.class);

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findByXpath(String path){
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
    }
}
