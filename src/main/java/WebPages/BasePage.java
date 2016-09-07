package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[h4]/following-sibling::div[1]")
    private WebElement playerRole;
    
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

    public String getPlayerRole() {
        return playerRole.getText().replaceAll("[^:]+:\\s*(.*)", "$1");
    }

}
