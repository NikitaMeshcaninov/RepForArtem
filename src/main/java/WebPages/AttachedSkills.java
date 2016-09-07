package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: A Timochko
 * Date: 21.08.16
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class AttachedSkills extends BasePage{

    @FindBy (xpath = "//button[@ng-click='showAddDialog()']")
    private WebElement addSkill;
    @FindBy (id = "attachedSkill_name")
    private WebElement addSkillName;
    @FindBy (xpath = "//textarea[@id='input_0']")
    private WebElement addSkillDescription;
    @FindBy (xpath = "//*[contains(text(),'Test Name')]/..//button/span[@class='caret']")
    private WebElement moreButton;
    @FindBy (xpath = "//a[contains(@ng-click, 'attachedSkill.id')]")
    private WebElement removeAttachedSkillX;
    @FindBy (xpath = "//md-switch[@ng-model='ctrl.difficult']")
    private WebElement difficultSlider;
    @FindBy (xpath = "//md-switch[@ng-model='ctrl.theoretical']")
    private WebElement theoreticalSlider;
    @FindBy (xpath = "//md-switch[@ng-model='ctrl.spells_connected']")
    private WebElement spellsSlider;

    @FindBy (xpath = "//*[contains(text(),'Test Name')]/..//div[@ng-if='attachedSkill.difficult']/span[contains(@class, 'glyphicon-ok')]")
    private WebElement difficultCheckboxEnabled;
    @FindBy (xpath = "//*[contains(text(),'Test Name')]/..//div[@ng-if='attachedSkill.theoretical']/span[contains(@class, 'glyphicon-ok')]")
    private WebElement theoreticalCheckboxEnabled;
    @FindBy (xpath = "//*[contains(text(),'Test')]/..//div[@ng-if='attachedSkill.default_skill']/span[contains(@class, 'glyphicon-ok')]")
    private WebElement defaultCheckboxEnabled;

   //Спросить артема о более адекетном Xpath, потому что это явный костыль описывать два состояния одной кнопки
    @FindBy (xpath = "//button[@ng-click='ctrl.save()' and @disabled='disabled']")
    private WebElement disabledSaveButton;
    @FindBy (xpath = "//button[@ng-click='ctrl.save()' and not(contains(@disabled, 'disabled'))]")
    private WebElement enabledSavebutton;

    public void setAddSkill(){
        addSkill.click();
    }
    public void setMoreButton(){
        moreButton.click();
    }
    public void setRemoveAttachedSkillX(){
        removeAttachedSkillX.click();
    }
    public void setDifficultSlider(){
        difficultSlider.click();
    }
    public void setTheoreticalSlider(){
        theoreticalSlider.click();
    }
    public void setSpellsSlider(){
        spellsSlider.click();
    }

    public void setAddSkillName(String name){
        addSkillName.sendKeys(name);
    }

    public void setAddSkillDescription(String name){
        addSkillDescription.sendKeys(name);
    }

    public WebElement getEnabledSavebutton(){
        return enabledSavebutton;
    }
    public void setEnabledSavebutton(){
        enabledSavebutton.click();
    }

    public WebElement getDisabledSaveButton(){
        return disabledSaveButton;
    }

    public WebElement getDifficultCheckboxEnabled(){
        return difficultCheckboxEnabled;
    }
    public WebElement getTheoreticalCheckboxEnabled(){
        return theoreticalCheckboxEnabled;
    }
    public WebElement getDefaultCheckboxEnabled(){
        return  defaultCheckboxEnabled;
    }

    public AttachedSkills (WebDriver webDriver){
        super(webDriver);
    }

}
