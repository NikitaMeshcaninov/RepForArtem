import org.junit.After;
import org.junit.AfterClass;
import pages.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AddingAndRemovingSkillForManager {

    private static WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 15);

        //logining
        LoginPage loginPage = new LoginPage(driver);
        assertEquals("The page should enter page to game site", "Login", driver.getTitle());
        System.out.println(TestData.MASTER_LOGIN + " " + "Залогинен");
        PersonageListPage personageListPage = loginPage.login(TestData.MASTER_LOGIN);
        Thread.sleep(TestData.timeSleep);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.=' " + TestData.MASTER_LOGIN + "']")));
        assertEquals("Роль данного игрока неверна", TestData.MASTER_ROLE, personageListPage.getPlayerRole());

        PersonageCreatePage personagePage = new PersonageCreatePage(driver);
        Thread.sleep(TestData.timeSleep);
        personagePage.waitForMainMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BasePage.attachedSkillsLocator)));
        System.out.println("Открываем главное меню -> Прикрепленные навыки");
        AttachedSkills attachedSkills = personagePage.goToAttachedSkills();

        System.out.println("Ожидание загрузки страницы 'Прикрепленные навыки'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3" + BasePage.attachedSkillsLocator)));

        Thread.sleep(TestData.timeSleep);
        System.out.println("Добавляем новый Прикрепленный навык");
        attachedSkills.setAddSkill();

        System.out.println("Вводим название навыка");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AttachedSkills.fieldNameLocator)));

        System.out.println("Вводим параметры" + " " + TestData.SKILL_NAME + " " + "и" + " " + TestData.SKILL_DESCRIPTION);
        attachedSkills.setAddSkillName(TestData.SKILL_NAME);
        attachedSkills.setAddSkillDescription(TestData.SKILL_DESCRIPTION);

        attachedSkills.isUnchecked();
        System.out.println("Активируем все чек-боксы");
        attachedSkills.setDifficultCheckBox();
        attachedSkills.setTheoreticalCheckBox();
        attachedSkills.setDefaultCheckBox();
        attachedSkills.isChecked();

        Thread.sleep(TestData.timeSleep);
        System.out.println("Нажимаем на кнопку 'Добавить'");
        attachedSkills.clickAddBtnOnAttachedSkillsForm();

        System.out.println("Ожидание загрузки страницы 'Прикрепленные навыки'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AttachedSkills.getSkillPopup)));
        driver.navigate().refresh();

        System.out.println("Удаляем тестовый скилл" + " " + TestData.SKILL_NAME);
        attachedSkills.clickOnSkill();
        attachedSkills.clickRemoveAttachedSkill();
        attachedSkills.clickConfirmDelete();
        attachedSkills.verifyDataRemoved();

        driver.close();
    }

    @AfterClass
    public static void driverQuit(){
        driver.quit();
    }
}
