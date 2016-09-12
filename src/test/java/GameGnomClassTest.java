import WebPages.*;
import enums.Attribute;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class GameGnomClassTest {

    private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(GameGnomClassTest.class);

    private static final boolean INCREASE = true;
    private static final boolean DECREASE = false;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        HomePage homePage = new HomePage(driver);
        assertEquals("The page should enter page to game site", "Вход", driver.getTitle());

        PersonageListPage personageListPage = homePage.login(SettingsForTest.USER_NAME);
        personageListPage.fillName(SettingsForTest.NAME);
        personageListPage.selecRace(SettingsForTest.RACE);
        personageListPage.fillexperience(SettingsForTest.XP);
        PersonageCreatePage personageCreatePage = personageListPage.addCharacer();

        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getPersonageName()));
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getPersonageRace()));
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getPersonageExp()));




        assertEquals("Name should be " + SettingsForTest.NAME, SettingsForTest.NAME,
                personageCreatePage.getPersonageName().getText());
        assertEquals("Race of personage should be " + SettingsForTest.NAME.toLowerCase(), SettingsForTest.RACE.toLowerCase()
                , personageCreatePage.getPersonageRace().getText().toLowerCase());
        assertEquals("Exp should be " + SettingsForTest.XP.toLowerCase(), SettingsForTest.XP.toLowerCase()
                , personageCreatePage.getPersonageExp().getAttribute("value"));

        personageCreatePage.openPropertiesMenu();

        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getDisadvantages()));
        personageCreatePage.openWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddWorth()));
        personageCreatePage.clikAddWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));


        personageCreatePage.selectWorth(SettingsForTest.WORTH);

        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getWarningmesage()));

        assertEquals("Warning should be present", "Требования не выполнены!"
                , personageCreatePage.getWarningmesage().getText());
        assertFalse("Button should not be active",  personageCreatePage.getAddButtonInPopupWindow().isEnabled());


        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAbortAddWorthButton()));
        personageCreatePage.abortAddWorth();
        LOGGER.info("Abort add worth operation");
        Utils.waitForInvisibility(driver, personageCreatePage.getAddWorthMenu());
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("Open character menu");
        personageCreatePage.changeAttribute(Attribute.STRENGTH, INCREASE, 2); //strength, add 2 points
        personageCreatePage.changeAttribute(Attribute.VITALITY, INCREASE, 3); // stamina, add 3 points
        LOGGER.info("Change hero stats to add worth");
        personageCreatePage.openPropertiesMenu();
        LOGGER.info("Open hero properties menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddWorth()));
        personageCreatePage.clikAddWorth();
        LOGGER.info("Open addWorth popup menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));
        personageCreatePage.selectWorth(SettingsForTest.WORTH);
        LOGGER.info("Select worth to add");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertFalse("No warning should be present",  personageCreatePage.getWarningmesage().isDisplayed());
        assertTrue("Button should be active ",  personageCreatePage.getAddButtonInPopupWindow().isEnabled());
        LOGGER.info("Check popup menu");
        personageCreatePage.submitAddWorth();
        LOGGER.info("Submitting worth add");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertTrue("Character should have worth",  personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.savePersonage();
        LOGGER.info("Check that worth was added, save character");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("Refresh page");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("Open properties menu");
        personageCreatePage.openPropertiesMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        personageCreatePage.openWorth();
        LOGGER.info("Open worth menu");
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getAddWorth()));
        assertTrue("Worth of character should be saved",personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("Check is worth saved, open characteristics menu");
        LOGGER.info("Decrease str by 1");
        personageCreatePage.changeAttribute(Attribute.STRENGTH, DECREASE, 1);
        personageCreatePage.openPropertiesMenu();
        LOGGER.info("Open properties menu");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        //висит ровно 30 секунд, видимо не работатет
        assertFalse("Worth of character should disappeared", Utils.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
        personageCreatePage.savePersonage();
        LOGGER.info("Check is worth Внушительность is disappeared");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("Refresh page");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("Open prop menu");
        personageCreatePage.openPropertiesMenu(); // тут упало с ошибкой "Element is not
                                                 // clickable at point (455, 607). Other
                                                 // element would receive the click:
                                                 // <div class="panel-group" id="perkAccordeon">...</div>"
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        personageCreatePage.openWorth();
        LOGGER.info("Open worth menu");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        //висит ровно 30 секунд, видимо не работатет
        assertFalse("У персонажа должна исчезнуть внушительность",  Utils.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
        LOGGER.info("End of test");
    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        PersonageCreatePage personageCreatePage = new PersonageCreatePage(driver);
        personageCreatePage.openMainMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'персонажи')]")));


        PersonageListPage personageListPage = personageCreatePage.goToPersonageListPage();
        personageListPage.openMoreMenuForPersonage();
        personageListPage.delCharacter();
        driver.close();
    }
}
