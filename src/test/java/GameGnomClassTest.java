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
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

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
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshot.png");
        driver.close();
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {
        assertEquals("The page title should e qual Google at the start of the test.", "Вход", driver.getTitle());
        WebElement enterField = driver.findElement(By.name("form-username"));
        enterField.sendKeys("shmublon");
        Thread.sleep(500);
        WebElement enterButton = driver.findElement(By.className("btn"));
        enterButton.click();
        Thread.sleep(500);
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Тестовый персонаж");
        Select raceSelector = new Select(driver.findElement(By.id("race_id")));
        raceSelector.selectByVisibleText("Гном");
        WebElement experienceField = driver.findElement(By.id("experience"));
        experienceField.sendKeys("200");
        Thread.sleep(500);
        WebElement addCharacterButton = driver.findElement(By.className("btn"));
        addCharacterButton.click();
        Thread.sleep(15000);

        assertEquals("Name should be \"Тестовый персонаж\"","тестовый персонаж",
                driver.findElement(By.xpath("/html/body/div/h3")).getText().toLowerCase());
        assertEquals("Расса персонажа должна быть \"Гном\"", "гном"
                , driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[2]/p")).getText().toLowerCase());
        assertEquals("Опыт персонажа должна быть 200", "200"
                , driver.findElement(By.xpath("/html/body/div/form/div/div[2]/div/p/strong")).getText().toLowerCase());
    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}
