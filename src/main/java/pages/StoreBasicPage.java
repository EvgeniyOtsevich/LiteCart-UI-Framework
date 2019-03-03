package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.store.CheckoutPage;
import pages.store.LandingPage;

public abstract class StoreBasicPage extends InitPage {

    @FindBy(css = "i.fa.fa-home")
    protected WebElement homeTitle;

    @FindBy(css = "#cart .quantity")
    protected static WebElement cartQuantity;

    protected StoreBasicPage(WebDriver driver) {
        super(driver);
    }

    public LandingPage goToHome(){
        homeTitle.click();
        return new LandingPage(driver);
    }

    public static int getCartQuantity(){
        return Integer.parseInt(cartQuantity.getText());
    }

    public CheckoutPage goToCheckout(){
        cartQuantity.click();
        return new CheckoutPage(driver);
    }

}
