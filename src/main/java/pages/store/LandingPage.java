package pages.store;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.StoreBasicPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LandingPage extends StoreBasicPage {

    @FindAll(@FindBy(css = "div#box-most-popular li > a:nth-of-type(1)"))
    private List<WebElement> mostPopularItemsList;

    @FindAll(@FindBy(css = "div#box-latest-products li > a:nth-of-type(1)"))
    private List<WebElement> latestProductItemsList;

    public LandingPage(WebDriver driver) {
        super(driver);
        driver.get(Config.get().getHostName());
    }

    public ProductPage openProduct(int index){
        mostPopularItemsList.get(index).click();
        return new ProductPage(driver);
    }

    public ProductPage openProduct(String name){
        List<String> productsNameList = latestProductItemsList.stream()
                .map(product -> product.findElement(By.cssSelector(".name")).getText())
                .collect(Collectors.toList());
        IntStream.range(0, productsNameList.size())
                .filter(i -> productsNameList.get(i).equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(i -> latestProductItemsList.get(i).click());
        return new ProductPage(driver);
    }
}
