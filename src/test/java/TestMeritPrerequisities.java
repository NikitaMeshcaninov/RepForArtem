import base.BaseErilonTest;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import steps.personage_steps.CreatePersonageSteps;

import static org.junit.Assert.*;

public class TestMeritPrerequisities extends BaseErilonTest {

    @Test
    public void gameTest() throws InterruptedException {
        try {
            getDriver().get("https://ru.bongacams.com/Effy_S");
            getDriver().findElement(By.xpath("//div[@id='header_login']//a")).click();
            getDriver().findElement(By.id("header_log_in_log_in_username")).sendKeys("shmublon");
            getDriver().findElement(By.id("header_log_in_log_in_password")).sendKeys("dexter1984");
            getDriver().findElement(By.xpath("//button[@class='bt35 bt35_green btn_blue']")).click();
            tip(1, getDriver());
        } finally {
            getDriver().quit();
        }
    }

    public static void tip(int amount, WebDriver driver) throws InterruptedException {
        for (int i = 0; i < amount; i++) {
            driver.findElement(By.id("bTip")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//label[text()='Другое количество']")).click();
            driver.findElement(By.id("tip_field_enter_amount")).clear();
            driver.findElement(By.id("tip_field_enter_amount")).sendKeys("1");
            driver.findElement(By.xpath("//div[@class='form_actions']/button")).click();
            Thread.sleep(500);
        }
    }
}
