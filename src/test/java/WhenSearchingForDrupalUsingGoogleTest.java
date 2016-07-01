package test.java;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WhenSearchingForDrupalUsingGoogleTest {
    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/?gfe_rd=cr&ei=Rz92V-qcCtPCtAG33KvoCg&gws_rd=ssl");
        screenshotHelper = new ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshot.png");
        driver.close();
    }

    @Test
    public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException, InterruptedException {
        assertEquals("The page title should e qual Google at the start of the test.", "Вход", driver.getTitle());
        WebElement enterField = driver.findElement(By.name("form-username"));
        enterField.sendKeys("Шмублон");
        enterField.submit();
        Thread.sleep(500);

    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}