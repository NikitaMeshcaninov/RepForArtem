package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[h4]/following-sibling::div[1]")
    private WebElement playerRole;

    @FindBy(id = "main_menu_link")
    private WebElement mainMenuButton;

    @FindBy(id = "my_personages")
    private WebElement myPersonagesButton;

    @FindBy(id = "loader")
    private WebElement loader;

    public WebElement getLoader() {
        return loader;
    }
    
    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    public void openMainMenu() {
        mainMenuButton.click();
    }

    public PersonageListPage goToPersonageListPage() {
        myPersonagesButton.click();
        getWebDriver().switchTo().alert().accept();
        return new PersonageListPage(getWebDriver());
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getPlayerRole() {
        return playerRole.getText().replaceAll("[^:]+:\\s*(.*)", "$1");
    }

}
