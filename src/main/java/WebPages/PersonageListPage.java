package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    private WebElement moreForCharacterButton;


    private WebElement delButton;

    public PersonageListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getMoreForCharacterButton(String name, WebDriver driver) {
        moreForCharacterButton = driver.findElement(By.xpath("//table//tr[td/a[contains(text(), '" + name + "')]]/td[2]//button"));
        return moreForCharacterButton;
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

    public PersonageCreatePage addCharacer() {

        addCharacterButton.click();
        return new PersonageCreatePage(getWebDriver());
    }

    public void openMoreMenuForPersonage(WebElement personageRow) {
        moreForCharacterButton = personageRow.findElement(By.xpath("td[2]//button"));
        moreForCharacterButton.click();
    }

    public void delCharacter(WebElement personageRow) {
        delButton = personageRow.findElement(By.xpath("td[2]//a[contains(text(), 'х')]"));
        delButton.click();
    }
}
