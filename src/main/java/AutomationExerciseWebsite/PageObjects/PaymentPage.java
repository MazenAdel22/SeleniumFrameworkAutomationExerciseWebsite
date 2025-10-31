package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponents {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (name = "name_on_card")
    WebElement nameOnCardInputField;

    @FindBy (name = "card_number")
    WebElement cardNumberInputField;

    @FindBy (name = "cvc")
    WebElement cvcInputField;

    @FindBy (name = "expiry_month")
    WebElement expiryMonthInputField;

    @FindBy (name = "expiry_year")
    WebElement expiryYearInputField;

    @FindBy (id = "submit")
    WebElement payAndConfirmOrderButton;


    @Step("Fill payment details")
    public void fillPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        nameOnCardInputField.sendKeys(nameOnCard);
        cardNumberInputField.sendKeys(cardNumber);
        cvcInputField.sendKeys(cvc);
        expiryMonthInputField.sendKeys(expiryMonth);
        expiryYearInputField.sendKeys(expiryYear);
    }

    @Step("Click on Pay and Confirm Order button")
    public PaymentDonePage clickPayAndConfirmOrderButton() {
        moveToElementWithJavascriptExecutor(payAndConfirmOrderButton);
        payAndConfirmOrderButton.click();
        return new PaymentDonePage(driver);
    }

}
