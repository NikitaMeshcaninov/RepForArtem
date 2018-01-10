package pages;

import base.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikita on 18.07.2016.
 */
public class LoginPage extends BasePage{
    private static String LOGIN_PAGE_URL = "http://erilon-staging.herokuapp.com/";
    @FindBy(css = "#validation-name")
    private WebElement nickNameTextField;

    @FindBy(css = ".btn")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        getDriver().get(LOGIN_PAGE_URL);
        assertEquals("Can't open login page", "Login", getDriver().getTitle());
    }

    final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

    public PersonageListPage login(String userName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn")));

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