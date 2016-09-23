package pages;

import base.WebElementFacade;
import enums.Attribute;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Nikita on 19.07.2016.
 */
public class PersonageCreatePage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(PersonageCreatePage.class);

    @FindBy(xpath = "//h3")
    private WebElement personageName;

    @FindBy(xpath = "//div[p/strong[text()='Раса:']]/following-sibling::div/p")
    private WebElement personageRace;

    @FindBy(xpath = ".//*[@id='expirience']")
    private WebElement personageExperience;

    @FindBy(xpath = "//*[contains(text(),'Особенности')]")
    private WebElement specialQualities;

    @FindBy(xpath = "//div[contains(text(),'Достоинства')]")
    private WebElement meritsButton;

    @FindBy(xpath = "//*[@id='merits']/child::*/child::*/child::button")
    private WebElement addMeritButton;

    @FindBy(xpath = "//img[@src='/images/ic_close_24px.svg']/parent::*")
    private WebElement cancelAddMeritDialogButton;

    @FindBy(xpath = "//input [@placeholder='Введите название']")
    private WebElement meritAutocomplete;

    @FindBy(xpath = "//p[contains(text(), 'не выполнены')]")
    private WebElement warningMessage;

    @FindBy(xpath = "//md-dialog//span[contains(text(), 'Добавить')]/parent::*")
    private WebElement addMeritDialogButton;

    @FindBy(xpath = "//*[contains(text(), 'Характеристики')]")
    private WebElement characteristics;

    @FindBy(xpath = "//*[@ng-click= 'savePersonage()']")
    private WebElement saveButton;

    @FindBy(xpath = "//md-dialog")
    private WebElement addMeritDialog;

    @FindBy(xpath = "//div[contains(text(), 'Недостатки')]")
    private WebElement disadvantages;

    @FindBy(id = "merits")
    private WebElement merits;

    public WebElementFacade personageName() {
        return element(personageName);
    }

    public WebElementFacade meritsButton() {
        return element(meritsButton);
    }

    public WebElementFacade addMeritButton() {
        return element(addMeritButton);
    }

    public WebElementFacade addMeritDialog() {
        return element(addMeritDialog);
    }

    public WebElementFacade cancelAddMeritDialogButton() {
        return element(cancelAddMeritDialogButton);
    }

    public WebElementFacade meritAutocomplete() {
        return element(meritAutocomplete);
    }

    public WebElementFacade warningMessage() {
        return element(warningMessage);
    }

    public WebElementFacade addMeritDialogButton() {
        return element(addMeritDialogButton);
    }

    public WebElementFacade characteristics() {
        return element(characteristics);
    }

    public WebElementFacade saveButton() {
        return element(saveButton);
    }

    public WebElementFacade getAddWorthMenu() {
        return element(addMeritDialog);
    }

    public WebElementFacade disadvantages() {
        return element(disadvantages);
    }

    public WebElementFacade personageRace() {
        return element(personageRace);
    }

    public WebElementFacade personageExperience() {
        return element(personageExperience);
    }

    public WebElementFacade specialQualities() {
        return element(specialQualities);
    }

    public WebElementFacade merits() {
        return element(merits);
    }

    public PersonageCreatePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSpecialQualities() {
        LOGGER.info("Open special qualities");
        specialQualities().click();
    }

    public void openMerits() {
        if (isCollapsed(merits())) {
            LOGGER.info("Click merits button");
            meritsButton().waitUntilVisible();
            meritsButton().click();
        }
        addMeritButton().waitUntilVisible();
    }

    public boolean isCollapsed(WebElementFacade tab) {
        return tab.getAttribute("class").equals("panel-collapse collapse");
    }

    public void clickAddMerit() {
        addMeritButton().click();
    }

    public void closeAddMeritDialog() {
        LOGGER.info("Close add merit dialog");
        cancelAddMeritDialogButton().click();
        addMeritDialog().waitForInvisibility();
    }

    public void selectMerit(String name) {
        meritAutocomplete().selectAutocomplete(name);
    }

    public AttachedSkills goToAttachedSkills() {
        attachedSkillsButton().click();
        return new AttachedSkills(getDriver());
    }

    /**
     * @param direction if true - increase attribute esle decrease
     **/
    public void changeAttribute(Attribute attribute, boolean direction, int amount) {
        String directionXpath = direction ? "increase" : "decrease";
        WebElementFacade attributeChangeButton = element(getDriver().findElement(By.xpath("//*[text()='" +
                attribute.getName() + "']/following-sibling::*/child::*[@ng-click='" +
                directionXpath + "Attribute(personageAttribute.id)']")));
        attributeChangeButton.waitUntilVisible();
        for (int i = 0; i < amount; i++) {
            attributeChangeButton.click();
        }
    }

    public void openCharacteristics() {
        LOGGER.info("Open characteristics tab");
        characteristics().click();
    }

    public void submitAddMeritDialog() {
        LOGGER.info("Submit add merit dialog");
        addMeritDialogButton().click();
        addMeritDialog().waitForInvisibility();
    }

    public void savePersonage() {
        LOGGER.info("Save personage");
        saveButton().click();
        waitForLoader();
    }

    public boolean hasMerit(String merit) {
        return isElementCurrentlyPresent(
                By.xpath("//tr[@ng-repeat='personageMerit in personageMerits']/td[contains(text(), '"
                        + merit + "')]"));
    }

    public void refresh() {
        LOGGER.info("Refresh page");
        getDriver().navigate().refresh();
        if (alertIsPresent()) {
            getAlert().accept();
        }
        waitForLoader();
    }
}
