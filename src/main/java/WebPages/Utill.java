package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Nikita on 17.08.2016.
 */
public class Utill {
    public boolean isElementPresent(String xpath, WebDriver driver) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}
