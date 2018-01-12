package pages;

import base.PageObject;
import base.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends PageObject {

    public static final String attachedSkillsLocator = "//span[contains(text(),'Прикрепленные навыки')]";

    @FindBy(xpath = attachedSkillsLocator + "/..")
    private WebElement attachedSkillsButton;

    @FindBy(css = ".left>div>div>div")
    private WebElement playerRole;

    private String mainMenuSection = ".left-menu-inner";

    @FindBy(id = "my_personages")   //span[contains(text(),'Персонажи')]
    private WebElement myPersonagesButton;

    @FindBy(id = "loader")
    private WebElement loader;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade loader() {
        return element(loader);
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

    final Wait<WebDriver> wait = new WebDriverWait(driver, 15);

    public void waitForMainMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(this.mainMenuSection)));
    }

    public void waitForElementVisibleByXpath(String locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void waitForElementVisibleByCss(String locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
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
