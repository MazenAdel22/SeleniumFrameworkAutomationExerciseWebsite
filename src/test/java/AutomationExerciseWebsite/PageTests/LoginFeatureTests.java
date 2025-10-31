package AutomationExerciseWebsite.PageTests;

import AutomationExerciseWebsite.PageObjects.SignUpAndLoginPage;
import AutomationExerciseWebsite.PageObjects.SignUpPage;
import AutomationExerciseWebsite.TestComponents.BaseTest;
import AutomationExerciseWebsite.TestComponents.DataProviderImp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginFeatureTests extends BaseTest {

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getValidData")
    public void signUpTestWithValidCridentials(HashMap<String,String> input) {
        SignUpAndLoginPage signUpAndLoginPage = homePage.goToSignUpLoginPage();
        SignUpPage signUpPage = signUpAndLoginPage.fillSignUpForm(input.get("name"), input.get("email"));
        signUpPage.fillSignUpForm( input.get("password"), input.get("day"), input.get("month"),
                input.get("year"), input.get("firstName"), input.get("lastName"), input.get("company"),
                input.get("address"), input.get("country"), input.get("state"), input.get("city"),
                input.get("zip"), input.get("mobileNumber"));
        Assert.assertEquals(signUpPage.getAccountCreatedMessage(), "ACCOUNT CREATED!");
        signUpPage.clickContinueButton();
        Assert.assertTrue(homePage.isLoggedInAs(input.get("name")));
        homePage.clickLogoutButton();
    }

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getValidData", dependsOnMethods = {"signUpTestWithValidCridentials"})
    public void loginTestWithValidCridentials(HashMap<String,String> input) {
        SignUpAndLoginPage signUpAndLoginPage = homePage.goToSignUpLoginPage();
        Assert.assertEquals(signUpAndLoginPage.getLoginToYourAccountText(), "Login to your account");
        signUpAndLoginPage.fillLoginForm(input.get("email"), input.get("password"));
        Assert.assertTrue(homePage.isLoggedInAs(input.get("name")));
        homePage.DeleteAccount();
        Assert.assertEquals(signUpAndLoginPage.getSucessDeletedMessage(), "ACCOUNT DELETED!");
        signUpAndLoginPage.clickContinueButton();
    }

}
