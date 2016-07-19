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

    public String TEST_NAME = SettingsForTest.name;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void delTestPersonageAndCloseBrowser() throws IOException, InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);

        PersonagePage personagePage = new PersonagePage(driver);
        personagePage.openMinMenu();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Мои персонажи']"))));
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
        assertEquals("The page title should e qual Google at the start of the test.", "Вход", driver.getTitle());
        homePage.login();

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).
                ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);

        PersonageListPage personageListPage = new PersonageListPage(driver);
        personageListPage.fillName(TEST_NAME);
        personageListPage.selecRace("Гном");
        personageListPage.fillexperience();
        personageListPage.addCharacer();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Тестовый персонаж']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[p/strong[text()='Раса:']]/following-sibling::div/p"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[p/strong[text()='Очки опыта:']]/following-sibling::div/div/p/strong"))));

        assertEquals("Name should be \"Тестовый персонаж\"", TEST_NAME,
                driver.findElement(By.xpath("//h3")).getText());
        assertEquals("Расса персонажа должна быть \"Гном\"", "гном"
                , driver.findElement(By.xpath("//div[p/strong[text()='Раса:']]/following-sibling::div/p")).getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть 200", "200"
                , driver.findElement(By.xpath("//div[p/strong[text()='Очки опыта:']]/following-sibling::div/div/p/strong")).getText().toLowerCase());

    }
}
