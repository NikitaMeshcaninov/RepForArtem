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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GameGnomClassTest {

    private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(GameGnomClassTest.class);

    private static final boolean INCREASE = true;
    private static final boolean DECREASE = false;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        personageCreatePage.clickAddWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));


        personageCreatePage.selectWorth(SettingsForTest.WORTH);

        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getWarningmesage()));

        assertEquals("Warning should be present", "Требования не выполнены!"
                , personageCreatePage.getWarningmesage().getText());
        assertFalse("Button should not be active", personageCreatePage.getAddButtonInPopupWindow().isEnabled());


        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAbortAddWorthButton()));
        personageCreatePage.abortAddWorth();
        LOGGER.info("1 Abort add worth operation");
        Utils.waitForInvisibility(driver, personageCreatePage.getAddWorthMenu());
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("2 Open character menu");
        personageCreatePage.changeAttribute(Attribute.STRENGTH, INCREASE, 2); //strength, add 2 points
        personageCreatePage.changeAttribute(Attribute.VITALITY, INCREASE, 3); // stamina, add 3 points
        LOGGER.info("3 Change hero stats to add worth");
        personageCreatePage.openPropertiesMenu();
        LOGGER.info("4 Open hero properties menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddWorth()));
        personageCreatePage.clickAddWorth();
        LOGGER.info("5 Open addWorth popup menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));
        personageCreatePage.selectWorth(SettingsForTest.WORTH);
        LOGGER.info("6 Select worth to add");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertFalse("7 No warning should be present", personageCreatePage.getWarningmesage().isDisplayed());
        assertTrue("Button should be active ", personageCreatePage.getAddButtonInPopupWindow().isEnabled());
        LOGGER.info("8 Check popup menu");
        personageCreatePage.submitAddWorth();
        LOGGER.info("9 Submitting worth add");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertTrue("Character should have worth", personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.savePersonage();
        LOGGER.info("10 Check that worth was added, save character");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("11 Refresh page");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("12 Open properties menu");
        personageCreatePage.openPropertiesMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        personageCreatePage.openWorth();
        LOGGER.info("13 Open worth menu");
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getAddWorth()));
        assertTrue("Worth of character should be saved", personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("14 Check is worth saved, open characteristics menu");
        LOGGER.info("15 Decrease str by 1");
        personageCreatePage.changeAttribute(Attribute.STRENGTH, DECREASE, 1);
        personageCreatePage.openPropertiesMenu();
        LOGGER.info("16 Open properties menu");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertFalse("Worth of character should disappeared", Utils.isElementCurrentlyPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
        personageCreatePage.savePersonage();
        LOGGER.info("17 Check is worth Внушительность is disappeared");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("18 Refresh page");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getSpecialPropertis()));
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getSpecialPropertis()));
        LOGGER.info("19 Open prop menu");
        personageCreatePage.openPropertiesMenu(); // тут упало с ошибкой "Element is not
        // clickable at point (455, 607). Other
        // element would receive the click:
        // <div class="panel-group" id="perkAccordeon">...</div>"
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        personageCreatePage.openWorth();
        LOGGER.info("20 Open worth menu");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertFalse("У персонажа должна исчезнуть внушительность", Utils.isElementCurrentlyPresent
                ("//*[contains(text(), 'Внушительность')]", driver));//*/
        LOGGER.info("21 End of test");
    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        PersonageCreatePage personageCreatePage = new PersonageCreatePage(driver);

        personageCreatePage.openMainMenu();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'персонажи')]")));
        PersonageListPage personageListPage = personageCreatePage.goToPersonageListPage();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tr[td/a[contains(text(), '" +
                SettingsForTest.NAME +
                "')]]/td[2]//button")));
        while (Utils.isElementCurrentlyPresent("//table//tr[td/a[contains(text(), '" +
                SettingsForTest.NAME +
                "')]]/td[2]//button", driver)) {

            personageListPage.openMoreMenuForPersonage();
            personageListPage.delCharacter();
            Thread.sleep(500);
            if (Utils.isElementCurrentlyPresent("//table//tr[td/a[contains(text(), '" +
                    SettingsForTest.NAME +
                    "')]]/td[2]//button", driver)) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tr[td/a[contains(text(), '" +
                        SettingsForTest.NAME +
                        "')]]/td[2]//button")));
            } else {
                break;
            }
        }
        driver.close();
    }
}
