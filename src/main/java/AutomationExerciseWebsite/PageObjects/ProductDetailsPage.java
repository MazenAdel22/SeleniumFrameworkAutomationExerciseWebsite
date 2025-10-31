package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends AbstractComponents {

    WebDriver driver ;
    public ProductDetailsPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".product-information h2")
    WebElement productNameTitle;

    @FindBy (xpath = "//div[@class='product-information'] /p[1]")
    WebElement productCategoryTitle;

    @FindBy (xpath = "//div[@class='product-information'] /p[2]")
    WebElement productAvailabilityTitle;

    @FindBy (xpath = "//div[@class='product-information'] /p[3]")
    WebElement productConditionTitle;

    @FindBy (xpath = "//div[@class='product-information'] /p[4]")
    WebElement productBrandTitle;

    @FindBy (css = ".product-information span span")
    WebElement productPriceTitle;

    @Step("Get product name title")
    public String getProductNameTitle(){
        return productNameTitle.getText();
    }

    @Step("Get product category title")
    public String getProductCategoryTitle(){
        return productCategoryTitle.getText().split(": ")[1].trim();
    }

    @Step("Get product availability title")
    public String getProductAvailabilityTitle(){
        return productAvailabilityTitle.getText().split(": ")[1].trim();
    }

    @Step("Get product condition title")
    public String getProductConditionTitle() {
        return productConditionTitle.getText().split(": ")[1].trim();
    }

    @Step("Get product brand title")
    public String getProductBrandTitle() {
        return productBrandTitle.getText().split(": ")[1].trim();
    }

    @Step("Get product price title")
    public String getProductPriceTitle() {
        return productPriceTitle.getText().trim();
    }
}
