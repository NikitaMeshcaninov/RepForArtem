package pages;

import base.PageObject;
import base.WebElementFacade;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends PageObject {
    @FindBy(id = "attached_skills_mm")
    private WebElement attachedSkillsButton;

    @FindBy(xpath = "//div[h4]/following-sibling::div[1]")
    private WebElement playerRole;

    @FindBy(id = "main_menu_link")
    private WebElement mainMenuButton;

    @FindBy(id = "my_personages")
    private WebElement myPersonagesButton;

    @FindBy(id = "loader")
    private WebElement loader;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade loader() {
        return element(loader);
    }

    public WebElementFacade mainMenuButton() {
        return element(mainMenuButton);
    }

    public WebElementFacade attachedSkillsButton() {
        return element(attachedSkillsButton);
    }

    public WebElementFacade myPersonagesButton() {
        return element(myPersonagesButton);
    }

    public WebElementFacade playerRole() {
        return element(playerRole);
    }

    public void openMainMenu() {
        mainMenuButton().click();
    }

    public PersonageListPage goToPersonageListPage() {
        myPersonagesButton().waitUntilVisible();
        myPersonagesButton().click();
        if (alertIsPresent()) {
            getAlert().accept();
        }
        return new PersonageListPage(getDriver());
    }

    public String getPlayerRole() {
        return playerRole().getText().replaceAll("[^:]+:\\s*(.*)", "$1");
    }

    public void waitForLoader() {
        loader().waitUntilPresent();
        try {
            loader().waitUntilVisible();
        } catch (TimeoutException e) {
            logger.warn("Timeout when waiting for loader");
        }
        loader().waitForInvisibility();
    }

}
