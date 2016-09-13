import WebPages.*;
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


/**
 * Created with IntelliJ IDEA.
 * User: A Timochko
 * Date: 21.08.16
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class AddingAndRemovingSkillForManager {

    private WebDriver driver;


    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void gameTest() throws IOException, InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);

        HomePage homePage = new HomePage(driver);
        assertEquals("The page should enter page to game site", "Вход", driver.getTitle());
        System.out.println(SettingsForTest.MANAGERNAME + " " + "Залогинен");
        PersonageListPage personageListPage = homePage.login(SettingsForTest.MANAGERNAME);
        Thread.sleep(500);
        //Добавить вейтер, не всегда успевает загрузиться
        assertEquals("Роль данного игрока неверна", SettingsForTest.MASTER_ROLE, personageListPage.getPlayerRole());

        PersonageCreatePage personagePage = new PersonageCreatePage(driver);
        Thread.sleep(500);
        personagePage.openMainMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='attached_skills_mm']")));
        //Вообще-то это веб элемент: attachedSkillsButton. Можно ли его иначе указать?
        System.out.println("Открываем главное меню -> Прикрепленные навыки");
        AttachedSkills attachedSkills = personagePage.goToAttachedSkills();
        System.out.println("Ожидание перед нажатием кнопки 'Добавить навык' пока не исчезнет лоадер");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loader']")));
        System.out.println("Создаём новый Прикрепленный навык");
        attachedSkills.setAddSkill();
        System.out.println("Проверяем что кнопка 'Сохранить' неактивна");
        assertEquals("Нет, неактивна", false, attachedSkills.getDisabledSaveButton().isEnabled());
        System.out.println("Вводим название навыка");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-dialog")));
        attachedSkills.setAddSkillName(SettingsForTest.SKILL_NAME);
        Thread.sleep(2000);
        //иногда тут отваливается написание имени, не успевает внести, спросить у Артёма как лучше обработать вейтером
        System.out.println("Проверяем что кнопка 'Сохранить' активна");
        assertTrue("Нет не активна", attachedSkills.getEnabledSavebutton().isEnabled());
        attachedSkills.setAddSkillDescription(SettingsForTest.SKILL_DESCRIPTION);
        Thread.sleep(2000);
        System.out.println("Вводим параметры" + " " + SettingsForTest.SKILL_NAME + " " + "и" + " " + SettingsForTest.SKILL_DESCRIPTION);
        attachedSkills.setDifficultSlider();
        attachedSkills.setTheoreticalSlider();
        attachedSkills.setSpellsSlider();
        wait.until(ExpectedConditions.visibilityOf(attachedSkills.getEnabledSavebutton()));
        attachedSkills.setEnabledSavebutton();
        assertEquals("Сл. не выбрана", true, attachedSkills.getDifficultCheckboxEnabled().isEnabled());
        assertEquals("Теор. не выбрана", true, attachedSkills.getTheoreticalCheckboxEnabled().isEnabled());
        //assertEquals("Деф. выбрана", false, attachedSkills.getDifficultCheckboxEnabled().isEnabled());
        System.out.println("Нажимаем на кнопку еще");
        attachedSkills.setMoreButton();
        System.out.println("Нажимаем на кнопку Х и удаляем" + " " + SettingsForTest.SKILL_NAME);
        attachedSkills.setRemoveAttachedSkillX();













        }



        }
