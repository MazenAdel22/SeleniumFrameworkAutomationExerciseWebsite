package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AbstractComponents {

    WebDriver driver;
    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//input[@id='search_product']")
    WebElement searchProductInput;

    @FindBy (xpath = "//button[@id='submit_search']")
    WebElement submitSearchButton;

    @FindBy (xpath = "//h2[@class='title text-center']")
    WebElement searchedProductsTitle;

    @FindBy (css = ".productinfo.text-center")
    List<WebElement> allProductsCards ;

    @FindBy (xpath = "//div[@class='productinfo text-center'] /p")
    List<WebElement> searchedProductsNames;


    @Step("Search for product: {0}")
    public void searchProduct(String productName) {
        searchProductInput.sendKeys(productName);
        submitSearchButton.click();
    }

    @Step("Get searched products title")
    public String getSearchedProductsTitle() {
        waitForWebElementToBeVisible(searchedProductsTitle);
        return searchedProductsTitle.getText();
    }

    @Step("Check if products list contains searched product: {0}")
    public boolean ProductsListContainsSearchedProduct(String productName) {
        return searchedProductsNames.stream().anyMatch(s -> s.getText().contains(productName));
    }

    @Step("Check if no products found")
    public boolean noProductsFound() {
        return searchedProductsNames.isEmpty();
    }

    @Step("Get product by name: {0}")
    public WebElement getProduct(String productName ) {
        WebElement product = allProductsCards.stream().filter(s -> s.findElement(By.cssSelector("p")).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    @Step("Click View Product button for product: {0}")
    public void viewProductButton(String productName) {
        WebElement container = getProduct(productName).findElement(By.xpath("./ancestor::div[@class='product-image-wrapper']"));
        container.findElement(By.cssSelector(".choose a")).click();
    }

    @Step("View product details for product: {0}")
    public ProductDetailsPage viewProductDetails(String productName) {
        getProduct(productName);
        moveToElementWithJavascriptExecutor(getProduct(productName));
        viewProductButton(productName);
        return new ProductDetailsPage(driver);
    }


}
