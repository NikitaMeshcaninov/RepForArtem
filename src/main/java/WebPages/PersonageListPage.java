package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Nikita on 18.07.2016.
 */
public class PersonageListPage extends BasePage{


    @FindBy(xpath = ".//*[@id='name']")
    private WebElement nameField;
    @FindBy(xpath = ".//*[@id='race_id']")
    private WebElement raceSelector;
    @FindBy(xpath = ".//*[@id='experience']")
    private WebElement experienceField;
    @FindBy(xpath = "//button[@ng-click='createPersonage()']")
    private WebElement addCharacterButton;
    @FindBy(xpath = "//table//tr[td/a[contains(text(), '" + SettingsForTest.NAME + "')]]/td[2]//button")
    private WebElement moreForCharacterButton;
    @FindBy(xpath = "//table//tr[td/a[contains(text(), '" + SettingsForTest.NAME + "')]]/td[2]//a[contains(text(), 'х')]")
    private WebElement delButton;

    public PersonageListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillName(String name) {
        nameField.sendKeys(name);
    }

    public void selecRace(String race) {
        Select select = new Select(raceSelector);
        select.selectByVisibleText(race);
    }

    public void fillexperience(String xp) {
        experienceField.sendKeys(xp);

    }

    public PersonagePage addCharacer() {

        addCharacterButton.click();
        return new PersonagePage(getWebDriver());
    }

    public void openMoreMenuForPersonage() {
        moreForCharacterButton.click();
    }

    public void delCharacter() {
        delButton.click();
    }
}
