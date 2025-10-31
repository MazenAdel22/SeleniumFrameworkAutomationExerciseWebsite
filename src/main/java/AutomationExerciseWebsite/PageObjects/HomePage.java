package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractComponents {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".productinfo.text-center")
    List<WebElement> allProductsCards ;

    @FindBy (css = ".modal-title.w-100")
    WebElement addedToCartMessage;

    @FindBy (css = ".btn.btn-success.close-modal.btn-block")
    WebElement continueShoppingButton;

    @Step("Navigate to Landing Page")
    public void goToLandingPage() {
        driver.get("https://automationexercise.com/");
    }

    @Step("Get product by name: {0}")
    public WebElement getProduct( String productName ) {
        WebElement product = allProductsCards.stream().filter(s -> s.findElement(By.cssSelector("p")).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    @Step("Add product to cart: {0}")
    public void addToCart ( String productName ) {
        getProduct(productName).findElement(By.cssSelector(".btn.btn-default.add-to-cart")).click();
    }

    @Step("Scroll to product and add to cart: {0}")
    public void addProductToCart( String productName){
        getProduct(productName);
        moveToElementWithJavascriptExecutor(getProduct(productName));
        addToCart(productName);
    }

    @Step("Get added to cart message")
    public String getAddedToCartMessage() {
        waitForWebElementToBeVisible(addedToCartMessage);
        return addedToCartMessage.getText();
    }

    @Step("Click Continue Shopping button")
    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }
}
