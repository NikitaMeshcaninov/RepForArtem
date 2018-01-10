package steps.personage_steps;

import enums.Attribute;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.PersonageCreatePage;
import pages.PersonageListPage;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class CreatePersonageSteps {
    private static final boolean INCREASE = true;
    private static final boolean DECREASE = false;

    private PersonageListPage personageListPage;
    private PersonageCreatePage personageCreatePage;

    public CreatePersonageSteps(WebDriver driver) {
        personageListPage = PageFactory.initElements(driver, PersonageListPage.class);
        personageCreatePage = PageFactory.initElements(driver, PersonageCreatePage.class);
    }

    public PersonageCreatePage createPersonage(String name, String race, String experience) {
        personageListPage.fillName(name);
        personageListPage.selectRace(race);
        personageListPage.fillExperience(experience);
        return personageListPage.clickAddPersonageButton();
    }

    public void openMeritsList() {
        personageCreatePage.openSpecialQualities();
        personageCreatePage.openMerits();
    }

    public String getPersonageName() {
        return personageCreatePage.personageName().getText();
    }

    public String getPersonageRace() {
        return personageCreatePage.personageRace().getText();
    }

    public String getPersonageExperience() {
        return personageCreatePage.personageExperience().getAttribute("value");
    }

    public void deletePersonages(String name) {
        personageCreatePage.waitForMainMenu();
        personageListPage = personageCreatePage.goToPersonageListPage();

        while (personageListPage.isPersonagePresent(name)) {
            personageListPage.openMoreMenuForPersonage(personageListPage.getPersonageRow(name));
            personageListPage.deletePersonage(personageListPage.getPersonageRow(name));
            Utils.waitTimeOut(1, TimeUnit.SECONDS);
        }
    }

    public void selectMerit(String name) {
        personageCreatePage.clickAddMerit();
        personageCreatePage.selectMerit(name);
    }

    public String getMeritWarningMessage() {
        personageCreatePage.warningMessage().waitUntilVisible();
        return personageCreatePage.warningMessage().getText();
    }

    public boolean isMeritWarningMessageVisible() {
        return personageCreatePage.warningMessage().isVisible();
    }

    public boolean isMeritSubmitAvailable() {
        return personageCreatePage.addMeritDialogButton().isEnabled();
    }

    public void increaseStrength(int amount) {
        personageCreatePage.changeAttribute(Attribute.STRENGTH, INCREASE, amount);
    }

    public void increaseVitality(int amount) {
        personageCreatePage.changeAttribute(Attribute.VITALITY, INCREASE, amount);
    }

    public void decreaseStrength(int amount) {
        personageCreatePage.changeAttribute(Attribute.STRENGTH, DECREASE, amount);
        if (personageCreatePage.deleteMeritConfirmButton().isCurrentlyVisible()) {
            personageCreatePage.confirmMeritDeletion();
        }
    }

    public void closeAddMeritDialog() {
        personageCreatePage.closeAddMeritDialog();
    }

    public void openCharacteristics() {
        personageCreatePage.openCharacteristics();
    }

    public void submitAddMeritDialog() {
        personageCreatePage.submitAddMeritDialog();
    }

    public void savePersonageAndRefresh() {
        personageCreatePage.savePersonage();
        personageCreatePage.refresh();
    }

    public boolean personageHasMerit(String name) {
        return personageCreatePage.hasMerit(name);
    }
}
