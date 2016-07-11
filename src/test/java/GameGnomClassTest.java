package test.java;

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
    private ScreenshotHelper screenshotHelper;


    @Before
    public void openBrowser() {


        driver = new ChromeDriver();
        driver.get("http://erilon-staging.herokuapp.com/");
        screenshotHelper = new GameGnomClassTest.ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException, InterruptedException {

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        screenshotHelper.saveScreenshot("screenshot.png");
        WebElement mainMenuButton = driver.findElement(By.xpath("//a[text()='Главное меню']"));
        mainMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Мои персонажи']"))));
        WebElement myCharactersButton = driver.findElement(By.xpath("//a[text()='Мои персонажи']"));
        myCharactersButton.click();
        driver.switchTo().alert().accept();
        WebElement moreForCharacterButton = driver.findElement(By.xpath("//tr[4]/td[2]/div/button"));
        moreForCharacterButton.click();
        WebElement xDellButton = driver.findElement(By.xpath("//tr[4]/td[2]/div/ul/li[3]/a"));
        xDellButton.click();
        driver.close();
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("The page title should e qual Google at the start of the test.", "Вход", driver.getTitle());
        WebElement enterField = driver.findElement(By.name("form-username"));
        enterField.sendKeys("shmublon");
        WebElement enterButton = driver.findElement(By.xpath("//button[@ng-click='login()']"));
        enterButton.click();
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Тестовый персонаж");
        Select raceSelector = new Select(driver.findElement(By.id("race_id")));
        raceSelector.selectByVisibleText("Гном");
        WebElement experienceField = driver.findElement(By.id("experience"));
        experienceField.sendKeys("200");
        WebElement addCharacterButton = driver.findElement(By.xpath("//button[@ng-click='createPersonage()']"));
        addCharacterButton.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Тестовый персонаж']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='Гном']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p/strong[text()='200']"))));

        assertEquals("Name should be \"Тестовый персонаж\"", "тестовый персонаж",
                driver.findElement(By.xpath("//h3[text()='Тестовый персонаж']")).getText().toLowerCase());
        assertEquals("Расса персонажа должна быть \"Гном\"", "гном"
                , driver.findElement(By.xpath("//p[text()='Гном']")).getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть 200", "200"
                , driver.findElement(By.xpath("//p/strong[text()='200']")).getText().toLowerCase());
    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}