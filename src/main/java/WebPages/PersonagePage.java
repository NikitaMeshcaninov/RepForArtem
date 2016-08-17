package WebPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Nikita on 19.07.2016.
 */
public class PersonagePage extends BasePage {
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
    @FindBy(xpath = "//*[contains(text(),'Особенности')]")
    private WebElement specialPropertis;
    @FindBy(xpath = "//div[contains(text(),'Достоинства')]")
    private WebElement worth;
    @FindBy(xpath = ".//*[@ng-click='calculateMeritSelectOptions();showAddMeritDialog(selectMerits, addPersonageMerit, validatePrerequisites)']")
    private WebElement addWorth;
    @FindBy(xpath = "//img[@src='/images/ic_close_24px.svg']/parent::*")
    private WebElement abortAddWorthButton;
    @FindBy(xpath = "//input [@placeholder='Введите название']")
    private WebElement worthSelector;
    @FindBy(xpath = "//p[contains(text(), 'не выполнены')]")
    private WebElement warningmesage;
    @FindBy(xpath = "html/body/div[3]/md-dialog/md-dialog-actions/button")
    private WebElement addButtonInPopupWindow;
    @FindBy(xpath = "//*[contains(text(), 'Характеристики')]")
    private WebElement characteristics;
    @FindBy(xpath = "//*[text()='Сила']/following-sibling::*/child::*[@ng-click='increaseAttribute(personageAttribute.id)']")
    private WebElement increaseStr;
    @FindBy(xpath = "//*[text()='Сила']/following-sibling::*/child::*[@ng-click='decreaseAttribute(personageAttribute.id)']")
    private WebElement decreaseStr;
    @FindBy(xpath = "//*[text()='Живучесть']/following-sibling::*/child::*[@ng-click='increaseAttribute(personageAttribute.id)']")
    private WebElement increaseStamina;
    @FindBy(xpath = "//*[text()='Живучесть']/following-sibling::*/child::*[@ng-click='decreaseAttribute(personageAttribute.id)']")
    private WebElement decreaseStamina;
    @FindBy (xpath = "//*[contains(text(), 'Внушительность')]")
    private WebElement worthImpressiveness;
    @FindBy (xpath = "//*[@ng-click= 'savePersonage()']")
    private WebElement saveButton;
    @FindBy (xpath = "//*[@id= 'loader']")
    private WebElement loader;
    @FindBy (xpath = "html/body/div[3]/md-dialog")
    private WebElement addWorthMenu;
    @FindBy (xpath = "//div[contains(text(), 'Недостатки')]")
    private WebElement disadvantages;

    public WebElement getDisadvantages() {
        return disadvantages;
    }

    public WebElement getAddWorthMenu() {
        return addWorthMenu;
    }

    public WebElement getLoader() {
        return loader;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getWorthImpressiveness() {
        return worthImpressiveness;
    }

    public WebElement getDecreaseStamina() {
        return decreaseStamina;
    }

    public WebElement getIncreaseStamina() {
        return increaseStamina;
    }

    public WebElement getDecreaseStr() {
        return decreaseStr;
    }

    public WebElement getIncreaseStr() {
        return increaseStr;
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

    public WebElement getSpecialPropertis() {
        return specialPropertis;
    }

    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    public void openPropertisMenu() {
        specialPropertis.click();
    }

    public void openWorth() {
        worth.click();
    }

    public void clikAddWorth() {
        addWorth.click();
    }

    public void abortAddWorth() {
        abortAddWorthButton.click();
    }

    public void selectWorth(String worth) throws InterruptedException {
        worthSelector.sendKeys(worth);
        Thread.sleep(500);
        worthSelector.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        worthSelector.sendKeys(Keys.ENTER);
        Thread.sleep(500);
    }

    public void openMinMenu() {
        mainMenuButton.click();
    }

    public PersonageListPage goToPersonageListPage() {

        myPersonagesButton.click();
        return new PersonageListPage(getWebDriver());
    }

    public void increaseStrBy1() {
        increaseStr.click();
    }

    public void decreaseStrBy1() {
        decreaseStr.click();
    }

    public void increaseStaminaBy1() {
        increaseStamina.click();
    }

    public void decreaseStaminaBy1(){
        decreaseStamina.click();
    }

    public void openCharacteristicsMenu(){
        characteristics.click();
    }

    public void submitAddWorth(){
        addButtonInPopupWindow.click();
    }
    public void savePersonage(){
        saveButton.click();
    }
}
