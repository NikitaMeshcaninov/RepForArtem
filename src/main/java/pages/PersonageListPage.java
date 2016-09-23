package pages;

import base.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Nikita on 18.07.2016.
 */
public class PersonageListPage extends BasePage{


    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "race_id")
    private WebElement raceSelect;

    @FindBy(id = "experience")
    private WebElement experienceField;

    @FindBy(xpath = "//button[@ng-click='createPersonage()']")
    private WebElement addCharacterButton;


    public PersonageListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElementFacade nameField() {
        return element(nameField);
    }

    public WebElementFacade raceSelect() {
        return element(raceSelect);
    }

    public WebElementFacade experienceField() {
        return element(experienceField);
    }

    public WebElementFacade addCharacterButton() {
        return element(addCharacterButton);
    }

    public void fillName(String name) {
        nameField().type(name);
    }

    public void selectRace(String race) {
        raceSelect().selectByVisibleText(race);
    }

    public void fillExperience(String xp) {
        experienceField().type(xp);
    }

    public WebElementFacade getPersonageRow(String name) {
        return element(getDriver().findElement(By.xpath("//table//tr[td/a[contains(text(), '" + name + "')]]")));
    }

    public boolean isPersonagePresent(String name) {
        return isElementCurrentlyPresent(By.xpath("//table//tr[td/a[contains(text(), '" + name + "')]]"));
    }

    public PersonageCreatePage clickAddPersonageButton() {
        addCharacterButton().click();
        waitForLoader();
        return new PersonageCreatePage(getDriver());
    }

    public void openMoreMenuForPersonage(WebElement personageRow) {
        personageRow.findElement(By.xpath("td[2]//button")).click();
    }

    public void deletePersonage(WebElementFacade personageRow) {
        personageRow.find(By.xpath("td[2]//a[contains(text(), 'х')]")).click();
    }
}
