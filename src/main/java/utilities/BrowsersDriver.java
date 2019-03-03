package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BrowsersDriver {

    protected WebDriver driver = returnDriver();

    private WebDriver returnDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new EventFiringWebDriver(new ChromeDriver());
            ((EventFiringWebDriver) driver).register(new EventsListener());
            driver.manage().window().maximize();
        }
        return driver;
    }
}
