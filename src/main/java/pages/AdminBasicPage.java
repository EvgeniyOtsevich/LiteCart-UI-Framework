package pages;

import com.sun.xml.internal.ws.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.store.LandingPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AdminBasicPage extends InitPage {

    @FindAll(@FindBy(css = "ul#box-apps-menu > li > a"))
    private List<WebElement> sideMenuItemsList;

    @FindAll(@FindBy(css = "ul.docs a"))
    private List<WebElement> sideSubMenuItemsList;

    @FindBy(css = "a[title=Catalog]")
    private WebElement catalogIcon;

    protected AdminBasicPage(WebDriver driver) {
        super(driver);
    }

    public LandingPage goToLandingPage(){
        wait.until(ExpectedConditions.visibilityOf(catalogIcon));
        catalogIcon.click();
        return new LandingPage(driver);
    }

    public int getSideMenuItemsSize(){
        return sideMenuItemsList.size();
    }

    public int getSubMenuItemsSize(){
        return sideSubMenuItemsList.size();
    }

    public Object openPage(SubMenuItems pageName) {
        getMenuElement(pageName).click();
        try {
            return Class.forName("pages.admin." + StringUtils.capitalize(pageName.getPageNameText()).replaceAll("\\s+","")+"Page")
                    .getConstructor(Class.forName("org.openqa.selenium.WebDriver"))
                    .newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private WebElement getMenuElement(SubMenuItems menuItem) {
        return driver.findElement(By.xpath("//span[text()='"+menuItem.getPageNameText()+"']"));
    }

    public void openMenuItemByIndex(int index) {
        sideMenuItemsList.get(index).click();
    }

    public void openSubMenuItemByIndex(int index) {
        String oldURL = driver.getCurrentUrl();
        if (!sideSubMenuItemsList.get(index).getAttribute("href").equalsIgnoreCase(oldURL)) {
            sideSubMenuItemsList.get(index).click();
        }
    }

    public boolean isPageContainH1(){
        return driver.findElement(By.tagName("h1")).isEnabled();
    }

    public enum SubMenuItems {
        CATALOG("Catalog"), COUNTRIES("Countries"), CURRENCIES("Currencies"),
        CUSTOMERS("Customers"), GEO_ZONES("Geo Zones"), LANGUAGES("Languages"),
        MODULES("Modules"), ORDERS("Orders"), PAGES("Pages"), REPORTS("Reports"),
        SETTINGS("Settings"), SLIDES("Slides"), TAX("Tax"),
        TRANSLATIONS("Translations"), USERS("Users"), VQMODS("vQmods");

        String pageName;

        SubMenuItems(String pageName) {
            this.pageName = pageName;
        }

        private String getPageNameText(){
            return pageName;
        }
    }
}
