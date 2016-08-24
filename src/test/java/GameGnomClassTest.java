import WebPages.*;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class GameGnomClassTest {

    private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(GameGnomClassTest.class);

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

        personageCreatePage.openPropertisMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getDisadvantages()));
        personageCreatePage.openWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddWorth()));
        personageCreatePage.clikAddWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));


        personageCreatePage.selectWorth(SettingsForTest.WORTH);

        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getWarningmesage()));

        assertEquals("Warning should be present", "Требования не выполнены!"
                , personageCreatePage.getWarningmesage().getText());
        assertEquals("Button should not be active", false, personageCreatePage.getAddButtonInPopupWindow().isEnabled());


        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAbortAddWorthButton()));
        personageCreatePage.abortAddWorth();
        LOGGER.info("Abort add worth operation");
        Thread.sleep(500);
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("Open character menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getIncreaseStr()));
        personageCreatePage.statChange("str",2,"up"); //strength, add 2 points
        personageCreatePage.statChange("sta",3,"up"); // stamina, add 3 points
        LOGGER.info("Change hero stats to add worth");
        personageCreatePage.openPropertisMenu();
        LOGGER.info("Open hero prop. menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddWorth()));
        personageCreatePage.clikAddWorth();
        LOGGER.info("Open addWorth popup menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorthSelector()));
        personageCreatePage.selectWorth(SettingsForTest.WORTH);
        LOGGER.info("Select worth to add");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddButtonInPopupWindow()));
        assertEquals("Button should be active ", true, personageCreatePage.getAddButtonInPopupWindow().isEnabled());
        assertEquals("No warning should be present", false, personageCreatePage.getWarningmesage().isDisplayed());
        LOGGER.info("Check popup menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getAddButtonInPopupWindow()));
        personageCreatePage.submitAddWorth();
        LOGGER.info("Submitting worth add");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        assertEquals("Character should have worth", true, personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.savePersonage();
        LOGGER.info("Check that worth was added, save character");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        LOGGER.info("Refresh page");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        personageCreatePage.openPropertisMenu();
        LOGGER.info("Open prop. menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        personageCreatePage.openWorth();
        LOGGER.info("Open worth menu");
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getAddWorth()));
        assertEquals("Worth of character should be saved", true, personageCreatePage.getWorthImpressiveness().isDisplayed());
        personageCreatePage.openCharacteristicsMenu();
        LOGGER.info("Check is worth saved, open characteristics menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getDecreaseStamina()));
        personageCreatePage.statChange("str",1,"down"); //strength, take 2 points
        LOGGER.info("Decrease str by 1");
        Thread.sleep(500);
        personageCreatePage.openPropertisMenu();
        LOGGER.info("Open prop menu");
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getAddWorth()));
        Utill search = new Utill();
        assertFalse("Worth of character should disappeared",search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
        personageCreatePage.savePersonage();
        LOGGER.info("Check is worth Внушительность is disappeared");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        LOGGER.info("Refresh page");
        wait.until(ExpectedConditions.attributeContains(personageCreatePage.getLoader(), "aria-hidden", "true"));
        personageCreatePage.openPropertisMenu();
        LOGGER.info("Open prop menu");
        wait.until(ExpectedConditions.elementToBeClickable(personageCreatePage.getWorth()));
        personageCreatePage.openWorth();
        LOGGER.info("Open worth menu");
        wait.until(ExpectedConditions.visibilityOf(personageCreatePage.getAddWorth()));
        assertEquals("У персонажа должна исчезнуть внушительность", false, search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        PersonageCreatePage personageCreatePage = new PersonageCreatePage(driver);
        Thread.sleep(500);
        personageCreatePage.openMinMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'персонажи')]")));


        PersonageListPage personageListPage = personageCreatePage.goToPersonageListPage();
        driver.switchTo().alert().accept();
        Thread.sleep(500);
        personageListPage.openMoreMenuForPersonage();
        personageListPage.delCharacter();
        driver.close();
    }
}
