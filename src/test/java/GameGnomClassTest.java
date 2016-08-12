import WebPages.PersonageListPage;
import WebPages.HomePage;
import WebPages.PersonagePage;
import WebPages.SettingsForTest;
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


    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        PersonagePage personagePage = new PersonagePage(driver);
        personagePage.openMinMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'персонажи')]")));
        personagePage.goToPersonageListPage();
        driver.switchTo().alert().accept();
        PersonageListPage personageListPage = new PersonageListPage(driver);
        personageListPage.openMoreMenuForPersonage();
        personageListPage.delCharacter();
        driver.close();
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(driver);
        assertEquals("The page should enter page to game site", "Вход", driver.getTitle());
        homePage.login();

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        PersonageListPage personageListPage = new PersonageListPage(driver);
        personageListPage.fillName(SettingsForTest.name);
        personageListPage.selecRace("Гном");
        personageListPage.fillexperience();
        personageListPage.addCharacer();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expirience")));

        PersonagePage personagePage = new PersonagePage(driver);

        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageName()));
        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageRace()));
        wait.until(ExpectedConditions.visibilityOf(personagePage.getPersonageExp()));


        assertEquals("Name should be " + SettingsForTest.name, SettingsForTest.name,
                personagePage.getPersonageName().getText());
        assertEquals("Расса персонажа должна быть " + SettingsForTest.name.toLowerCase(), SettingsForTest.race.toLowerCase()
                , personagePage.getPersonageRace().getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть " + SettingsForTest.xp.toLowerCase(), SettingsForTest.xp.toLowerCase()
                , personagePage.getPersonageExp().getAttribute("value"));

    }
}
