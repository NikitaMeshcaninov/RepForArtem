import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AddingAndRemovingSkillForManager {

    private static WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {

        PersonageListPage personageListPage = logining();
        Thread.sleep(TestData.timeSleep);
        verifyUserRole(personageListPage);
        PersonageCreatePage personagePage = new PersonageCreatePage(driver);
        Thread.sleep(TestData.timeSleep);
        AttachedSkills attachedSkills = openAttachSkillPage(personagePage);
        waitForAttachSkillPage();
        Thread.sleep(TestData.timeSleep);
        setNewSkill(attachedSkills);
        checkedCheckBoxes(attachedSkills);
        Thread.sleep(TestData.timeSleep);
        clickAddButtonOnAttachSkillForm(attachedSkills);
        Thread.sleep(TestData.timeSleep);
        waitForNewSkillLoading();
        deleteSkill(attachedSkills);

        driver.close();
    }

    private Wait<WebDriver> waiter(){
        return new WebDriverWait(driver, 15);
    }

    private PersonageListPage logining(){
        LoginPage loginPage = new LoginPage(driver);
        assertEquals("The page should enter page to game site", "Login", driver.getTitle());
        System.out.println(TestData.MASTER_LOGIN + " " + "Залогинен");
        return loginPage.login(TestData.MASTER_LOGIN);
    }

    private void verifyUserRole (PersonageListPage personageListPage){
        waiter().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.=' " + TestData.MASTER_LOGIN + "']")));
        assertEquals("Роль данного игрока неверна", TestData.MASTER_ROLE, personageListPage.getPlayerRole());

    }

    /*private void sleep(){
        try {
            Thread.sleep(TestData.timeSleep);
        }catch(InterruptedException ie) {System.out.println("InterruptedException");}
    }*/

    private AttachedSkills openAttachSkillPage(PersonageCreatePage personagePage){
        personagePage.waitForMainMenu();
        waiter().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BasePage.attachedSkillsLocator)));
        System.out.println("Открываем главное меню -> Прикрепленные навыки");
        return personagePage.goToAttachedSkills();
    }

    private void waitForAttachSkillPage(){
        System.out.println("Ожидание загрузки страницы 'Прикрепленные навыки'");
        waiter().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3" + BasePage.attachedSkillsLocator)));
    }

    private void setNewSkill(AttachedSkills attachedSkills){
        addNewAttachSkill(attachedSkills);
        waitForSkill(attachedSkills);
        setSkillNameAndDescription(attachedSkills);
    }

    private void addNewAttachSkill(AttachedSkills attachedSkills){
        System.out.println("Добавляем новый Прикрепленный навык");
        attachedSkills.setAddSkill();
    }

    private void waitForSkill(AttachedSkills attachedSkills){
        System.out.println("Вводим название навыка");
        waiter().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AttachedSkills.fieldNameLocator)));
    }

    private void setSkillNameAndDescription(AttachedSkills attachedSkills){
        System.out.println("Вводим параметры" + " " + TestData.SKILL_NAME + " " + "и" + " " + TestData.SKILL_DESCRIPTION);
        attachedSkills.setAddSkillName(TestData.SKILL_NAME);
        attachedSkills.setAddSkillDescription(TestData.SKILL_DESCRIPTION);
    }

    private void checkedCheckBoxes(AttachedSkills attachedSkills){
        attachedSkills.isUnchecked();
        System.out.println("Активируем все чек-боксы");
        attachedSkills.setDifficultCheckBox();
        attachedSkills.setTheoreticalCheckBox();
        attachedSkills.setDefaultCheckBox();
        attachedSkills.isChecked();
    }

    private void clickAddButtonOnAttachSkillForm(AttachedSkills attachedSkills){
        System.out.println("Нажимаем на кнопку 'Добавить'");
        attachedSkills.clickAddBtnOnAttachedSkillsForm();
    }

    private void waitForNewSkillLoading(){
        System.out.println("Ожидание загрузки страницы 'Прикрепленные навыки'");
        waiter().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AttachedSkills.getSkillPopup)));
        driver.navigate().refresh();
    }

    private void deleteSkill(AttachedSkills attachedSkills){
        System.out.println("Удаляем тестовый скилл" + " " + TestData.SKILL_NAME);
        attachedSkills.clickOnSkill();
        attachedSkills.clickRemoveAttachedSkill();
        attachedSkills.clickConfirmDelete();
        attachedSkills.verifyDataRemoved();
    }

    @AfterClass
    public static void driverQuit(){
        driver.quit();
    }
}
