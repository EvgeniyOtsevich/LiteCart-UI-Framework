package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class InitPage {

    private final int TIME_OUT_SEC = 5;
    private final int SLEEP_MILLIS = 1000;
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected InitPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, TIME_OUT_SEC, SLEEP_MILLIS);
    }
}
