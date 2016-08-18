import WebPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class GameGnomClassTest {

    private WebDriver driver;


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
        personagePage.abortAddWorth();
        Thread.sleep(500);
        personagePage.openCharacteristicsMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getIncreaseStr()));
        personagePage.increaseStrBy1();
        personagePage.increaseStrBy1();
        personagePage.increaseStaminaBy1();
        personagePage.increaseStaminaBy1();
        personagePage.increaseStaminaBy1();
        personagePage.openPropertisMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddWorth()));
        personagePage.clikAddWorth();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorthSelector()));
        personagePage.selectWorth(SettingsForTest.WORTH);
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddButtonInPopupWindow()));
        assertEquals("Конопка добавить должна быть активна", true, personagePage.getAddButtonInPopupWindow().isEnabled());
        assertEquals("Предупреждение должно отсутствовать", false, personagePage.getWarningmesage().isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getAddButtonInPopupWindow()));
        personagePage.submitAddWorth();
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        assertEquals("У персонажа должна появиться внушительность", true, personagePage.getWorthImpressiveness().isDisplayed());
        personagePage.savePersonage();
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        personagePage.openPropertisMenu();
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorth()));
        personagePage.openWorth();
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        assertEquals("У персонажа должна остаться внушительность", true, personagePage.getWorthImpressiveness().isDisplayed());
        personagePage.openCharacteristicsMenu();
        System.out.println("1");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getDecreaseStamina()));
        System.out.println("2");
        personagePage.decreaseStaminaBy1();
        System.out.println("3");
        Thread.sleep(500);
        personagePage.openPropertisMenu();
        System.out.println("4");
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        System.out.println("5");
        Utill search = new Utill();
        System.out.println("6");
        assertEquals("У персонажа должна исчезнуть внушительность", false, search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]",driver));
        personagePage.savePersonage();
        System.out.println("7");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        System.out.println("8");
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        System.out.println("9");
        wait.until(ExpectedConditions.attributeContains(personagePage.getLoader(), "aria-hidden", "true"));
        System.out.println("10");
        personagePage.openPropertisMenu();
        System.out.println("11");
        wait.until(ExpectedConditions.elementToBeClickable(personagePage.getWorth()));
        System.out.println("12");
        personagePage.openWorth();
        System.out.println("13");
        wait.until(ExpectedConditions.visibilityOf(personagePage.getAddWorth()));
        System.out.println("14");
        assertEquals("У персонажа должна исчезнуть внушительность", false, search.isElementPresent
                ("//*[contains(text(), 'Внушительность')]",driver));
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
