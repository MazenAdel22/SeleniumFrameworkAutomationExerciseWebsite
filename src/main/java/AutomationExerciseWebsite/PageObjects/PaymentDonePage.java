package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentDonePage extends AbstractComponents {

    WebDriver driver;

    public PaymentDonePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "p")
    WebElement orderPlacedSuccessfullyMessage;

    @Step("Get order placed successfully message")
    public String getOrderPlacedSuccessfullyMessage() {
        waitForWebElementToBeVisible(orderPlacedSuccessfullyMessage);
        return orderPlacedSuccessfullyMessage.getText();
    }
}
