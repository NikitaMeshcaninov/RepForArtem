package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikita on 18.07.2016.
 */
public class HomePage extends BasePage{
    private static String pageURL = "http://erilon-staging.herokuapp.com/";
    @FindBy(how = How.XPATH, using = ".//*[@id='nickName']")
    private WebElement nickNameTextField;
    @FindBy(how = How.XPATH, using = "//button[@ng-click='login()']")
    private WebElement loginButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        getWebDriver().get(pageURL);
    }

    public PersonageListPage login(String userName) {
        nickNameTextField.sendKeys(userName);
        loginButton.click();
        return new PersonageListPage(getWebDriver());
    }
}