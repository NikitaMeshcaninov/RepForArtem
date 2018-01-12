package pages;

import base.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertEquals;

public class PersonageListPage extends BasePage{

    //#createPersonageButton
    private final String characterBtn = "//button[@ng-click='createPersonage()']";

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "race_id")
    private WebElement raceSelect;

    @FindBy(id = "experience")
    private WebElement experienceField;

    @FindBy(xpath = characterBtn)
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

    public void btnAddIsEnabled(){
        assertEquals("Button is disabled", this.addCharacterButton.isEnabled(), true);
    }

    public void btnAddIsDisabled(){
        assertEquals("Button is enabled", this.addCharacterButton.isEnabled(), false);
    }

    public PersonageCreatePage clickAddPersonageButton() {
        waitForElementVisibleByXpath(this.characterBtn);
        this.addCharacterButton.sendKeys(Keys.HOME);
        wait.until(ExpectedConditions.elementToBeClickable(this.addCharacterButton));
        addCharacterButton().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h3[.='Добавить персонажа']")));
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
