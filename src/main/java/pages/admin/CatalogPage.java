package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AdminBasicPage;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage extends AdminBasicPage {

    @FindBy(css = "a.button[href*=edit_product]")
    private WebElement addNewProductButton;

    @FindAll(@FindBy(css = ".dataTable .row"))
    private List<WebElement> productTableRows;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProducts() {
        return productTableRows.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public AddProductPage addNewProduct(){
        wait.until(ExpectedConditions.visibilityOf(addNewProductButton));
        addNewProductButton.click();
        return new AddProductPage(driver);
    }

   public class AddProductPage extends CatalogPage {

       @FindBy(xpath = "//a[@href='#tab-prices']")
       private WebElement priceTab;

       @FindBy(xpath = "//a[@href='#tab-general']")
       private WebElement generalTab;

       @FindBy(css = "button[name=save]")
       private WebElement saveButton;

       @FindBy(css = "button[name=cancel]")
       private WebElement cancelButton;

       @FindBy(css = "button[name=delete]")
       private WebElement deleteButton;

       AddProductPage(WebDriver driver) {
           super(driver);
       }

       public CatalogPage save() {
           wait.until(ExpectedConditions.visibilityOf(saveButton));
           saveButton.click();
           return new CatalogPage(driver);
       }

       protected CatalogPage cancel() {
           wait.until(ExpectedConditions.visibilityOf(cancelButton));
           cancelButton.click();
           return new CatalogPage(driver);
       }

       protected CatalogPage delete() {
           wait.until(ExpectedConditions.visibilityOf(deleteButton));
           deleteButton.click();
           wait.until(ExpectedConditions.alertIsPresent());
           driver.switchTo().alert().accept();
           return new CatalogPage(driver);
       }

       public PriceTab switchToPriceTab(){
           if (!priceTab.findElement(By.xpath("./..")).getAttribute("class").equals("active")) priceTab.click();
           return new PriceTab(driver);
       }

       public GeneralTab switchTabGeneralTab(){
           if (!generalTab.findElement(By.xpath("./..")).getAttribute("class").equals("active")) generalTab.click();
           return new GeneralTab(driver);
       }

       public class PriceTab extends AddProductPage {

           @FindBy(xpath = "//input[@name='gross_prices[USD]']")
           private WebElement USDGrossPrice;

           @FindBy(xpath = "//input[@name='gross_prices[EUR]']")
           private WebElement EURGrossPrice;

           PriceTab(WebDriver driver) {
               super(driver);
           }

           public PriceTab setPriceUSD(double amount){
               wait.until(ExpectedConditions.visibilityOf(USDGrossPrice));
               USDGrossPrice.clear();
               USDGrossPrice.sendKeys(String.valueOf(amount));
               return this;
           }

           public PriceTab setPriceEUR(String amount){
               wait.until(ExpectedConditions.visibilityOf(EURGrossPrice));
               EURGrossPrice.clear();
               EURGrossPrice.sendKeys(String.valueOf(amount));
               return this;
           }

       }

       public class GeneralTab extends AddProductPage {
           public GeneralTab(WebDriver driver) {
               super(driver);
           }

           @FindBy(xpath = "//label[contains(text(),'Enabled')]/input")
           private WebElement statusEnabled;

           @FindBy(xpath = "//input[@name='name[en]']")
           private WebElement productName;

           @FindBy(css = "input[name=code]")
           private WebElement productCode;

           @FindBy(css = "input[name=quantity]")
           private WebElement productQuantity;

           @FindBy(xpath = "//input[@name='new_images[]']")
           private WebElement productPicture;

           public GeneralTab enableProduct(){
               statusEnabled.click();
               return this;
           }

           public GeneralTab fillProductName(String name){
               productName.sendKeys(name);
               return this;
           }

           public GeneralTab fillProductCode(String code){
               productCode.sendKeys(code);
               return this;
           }

           public GeneralTab setProductQuantity(int quantity){
               productQuantity.clear();
               productQuantity.sendKeys(String.valueOf(quantity));
               return this;
           }

           public GeneralTab uploadFile(){
               productPicture.sendKeys(System.getProperty("user.dir") + "\\duck_hiw.jpg");
               return this;
           }

       }
   }

}
