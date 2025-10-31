package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//tr[last()]//p[@class='cart_total_price']")
    WebElement totalPriceElement;

    @FindBy (css = ".address.item.box .address_firstname.address_lastname")
    WebElement deliveryAddressName;

    @FindBy (css = ".address.item.box .address_country_name")
    WebElement deliveryAddressCountry;

    @FindBy (css = ".address.item.box .address_phone")
    WebElement deliveryAddressPhone;

    @FindBy (css = ".address.alternate_item.box .address_firstname.address_lastname")
    WebElement billingAddressName;

    @FindBy (css = ".address.alternate_item.box .address_country_name")
    WebElement billingAddressCountry;

    @FindBy (css = ".address.alternate_item.box .address_phone")
    WebElement billingAddressPhone;

    @FindBy (css = ".form-control")
    WebElement orderCommentsTextArea;

    @FindBy (css = ".btn.btn-default.check_out" )
    WebElement placeOrderButton;

    @FindBy (xpath = "//tr/td[2][.//a]")
    List<WebElement> checkoutProducts ;

    By productTotalPriceInCheckoutCartBy = By.xpath("./following-sibling::td[@class='cart_total']/p");

    @Step ("Get delivery address name")
    public String getDeliveryAddressName() {
        return deliveryAddressName.getText().split("Mr. ")[1].trim();
    }

    @Step ("Get delivery address country")
    public String getDeliveryAddressCountry() {
        return deliveryAddressCountry.getText().trim();
    }

    @Step ("Get delivery address phone")
    public String getDeliveryAddressPhone() {
        return deliveryAddressPhone.getText().trim();
    }

    @Step ("Get billing address name")
    public String getBillingAddressName() {
        return billingAddressName.getText().split("Mr. ")[1].trim();
    }

    @Step ("Get billing address country")
    public String getBillingAddressCountry() {
        return billingAddressCountry.getText().trim();
    }

    @Step ("Get billing address phone")
    public String getBillingAddressPhone() {
        return billingAddressPhone.getText().trim();
    }

    @Step ("Calculate products total price in checkout cart")
    public int calculateProductsTotalPrice() {
        int sum = 0;
        for (WebElement product : checkoutProducts) {
            String totalPriceText = product.findElement(productTotalPriceInCheckoutCartBy).getText().split("Rs. ")[1].trim();
            sum += Integer.parseInt(totalPriceText);
        }
        return sum;
    }

    @Step ("Get total price from checkout page")
    public int getTotalPrice() {
        moveToElementWithJavascriptExecutor(totalPriceElement);
        String totalPrice = totalPriceElement.getText().split("Rs. ")[1].trim();
        return Integer.parseInt(totalPrice);
    }

    @Step ("Enter order comments: {0}")
    public void enterOrderComments(String comments) {
        orderCommentsTextArea.sendKeys(comments);
    }

    @Step ("Click on Place Order button")
    public PaymentPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return new PaymentPage(driver);
    }




}
