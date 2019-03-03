package pages.admin;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.InitPage;

public class LoginPage extends InitPage {

    @FindBy(css = "input[name=username]")
    private WebElement usernameField;

    @FindBy(css = "input[name=password]")
    private WebElement usernamePass;

    @FindBy(css = "button[name=login]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(Config.get().getHostName() + "/admin");
    }

    private LoginPage setUsername(String username){
        usernameField.sendKeys(username);
        return this;
    }

    private LoginPage setPassword(String password){
        usernamePass.sendKeys(password);
        return this;
    }

    public HomePage login(String username, String password){
        setUsername(username);
        setPassword(password);
        loginButton.click();
        return new HomePage(driver);
    }
}
