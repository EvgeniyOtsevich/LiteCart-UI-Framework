package test;

import config.Config;
import org.junit.After;
import org.junit.Test;
import pages.admin.HomePage;
import pages.admin.LoginPage;
import pages.store.LandingPage;
import pages.store.ProductPage;
import utilities.BrowsersDriver;

import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.assertTrue;

public class LeftMenuNavigationTest extends BrowsersDriver {

    private LoginPage loginPage;
    private HomePage adminHomePage;

    @Test
    public void subMenuNavigation(){
        loginPage = new LoginPage(driver);
        adminHomePage = loginPage.login(Config.get().getAdminName(), Config.get().getAdminPass());

        IntStream.range(0, adminHomePage.getSideMenuItemsSize()).forEach(i -> {
            adminHomePage.openMenuItemByIndex(i);
            assertThat(adminHomePage.isPageContainH1()).isTrue();
            
            if (adminHomePage.getSubMenuItemsSize() > 0){
                IntStream.range(0, adminHomePage.getSubMenuItemsSize()).forEach(y -> {
                    adminHomePage.openSubMenuItemByIndex(y);
                    assertThat(adminHomePage.isPageContainH1()).isTrue();
                });
            }
        });
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
