package pages;

import entity.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    public List<ProductItem> getItemsFromPage() {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        ArrayList<ProductItem> listOfItems = new ArrayList<>();
        for (WebElement cartItem : cartItems) {
            String name = cartItem.findElements(By.className("inventory_item_name")).get(0).getText();
            Double price = Double.parseDouble(cartItem.findElements(By.className("inventory_item_price")).get(0).getText().replace("$", ""));
            ProductItem item = new ProductItem(name, price);
            listOfItems.add(item);
        }
        return listOfItems;
    }
}
