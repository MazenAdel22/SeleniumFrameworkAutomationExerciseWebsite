package AutomationExerciseWebsite.AbstractComponents;

import AutomationExerciseWebsite.PageObjects.CartPage;
import AutomationExerciseWebsite.PageObjects.HomePage;
import AutomationExerciseWebsite.PageObjects.ProductsPage;
import AutomationExerciseWebsite.PageObjects.SignUpAndLoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='/login']")
    WebElement signUpLoginHeader;

    @FindBy(css = "b")
    WebElement loggedInAsTextHeader;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutHeader;

    @FindBy(xpath = "//a[@href='/delete_account'] /i")
    WebElement deleteAccountHeader;

    @FindBy(xpath = "//a[@href='/products']")
    WebElement productsHeader;

    @FindBy (xpath = "//a[@href='/view_cart']")
    WebElement cartHeader;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='single-widget'] /h2")
    WebElement subscriptionTitle;

    @FindBy(id = "susbscribe_email")
    WebElement subscriptionEmailInputField;

    @FindBy(id = "subscribe")
    WebElement subscriptionButton;

    @FindBy(css = ".alert-success.alert")
    WebElement subscriptionSuccessMessage;

    public void moveToElementWithJavascriptExecutor(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForWebElementToBeVisible (WebElement findWebElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findWebElement));
    }

    @Step ("Go to Sign Up and Login Page")
    public SignUpAndLoginPage goToSignUpLoginPage() {
        signUpLoginHeader.click();
        return new SignUpAndLoginPage(driver);
    }

    @Step ("Go to Products Page")
    public ProductsPage goToProductsPage() {
        productsHeader.click();
        return new ProductsPage(driver);
    }

    @Step ("Go to Cart Page")
    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

    @Step ("Click on Logout Button")
    public void clickLogoutButton() {
        logoutHeader.click();
    }

    @Step ("Click on Delete Account Button")
    public void DeleteAccount() {
        deleteAccountHeader.click();
    }

    @Step ("Check if logged in as: {0}")
    public boolean isLoggedInAs(String name) {
        return loggedInAsTextHeader.getText().contains(name);
    }

    @Step ("Click on Continue Button")
    public HomePage clickContinueButton() {
        continueButton.click();
        return new HomePage(driver);
    }

    @Step ("Get Subscription Title")
    public String getSubscriptionTitle() {
        moveToElementWithJavascriptExecutor(subscriptionTitle);
        waitForWebElementToBeVisible(subscriptionTitle);
        return subscriptionTitle.getText();
    }

    @Step ("Enter subscription email: {0} and click on Subscribe button")
    public void enterSubscriptionEmail(String email) {
        subscriptionEmailInputField.sendKeys(email);
        subscriptionButton.click();
    }

    @Step ("Get Subscription Success Message")
    public String getSubscriptionSuccessMessage() {
        waitForWebElementToBeVisible(subscriptionSuccessMessage);
        return subscriptionSuccessMessage.getText();
    }
}
