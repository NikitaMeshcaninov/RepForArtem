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
    @FindBy(xpath = ".//*[@id='name']")
    private WebElement nameField;
    @FindBy(xpath = ".//*[@id='race_id']")
    private WebElement raceSelector;
    @FindBy(xpath = ".//*[@id='race_id']/option[3]")
    private WebElement raceSelectorGnom;
    @FindBy(xpath = ".//*[@id='experience']")
    private WebElement experienceField;
    @FindBy(xpath = "//button[@ng-click='createPersonage()']")
    WebElement addCharacterButton;

    public AddNewPersonagePage(WebDriver driver){
        this.driver = driver;
        driver.get(PageURL);
        PageFactory.initElements(driver, this);
    }


    public void fillName(String name) {
        nameField.sendKeys(name);
    }

    public void selecRace(String raceName) {
        new Select(raceSelector).selectByValue(raceName);
    }
    public void fillexperience() {
        experienceField.sendKeys("200");
    }
    public void addCharacer() {
       addCharacterButton.click();
    }
}
