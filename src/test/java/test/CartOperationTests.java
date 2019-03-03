package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.store.CheckoutPage;
import pages.store.LandingPage;
import pages.store.ProductPage;
import utilities.BrowsersDriver;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static pages.StoreBasicPage.getCartQuantity;

public class CartOperationTests extends BrowsersDriver {

    private LandingPage landingPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @Test
    public void subMenuNavigation() {
        landingPage = new LandingPage(driver);
        IntStream.range(0, 3).forEach(i -> {
            productPage = landingPage.openProduct(i).addToCart();
            landingPage = productPage.goToHome();
        });
        checkoutPage = productPage.goToCheckout();
        checkoutPage.removeAllItems();
        assertThat(checkoutPage.verifyCartIsEmpty()).isTrue();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
