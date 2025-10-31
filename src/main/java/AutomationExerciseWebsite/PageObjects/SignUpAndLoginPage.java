package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpAndLoginPage extends AbstractComponents {

    WebDriver driver;

    public SignUpAndLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement nameInputField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailInputField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signUpButton;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement loginEmailInputField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement loginPasswordInputField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='login-form'] /h2")
    WebElement loginToYourAccountText;

    @FindBy(xpath = "//h2[@class='title text-center'] /b")
    WebElement sucessDeletedMessage;

    @FindBy(xpath = "//form[@action='/login'] /p")
    WebElement errorInvalidEmailPasswordMessage;

    @FindBy(xpath = "//form[@action='/signup'] /p")
    WebElement errorExistingEmailMessage;

    @Step("Fill Sign Up form with Name: {0} and Email: {1}")
    public SignUpPage fillSignUpForm(String name, String email) {
        nameInputField.sendKeys(name);
        emailInputField.sendKeys(email);
        signUpButton.click();
        return new SignUpPage(driver);
    }

    @Step("Fill Login form with Email: {0} and Password: {1}")
    public void fillLoginForm(String email, String password) {
        loginEmailInputField.sendKeys(email);
        loginPasswordInputField.sendKeys(password);
        loginButton.click();
    }

    @Step("Get 'Login to your account' text")
    public String getLoginToYourAccountText() {
        return loginToYourAccountText.getText();
    }

    @Step("Get 'Account Deleted!' message")
    public String getSucessDeletedMessage() {
        return sucessDeletedMessage.getText();
    }

    @Step("Get error message for invalid login")
    public String getErrorLoginMessage() {
        return errorInvalidEmailPasswordMessage.getText();
    }

    @Step("Get error message for existing email sign up")
    public String getErrorSignUpMessage() {
        return errorExistingEmailMessage.getText();
    }
}
