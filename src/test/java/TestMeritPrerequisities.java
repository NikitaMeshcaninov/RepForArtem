import base.BaseErilonTest;
import org.junit.Before;
import pages.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import steps.personage_steps.CreatePersonageSteps;

import static org.junit.Assert.*;

public class TestMeritPrerequisities extends BaseErilonTest {

    private CreatePersonageSteps createPersonageSteps;

    private static final Logger LOGGER = Logger.getLogger(TestMeritPrerequisities.class);

    @Override
    @Before
    public void preConditions() {
        super.preConditions();
        homePage.login(TestData.USER_LOGIN);
        createPersonageSteps = new CreatePersonageSteps(getDriver());
    }

    @Test
    public void gameTest() {
        createPersonageAndCheckData();
        checkNonAvailableMerit();
        setMeritPrerequisities();
        checkAvailableMeritAndSubmit();
        checkMeritAdded();
        deleteOneOfPrerequisities();
        checkMeritDeleted();
    }

    @After
    public void postConditions() {
        createPersonageSteps.deletePersonages(TestData.PERSONAGE_NAME);
        getDriver().close();
    }

    private void createPersonageAndCheckData() {
        createPersonageSteps.createPersonage(TestData.PERSONAGE_NAME, TestData.RACE, TestData.XP);

        assertEquals("Personage name should be " + TestData.PERSONAGE_NAME, TestData.PERSONAGE_NAME,
                createPersonageSteps.getPersonageName());
        assertEquals("Race of personage should be " + TestData.RACE.toLowerCase(), TestData.RACE,
                createPersonageSteps.getPersonageRace());
        assertEquals("Amount of personage experience should be " + TestData.XP, TestData.XP,
                createPersonageSteps.getPersonageExperience());
    }

    private void checkNonAvailableMerit() {
        createPersonageSteps.openMeritsList();
        createPersonageSteps.selectMerit(TestData.MERIT);

        assertEquals("Warning should be present", "Требования не выполнены!",
                createPersonageSteps.getMeritWarningMessage());
        assertFalse("Button should not be active",
                createPersonageSteps.isMeritSubmitAvailable());

        createPersonageSteps.closeAddMeritDialog();
    }

    private void setMeritPrerequisities() {
        createPersonageSteps.openCharacteristics();
        LOGGER.info("Increase strength and vitality");
        createPersonageSteps.increaseStrength(2);
        createPersonageSteps.increaseVitality(3);
    }

    private void checkAvailableMeritAndSubmit() {
        createPersonageSteps.openMeritsList();
        createPersonageSteps.selectMerit(TestData.MERIT);

        assertFalse("No warning should be present", createPersonageSteps.isMeritWarningMessageVisible());
        assertTrue("Button should be active", createPersonageSteps.isMeritSubmitAvailable());

        createPersonageSteps.submitAddMeritDialog();
    }

    private void checkMeritAdded() {
        assertTrue("Character should have merit " + TestData.MERIT,
                createPersonageSteps.personageHasMerit(TestData.MERIT));

        createPersonageSteps.savePersonageAndRefresh();
        createPersonageSteps.openMeritsList();

        assertTrue("Character should have merit " + TestData.MERIT,
                createPersonageSteps.personageHasMerit(TestData.MERIT));
    }

    private void deleteOneOfPrerequisities() {
        createPersonageSteps.openCharacteristics();
        LOGGER.info("Decrease strength");
        createPersonageSteps.decreaseStrength(1);
    }

    private void checkMeritDeleted() {
        createPersonageSteps.openMeritsList();

        assertFalse("Character should have merit " + TestData.MERIT,
                createPersonageSteps.personageHasMerit(TestData.MERIT));

        createPersonageSteps.savePersonageAndRefresh();
        createPersonageSteps.openMeritsList();

        assertFalse("Character should have merit " + TestData.MERIT,
                createPersonageSteps.personageHasMerit(TestData.MERIT));
    }
}
