package test;

import config.Config;
import org.junit.After;
import org.junit.Test;
import pages.admin.*;
import pages.store.CheckoutPage;
import pages.store.LandingPage;
import pages.store.ProductPage;
import products.Product;
import utilities.BrowsersDriver;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static pages.AdminBasicPage.SubMenuItems.*;

public class AddNewProductTest extends BrowsersDriver {

    private LoginPage loginPage;
    private HomePage adminHomePage;
    private CatalogPage catalogPage;
    private LandingPage landingPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private CatalogPage.AddProductPage addProductPage;
    private CatalogPage.AddProductPage.GeneralTab generalTab;
    private CatalogPage.AddProductPage.PriceTab priceTab;

    @Test
    public void addNewProduct() {

        final Product coolDuck = new Product();

        loginPage = new LoginPage(driver);
        adminHomePage = loginPage.login(Config.get().getAdminName(), Config.get().getAdminPass());

        catalogPage = (CatalogPage) adminHomePage.openPage(CATALOG);
        addProductPage = catalogPage.addNewProduct();
        generalTab = addProductPage.switchTabGeneralTab();

        generalTab.enableProduct()
                .fillProductName(coolDuck.getName())
                .fillProductCode(coolDuck.getCode())
                .setProductQuantity(coolDuck.getQuantity())
                .uploadFile();

        priceTab = addProductPage.switchToPriceTab();
        priceTab.setPriceEUR(coolDuck.getPrice()).save();

        assertThat(catalogPage.getProducts()).contains(coolDuck.getName());

        landingPage = catalogPage.goToLandingPage();
        productPage = landingPage.openProduct(coolDuck.getName()).addToCart();
        landingPage = productPage.goToHome();

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
