package WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Nikita on 18.07.2016.
 */
public class AddNewPersonagePage {
    private WebDriver driver;
    private static String PageURL = "http://erilon-staging.herokuapp.com/views/user_personage_manager.html?id=1";
    @FindBy(how = How.XPATH, using = ".//*[@id='name']")
    private WebElement nameField;
    @FindBy(how = How.XPATH, using = ".//*[@id='race_id']")
    private Select raceSelector;
    @FindBy(how = How.XPATH, using = ".//*[@id='experience']")
    private WebElement experienceField;
    @FindBy(how = How.XPATH, using = "\"//button[@ng-click='createPersonage()']")
    WebElement addCharacterButton;

    public AddNewPersonagePage(WebDriver driver){
        this.driver = driver;
        driver.get(PageURL);
        PageFactory.initElements(driver, this);
    }

    public void AddCharacter(){
        nameField.sendKeys("shmublon");
        raceSelector.selectByVisibleText("Гном");
        experienceField.sendKeys("200");
        addCharacterButton.click();
    }
}
