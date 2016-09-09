package WebPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikita on 17.08.2016.
 */
public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class);
    public static boolean isElementPresent(String xpath, WebDriver driver) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static WebDriverWait waitFor(WebDriver driver) {
        return new WebDriverWait(driver, 30);
    }

    public static void waitForInvisibility(WebDriver driver, WebElement element) {
        try {
            waitFor(driver).until(invisibilityOfElement(element));
        } catch (TimeoutException e) {
            if (element.isDisplayed()) {
                LOGGER.warn("Element " + element.toString() + " still visible!!");
            }
        }
    }

    private static ExpectedCondition<Boolean> invisibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    webDriver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
                    visible = element.isDisplayed();
                } catch (Exception e) {/**/} finally {
                    webDriver.manage().timeouts()
                            .implicitlyWait(30, TimeUnit.SECONDS);
                }
                return !visible;
            }

            @Override
            public String toString() {
                return "invisibility of element " + element;
            }
        };
    }

}
