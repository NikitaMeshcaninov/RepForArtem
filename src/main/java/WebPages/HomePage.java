package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikita on 18.07.2016.
 */
public class HomePage {
    private WebDriver driver;
    private static String pageURL = "http://erilon-staging.herokuapp.com/";
    @FindBy(how = How.XPATH, using = ".//*[@id='nickName']")
    private WebElement nickNameTextField;
    @FindBy(how = How.XPATH, using = "//button[@ng-click='login()']")
    private WebElement loginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(pageURL);
        PageFactory.initElements(driver, this);
    }

    public void login() {
        nickNameTextField.sendKeys(SettingsForTest.USER_NAME);
        loginButton.click();
    }
}