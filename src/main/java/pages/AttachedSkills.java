package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;

public class AttachedSkills extends BasePage{

    public static final String fieldNameLocator = "#name";
    public static final String btnAddSkill = "//a[contains(text(),'Добавить')]";//".link-underlined.link-blue";
    public static final String checkBoxDifficultLocator = "//label[.='Сложный']";
    public static final String checkBoxTheoreticalLocator = "//label[.='Теоретический']";
    public static final String checkBoxDefaultLocator = "//label[.='По умолчанию']";
    public static final String btnAddOnAttachedSkillForm = "//button[contains(text(),'Добавить')]";
    public static final String getSkillPopup = "//*[contains(text(),'" + TestData.SKILL_NAME + "')]";
    public static final String btnRemoveSkill = ".btn.delete";
    public static final String btnDeleteConfirm = ".swal2-confirm.swal2-styled";

    @FindBy (xpath = btnAddSkill)
    private WebElement addSkill;

    @FindBy (css = fieldNameLocator)
    private WebElement addSkillName;

    @FindBy (css = "#description")
    private WebElement addSkillDescription;

    @FindBy (css = btnRemoveSkill)
    private WebElement removeAttachedSkill;

    @FindBy (css = btnDeleteConfirm)
    private WebElement confirmDelete;

    @FindBy (xpath = getSkillPopup)
    private WebElement elSkillPopup;

    @FindBy (xpath = checkBoxDifficultLocator)
    private WebElement difficultCheckBox;

    @FindBy (xpath = checkBoxTheoreticalLocator)
    private WebElement theoreticalCheckBox;

    @FindBy (xpath = checkBoxDefaultLocator)
    private WebElement defaultCheckBox;

    @FindBy (xpath = btnAddOnAttachedSkillForm)
    private WebElement elBtnAddAttachedSkillForm;

    public void setAddSkill(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.btnAddSkill)));
        addSkill.click();
    }

    public void clickRemoveAttachedSkill(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(this.btnRemoveSkill)));
        removeAttachedSkill.click();
    }

    public void clickConfirmDelete(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(this.btnDeleteConfirm)));
        confirmDelete.click();
    }

    private String []checkBoxes = {"#difficultSkill", "#defaultSkill", "#theoreticalSkill"};
    private boolean chbStatus(String locator){return driver.findElement(By.cssSelector(locator)).isSelected();}

    public void isChecked(){
        for(int i = 0; i < checkBoxes.length; i++)
            assertEquals("Чек-бокс не активирован", this.chbStatus(checkBoxes[i]), true);
    }

    public void isUnchecked(){
        for(int i = 0; i < checkBoxes.length; i++)
            assertEquals("Чек-бокс активирован", this.chbStatus(checkBoxes[i]), false);
    }

    public void setDifficultCheckBox(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.checkBoxDifficultLocator)));
        difficultCheckBox.click();
    }

    public void clickOnSkill(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.getSkillPopup)));
        elSkillPopup.click();
    }

    public void verifyDataRemoved(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.getSkillPopup)));
    }

    public void setTheoreticalCheckBox(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.checkBoxTheoreticalLocator)));
        theoreticalCheckBox.click();
    }
    public void setDefaultCheckBox(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.checkBoxDefaultLocator)));
        defaultCheckBox.click();
    }

    public void setAddSkillName(String name){
        addSkillName.sendKeys(name);
    }

    public void setAddSkillDescription(String name){
        addSkillDescription.sendKeys(name);
    }

    public void clickAddBtnOnAttachedSkillsForm(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.btnAddOnAttachedSkillForm)));
        elBtnAddAttachedSkillForm.click();
    }

    public AttachedSkills (WebDriver webDriver){
        super(webDriver);
    }
}
