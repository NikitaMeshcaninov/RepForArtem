package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikita on 19.07.2016.
 */
public class PersonagePage {
    private WebDriver driver;
    private static String PageURL = "http://erilon-staging.herokuapp.com/views/view_personage.html?id=105";
    @FindBy(xpath = "//a[text()='Главное меню']")
    private WebElement mainMenuButton;
    @FindBy(xpath = "//a[text()='Мои персонажи']")
    private WebElement myPersonagesButton;

    public PersonagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openMinMenu(){
        mainMenuButton.click();
    }

    public void goToPersonageListPage(){
        myPersonagesButton.click();
    }

}
