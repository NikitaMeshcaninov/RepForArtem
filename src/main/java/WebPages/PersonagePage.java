package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikita on 19.07.2016.
 */
public class PersonagePage extends BasePage{
    @FindBy(xpath = "//a[text()='Главное меню']")
    private WebElement mainMenuButton;
    @FindBy(xpath = "//a[contains(text(), 'персонажи')]")
    private WebElement myPersonagesButton;
    @FindBy(xpath = "//h3[text() = '" + SettingsForTest.NAME + "']")
    private WebElement personageName;
    @FindBy(xpath = "//div[p/strong[text()='Раса:']]/following-sibling::div/p")
    private WebElement personageRace;
    @FindBy(xpath = ".//*[@id='expirience']")
    private WebElement personageExp;

    public PersonagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPersonageName() {
        return personageName;
    }

    public WebElement getPersonageRace() {
        return personageRace;
    }

    public WebElement getPersonageExp() {
        return personageExp;
    }

    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    public void openMinMenu() {
        mainMenuButton.click();
    }

    public PersonageListPage goToPersonageListPage() {

        myPersonagesButton.click();
       return new PersonageListPage(getWebDriver());
    }

}
