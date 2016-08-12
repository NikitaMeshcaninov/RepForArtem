package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private WebDriver webDriver;
    
    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

}
