import WebPages.*;
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

import static junit.framework.Assert.assertEquals;

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
        PersonagePage personagePage = personageListPage.addCharacer();

        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageName()));
        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageRace()));
        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageExp()));


        assertEquals("Name should be " + SettingsForTest.NAME, SettingsForTest.NAME,
                personagePage.getPersonageName().getText());
        assertEquals("Расса персонажа должна быть " + SettingsForTest.NAME.toLowerCase(), SettingsForTest.RACE.toLowerCase()
                , personagePage.getPersonageRace().getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть " + SettingsForTest.XP.toLowerCase(), SettingsForTest.XP.toLowerCase()
                , personagePage.getPersonageExp().getAttribute("value"));

        personagePage.openPropertisMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getDisadvantages()));
        personagePage.openWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddWorth()));
        personagePage.clikAddWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorthSelector()));


        personagePage.selectWorth(SettingsForTest.WORTH);

        wait.until(ExpectedConditions.visibilityOf(personagePage.getWarningmesage()));

        assertEquals("Принехватке атрибутов должо высвечиваться предупреждение", "Требования не выполнены!"
                , personagePage.getWarningmesage().getText());
        assertEquals("Конопка добавить должна быть не активна еси требования не " +
                "выполненны", false, personagePage.getAddButtonInPopupWindow().isEnabled());


        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAbortAddWorthButton()));
        LOGGER.info("1");
        personagePage.abortAddWorth();
        LOGGER.info("2");
        Thread.sleep(500);
        LOGGER.info("3");
        personagePage.openCharacteristicsMenu();
        LOGGER.info("4");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getIncreaseStr()));
        LOGGER.info("5");
        personagePage.increaseStrBy1();
        LOGGER.info("6");
        personagePage.increaseStrBy1();
        personagePage.increaseStaminaBy1();
        personagePage.increaseStaminaBy1();
        personagePage.increaseStaminaBy1();
        LOGGER.info("7");
        personagePage.openPropertisMenu();
        LOGGER.info("8");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddWorth()));
        personagePage.clikAddWorth();
        LOGGER.info("9");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorthSelector()));
        personagePage.selectWorth(SettingsForTest.WORTH);
        LOGGER.info("10");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddButtonInPopupWindow()));
        assertEquals("Конопка добавить должна быть активна", true, personagePage.getAddButtonInPopupWindow().isEnabled());
        assertEquals("Предупреждение должно отсутствовать", false, personagePage.getWarningmesage().isDisplayed());
        LOGGER.info("11");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddButtonInPopupWindow()));
        LOGGER.info("12");
        personagePage.submitAddWorth();
        LOGGER.info("13");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        assertEquals("У персонажа должна появиться внушительность", true, personagePage.getWorthImpressiveness().isDisplayed());
        personagePage.savePersonage();
        LOGGER.info("14");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        LOGGER.info("15");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        personagePage.openPropertisMenu();
        LOGGER.info("16");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorth()));
        personagePage.openWorth();
        LOGGER.info("17");
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        assertEquals("У персонажа должна остаться внушительность", true, personagePage.getWorthImpressiveness().isDisplayed());
        personagePage.openCharacteristicsMenu();
        LOGGER.info("18");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getDecreaseStamina()));
        LOGGER.info("19");
        personagePage.decreaseStaminaBy1();
        LOGGER.info("20");
        Thread.sleep(500);
        personagePage.openPropertisMenu();
        LOGGER.info("21");
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        LOGGER.info("22");
        Utill search = new Utill();
        LOGGER.info("23");
        assertEquals("У персонажа должна исчезнуть внушительность", false, search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
        personagePage.savePersonage();
        LOGGER.info("24");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("25");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        LOGGER.info("26");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        LOGGER.info("27");
        personagePage.openPropertisMenu();
        LOGGER.info("28");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorth()));
        LOGGER.info("29");
        personagePage.openWorth();
        LOGGER.info("30");
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        LOGGER.info("31");
        assertEquals("У персонажа должна исчезнуть внушительность", false, search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]", driver));
    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        PersonagePage personagePage = new PersonagePage(driver);
        Thread.sleep(500);
        personagePage.openMinMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'персонажи')]")));


        PersonageListPage personageListPage = personagePage.goToPersonageListPage();
        driver.switchTo().alert().accept();
        Thread.sleep(500);
        personageListPage.openMoreMenuForPersonage();
        personageListPage.delCharacter();
        driver.close();
    }
}
