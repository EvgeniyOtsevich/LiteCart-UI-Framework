package pages.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.StoreBasicPage;

import java.util.List;
import java.util.stream.IntStream;

public class CheckoutPage extends StoreBasicPage {

    @FindAll(@FindBy(css = ".shortcuts a"))
    private List<WebElement> itemsShortcutsList;

    @FindBy(css = "button[name=remove_cart_item]")
    private WebElement removeButton;

    @FindBy(css = "div#checkout-cart-wrapper em")
    private WebElement emptyTableMessage;

    @FindAll(@FindBy(css = "table.dataTable.rounded-corners tr:not(.header) td.item"))
    private List<WebElement> orderTableRows;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage removeFirstItem(){
        int itemsSize = orderTableRows.size();
        wait.until(ExpectedConditions.visibilityOf(removeButton));
        removeButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("table.dataTable.rounded-corners tr:not(.header) td.item"), itemsSize));
        return this;
    }

    public CheckoutPage removeAllItems(){
        IntStream.range(0, orderTableRows.size()).forEach(i -> removeFirstItem());
        return this;
    }

    public boolean verifyCartIsEmpty(){
        wait.until(ExpectedConditions.visibilityOf(emptyTableMessage));
        return emptyTableMessage.isDisplayed();
    }
}
