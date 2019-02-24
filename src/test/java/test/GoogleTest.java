package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GoogleTest {

    private static final String googleURL = "https://google.com";
    private WebDriver driver;

    @After
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void chromeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(googleURL);
    }

    @Test
    public void fireFoxTest() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(googleURL);
    }

    @Test
    public void edgeTest() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get(googleURL);
    }

    @Test
    public void ieTest() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        driver.get(googleURL);
    }
}
