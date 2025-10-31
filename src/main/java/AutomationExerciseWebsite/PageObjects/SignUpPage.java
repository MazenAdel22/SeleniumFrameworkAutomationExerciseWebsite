package AutomationExerciseWebsite.PageObjects;

import AutomationExerciseWebsite.AbstractComponents.AbstractComponents;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends AbstractComponents {

    WebDriver driver;
    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="#id_gender1")
    WebElement mrTitle;

    @FindBy(css = "#password")
    WebElement passwordField;

    @FindBy(css = "#days")
    WebElement daysDropdownElement;

    @FindBy(css = "#months")
    WebElement monthsDropdownElement;

    @FindBy(css = "#years")
    WebElement yearsDropdownElement;

    @FindBy(css = "#newsletter")
    WebElement newsletterCheckbox;

    @FindBy(css = "#optin")
    WebElement offersCheckbox;

    @FindBy(css = "#first_name")
    WebElement firstNameField;

    @FindBy(css = "#last_name")
    WebElement lastNameField;

    @FindBy(css = "#company")
    WebElement companyField;

    @FindBy(css = "#address1")
    WebElement addressField;

    @FindBy(css = "#country")
    WebElement countriesDropdownElement;

    @FindBy(xpath = "//input[@data-qa='state']")
    WebElement stateField;

    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement cityField;

    @FindBy(css = "#zipcode")
    WebElement zipCodeField;

    @FindBy(css = "#mobile_number")
    WebElement mobileNumberField;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountButton;

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreatedMessage;


    @Step("Fill Sign Up Form and Create Account")
    public void fillSignUpForm(String password, String day, String month, String year,
                               String firstName, String lastName, String company,
                               String address, String country, String state,
                               String city, String zipCode, String mobileNumber) {
        mrTitle.click();
        passwordField.sendKeys(password);
        Select daysDropDown = new Select(daysDropdownElement);
        daysDropDown.selectByVisibleText(day);
        Select monthsDropDown = new Select(monthsDropdownElement);
        monthsDropDown.selectByVisibleText(month);
        Select yearsDropDown = new Select(yearsDropdownElement);
        yearsDropDown.selectByVisibleText(year);
        this.moveToElementWithJavascriptExecutor(newsletterCheckbox);
        newsletterCheckbox.click();
        this.moveToElementWithJavascriptExecutor(offersCheckbox);
        offersCheckbox.click();
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        companyField.sendKeys(company);
        addressField.sendKeys(address);
        Select countriesDropDown = new Select(countriesDropdownElement);
        countriesDropDown.selectByVisibleText(country);
        stateField.sendKeys(state);
        cityField.sendKeys(city);
        zipCodeField.sendKeys(zipCode);
        mobileNumberField.sendKeys(mobileNumber);
        this.moveToElementWithJavascriptExecutor(createAccountButton);
        createAccountButton.click();
    }

    @Step("Get Account Created Message")
    public String getAccountCreatedMessage() {
        return accountCreatedMessage.getText();
    }


}
