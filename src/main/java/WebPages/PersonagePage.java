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
    @FindBy (xpath = ".//*[@id='accordion']/div[3]/h4/a/div")
    private WebElement specialPropertis;
    @FindBy (xpath = ".//*[@id='perkAccordeon']/div[2]/h4/a/div")
    private WebElement worth;
    @FindBy (xpath = ".//*[@id='merits']/div/md-content/button")
    private WebElement addWorth;
    @FindBy (xpath = "html/body/div[3]/md-dialog/md-toolbar/div/button")
    private WebElement abortAddWorthButton;
    @FindBy (xpath = ".//md-autocomplete-wrap/input")
    private WebElement worthSelector;
    @FindBy (xpath = "//p[contains(text(), 'не выполнены')]" )
    private WebElement warningmesage;
    @FindBy (xpath = "html/body/div[3]/md-dialog/md-dialog-actions/button")
    private WebElement addButtonInPopupWindow;

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

    public void openPropertis(){
        specialPropertis.click();
    }

    public void openWorth(){
        worth.click();
    }

    public void clikAddWorth(){
        addWorth.click();
    }

    public void abortAddWorth(){
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

}
