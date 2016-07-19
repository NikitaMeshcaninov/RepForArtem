package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Nikita on 18.07.2016.
 */
public class PersonageListPage {

    private final String testname = SettingsForTest.name;

    private WebDriver driver;
    private static String PageURL = "http://erilon-staging.herokuapp.com/views/user_personage_manager.html?id=1";
    @FindBy(xpath = ".//*[@id='name']")
    private WebElement nameField;
    @FindBy(xpath = ".//*[@id='race_id']")
    private WebElement raceSelector;
    @FindBy(xpath = ".//*[@id='experience']")
    private WebElement experienceField;
    @FindBy(xpath = "//button[@ng-click='createPersonage()']")
    WebElement addCharacterButton;
    @FindBy(xpath = "//table//tr[td/a[contains(text(), '" + testname + "')]]/td[2]//button")
    WebElement moreForCharacterButton;
    @FindBy(xpath = "//table//tr[td/a[contains(text(), '" + testname + "')]]/td[2]//a[contains(text(), 'х')]")
    WebElement delButton;

    public PersonageListPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PageURL);
        PageFactory.initElements(driver, this);
    }


    public void fillName(String name) {
        nameField.sendKeys(name);
    }

    public void selecRace(String raceName) {
        Select select = new Select(raceSelector);
        select.selectByVisibleText(raceName);
    }

    public void fillexperience() {
        experienceField.sendKeys("200");

    }

    public void addCharacer() {
        addCharacterButton.click();
    }

    public void openMoreMenuForPersonage() {
        moreForCharacterButton.click();
    }

    public void delCharacter() {
        delButton.click();
    }
}
