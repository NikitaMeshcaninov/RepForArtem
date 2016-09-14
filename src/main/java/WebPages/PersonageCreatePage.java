package WebPages;

import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Attr;

/**
 * Created by Nikita on 19.07.2016.
 */
public class PersonageCreatePage extends BasePage {


    @FindBy(xpath = "//a[@id='attached_skills_mm']")
    private WebElement attachedSkillsButton;

    @FindBy(xpath = "//h3[text() = '" + SettingsForTest.NAME + "']")
    private WebElement personageName;

    @FindBy(xpath = "//div[p/strong[text()='Раса:']]/following-sibling::div/p")
    private WebElement personageRace;

    @FindBy(xpath = ".//*[@id='expirience']")
    private WebElement personageExp;

    @FindBy(xpath = "//*[contains(text(),'Особенности')]")
    private WebElement specialPropertis;

    @FindBy(xpath = "//div[contains(text(),'Достоинства')]")
    private WebElement worth;

    @FindBy(xpath = "//*[@id='merits']/child::*/child::*/child::button")
    private WebElement addWorth;

    @FindBy(xpath = "//img[@src='/images/ic_close_24px.svg']/parent::*")
    private WebElement abortAddWorthButton;

    @FindBy(xpath = "//input [@placeholder='Введите название']")
    private WebElement worthSelector;

    @FindBy(xpath = "//p[contains(text(), 'не выполнены')]")
    private WebElement warningmesage;

    @FindBy(xpath = "//md-dialog//span[contains(text(), 'Добавить')]/parent::*")
    private WebElement addButtonInPopupWindow;

    @FindBy(xpath = "//*[contains(text(), 'Характеристики')]")
    private WebElement characteristics;

    @FindBy(xpath = "//*[contains(text(), 'Внушительность')]")
    private WebElement worthImpressiveness;

    @FindBy(xpath = "//*[@ng-click= 'savePersonage()']")
    private WebElement saveButton;


    @FindBy(xpath = "//md-dialog")
    private WebElement addWorthMenu;

    @FindBy(xpath = "//div[contains(text(), 'Недостатки')]")
    private WebElement disadvantages;

    public WebElement getDisadvantages() {
        return disadvantages;
    }

    public WebElement getAddWorthMenu() {
        return addWorthMenu;
    }


    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getWorthImpressiveness() {
        return worthImpressiveness;
    }

    public WebElement getCharacteristics() {
        return characteristics;
    }

    public WebElement getAddButtonInPopupWindow() {
        return addButtonInPopupWindow;
    }

    public WebElement getWarningmesage() {
        return warningmesage;
    }

    public WebElement getWorthSelector() {
        return worthSelector;
    }

    public WebElement getAbortAddWorthButton() {
        return abortAddWorthButton;
    }

    public WebElement getAddWorth() {
        return addWorth;
    }

    public WebElement getWorth() {
        return worth;
    }

    public PersonageCreatePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPersonageName(String name, WebDriver driver) {
        WebElement personageName = driver.findElement(By.xpath("//h3[text() = '" + name + "']"));
        return personageName;
    }

    public WebElement getPersonageRace() {
        return personageRace;
    }

    public WebElement getPersonageExp() {
        return personageExp;
    }

    public WebElement getSpecialPropertis() {
        return specialPropertis;
    }


    public void openPropertiesMenu() {
        specialPropertis.click();
    }

    public void openWorth() {
        worth.click();
    }

    public void clickAddWorth() {
        addWorth.click();
    }

    public void abortAddWorth() {
        abortAddWorthButton.click();
    }

    public void selectWorth(String worth) throws InterruptedException {
        worthSelector.sendKeys(worth);
        //Thread.sleep(500);
        worthSelector.sendKeys(Keys.ARROW_DOWN);
        //Thread.sleep(500);
        worthSelector.sendKeys(Keys.ENTER);
        //Thread.sleep(500);
    }


    public AttachedSkills goToAttachedSkills() {

        attachedSkillsButton.click();
        return new AttachedSkills(getWebDriver());
    }

    /**
     * @param direction if true - increase attribute esle decrease
     **/
    public void changeAttribute(Attribute attribute, boolean direction, int amount) {
        String directionXpath = direction ? "increase" : "decrease";
        WebElement attributeChangeButton = getWebDriver().findElement(By.xpath("//*[text()='" + attribute.getName() + "']/following-sibling::*/child::*[@ng-click='" + directionXpath + "Attribute(personageAttribute.id)']"));
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(attributeChangeButton));
        for (int i = 0; i < amount; i++) {
            attributeChangeButton.click();
        }
    }

    public void openCharacteristicsMenu() {
        characteristics.click();
    }

    public void submitAddWorth() {
        getAddButtonInPopupWindow().click();
    }

    public void savePersonage() {
        saveButton.click();
    }
}
