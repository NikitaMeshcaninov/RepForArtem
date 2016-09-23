package base;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import utils.Timeout;

import java.util.concurrent.TimeUnit;

public class BaseErilonTest {
    private WebDriver driver;
    protected HomePage homePage;

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void preConditions() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }
}
