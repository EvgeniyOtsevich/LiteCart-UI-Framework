package pages.store;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.StoreBasicPage;

import java.util.List;

public class ProductPage extends StoreBasicPage {

    @FindBy(css = "button[name=add_cart_product]")
    private WebElement addToCartButton;

    @FindBy(css = "input[name=quantity]")
    private WebElement productQuantity;

    @FindAll(@FindBy(css = ".options > select"))
    private List<WebElement> sizeSelect;

    protected ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart(){
        if (sizeSelect.size() > 0) {
            Select size = new Select(sizeSelect.get(0));
            size.selectByValue("Small");
        }
        int oldQuantity = getCartQuantity();
        addToCartButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(cartQuantity, String.valueOf(oldQuantity))));
        return this;
    }

//    public ProductPage addToCart(int quantity){
//        int oldQuantity = getCartQuantity();
//        setQuantity(quantity);
//        addToCartButton.click();
//        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(cartQuantity, String.valueOf(oldQuantity))));
//        return this;
//    }

    public ProductPage setQuantity(int quantity){
        productQuantity.clear();
        productQuantity.sendKeys(String.valueOf(quantity));
        return this;
    }


}
