package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//tr/td[2][.//a]")
    List<WebElement> cartProducts ;

    @FindBy (css = ".btn.btn-default.check_out")
    WebElement proceedToCheckoutButton ;

    By getProductInCartBy = By.cssSelector("h4 a");
    By singleProductPriceInCartBy = By.xpath("./following-sibling::td[@class='cart_price']/p");
    By productQuantityInCartBy = By.xpath("./following-sibling::td[@class='cart_quantity']/button");
    By productTotalPriceInCartBy = By.xpath("./following-sibling::td[@class='cart_total']/p");
    By removeProductFromCartBy = By.xpath("./following-sibling::td[@class='cart_delete']/a");

    @Step ("Get product in cart by name: {0}")
    public WebElement getProductInCart(String productName) {
        return cartProducts.stream().filter(s -> s.findElement(getProductInCartBy).getText().equals(productName)).findFirst().orElse(null);
    }

    @Step ("Get single product price in cart for product: {0}")
    public String getSingleProductPriceInCart(String productName) {
        return getProductInCart(productName).findElement(singleProductPriceInCartBy).getText();
    }

    @Step ("Get product quantity in cart for product: {0}")
    public String getProductQuantity(String productName) {
        return getProductInCart(productName).findElement(productQuantityInCartBy).getText();
    }

    @Step ("Get product total price as integer in cart for product: {0}")
    public int getProductQuantityAsInt(String productName) {
        return Integer.parseInt(getProductInCart(productName).findElement(productQuantityInCartBy).getText());
    }

    @Step ("Get product total price as integer in cart for product: {0}")
    public int getProductTotalPriceInCartAsInt(String productName) {
        return Integer.parseInt(getProductInCart(productName).findElement(productTotalPriceInCartBy).getText().split("Rs. ")[1].trim());
    }

    @Step ("Get product price as integer in cart for product: {0}")
    public int getProductPriceAsInt(String productName) {
        return Integer.parseInt(getSingleProductPriceInCart(productName).split("Rs. ")[1].trim());
    }

    @Step ("Get product manipulated price as integer in cart for product: {0}")
    public int getProductManipulatedPriceAsInt(String productName) {
        return getProductPriceAsInt(productName) * getProductQuantityAsInt(productName);
    }

    @Step ("Remove product from cart: {0}")
    public void removeProductFromCart(String productName) {
        getProductInCart(productName).findElement(removeProductFromCartBy).click();
    }

    @Step ("Click on Proceed To Checkout button")
    public CheckoutPage ProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

}
