package pages;

import base.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikita on 18.07.2016.
 */
public class HomePage extends BasePage{
    private static String LOGIN_PAGE_URL = "http://erilon-staging.herokuapp.com/";
    @FindBy(xpath = ".//*[@id='nickName']")
    private WebElement nickNameTextField;

    @FindBy(xpath = "//button[@ng-click='login()']")
    private WebElement loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
        getDriver().get(LOGIN_PAGE_URL);
        assertEquals("Can't open login page", "Вход", getDriver().getTitle());
    }

    public PersonageListPage login(String userName) {
        nickNameTextField().type(userName);
        loginButton().click();
        return new PersonageListPage(getDriver());
    }

    public WebElementFacade nickNameTextField() {
        return element(nickNameTextField);
    }

    public WebElementFacade loginButton() {
        return element(loginButton);
    }
}