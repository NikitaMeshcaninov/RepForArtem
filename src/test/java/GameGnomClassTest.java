import WebPages.AddNewPersonagePage;
import WebPages.HomePage;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GameGnomClassTest {

    private WebDriver driver;


    private String testName = "Тестовый персонаж";


    @Before
    public void openBrowser() {


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException, InterruptedException {

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement mainMenuButton = driver.findElement(By.xpath("//a[text()='Главное меню']"));
        mainMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Мои персонажи']"))));
        WebElement myCharactersButton = driver.findElement(By.xpath("//a[text()='Мои персонажи']"));
        myCharactersButton.click();
        driver.switchTo().alert().accept();
        WebElement moreForCharacterButton = driver.findElement(By.xpath("//table//tr[td/a[contains(text(), '" + testName + "')]]/td[2]//button"));
        moreForCharacterButton.click();
        //there is russian symbol х
        WebElement xDellButton = driver.findElement(By.xpath("//table//tr[td/a[contains(text(), '" + testName + "')]]/td[2]//a[contains(text(), 'х')]"));
        xDellButton.click();
        driver.close();
    }



    @Test
    public void gameTest() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(driver);
        assertEquals("The page title should e qual Google at the start of the test.", "Вход", driver.getTitle());
        homePage.login();

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).
                ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);



        AddNewPersonagePage addNewPersonagePage = new AddNewPersonagePage(driver);
        addNewPersonagePage.AddCharacter();



        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Тестовый персонаж']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[p/strong[text()='Раса:']]/following-sibling::div/p"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[p/strong[text()='Очки опыта:']]/following-sibling::div/div/p/strong"))));

        assertEquals("Name should be \"Тестовый персонаж\"", testName,
                driver.findElement(By.xpath("//h3")).getText());
        assertEquals("Расса персонажа должна быть \"Гном\"", "гном"
                , driver.findElement(By.xpath("//div[p/strong[text()='Раса:']]/following-sibling::div/p")).getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть 200", "200"
                , driver.findElement(By.xpath("//div[p/strong[text()='Очки опыта:']]/following-sibling::div/div/p/strong")).getText().toLowerCase());
    }
}
