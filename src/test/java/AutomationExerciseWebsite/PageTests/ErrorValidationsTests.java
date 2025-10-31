package AutomationExerciseWebsite.PageTests;

import AutomationExerciseWebsite.PageObjects.ProductsPage;
import AutomationExerciseWebsite.PageObjects.SignUpAndLoginPage;
import AutomationExerciseWebsite.TestComponents.BaseTest;
import AutomationExerciseWebsite.TestComponents.DataProviderImp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ErrorValidationsTests extends BaseTest {


    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getInvalidLoginData")
    public void loginErrorValidation(HashMap<String,String> input) {
        SignUpAndLoginPage signUpAndLoginPage = homePage.goToSignUpLoginPage();
        Assert.assertEquals(signUpAndLoginPage.getLoginToYourAccountText(), "Login to your account");
        signUpAndLoginPage.fillLoginForm(input.get("email"), input.get("password"));
        Assert.assertEquals(signUpAndLoginPage.getErrorLoginMessage(), "Your email or password is incorrect!");
    }

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getInvalidSignUpData")
    public void signUpErrorValidation(HashMap<String,String> input) {
        SignUpAndLoginPage signUpAndLoginPage = homePage.goToSignUpLoginPage();
        signUpAndLoginPage.fillSignUpForm(input.get("name"), input.get("email"));
        Assert.assertEquals(signUpAndLoginPage.getErrorSignUpMessage(), "Email Address already exist!");
    }

    @Test
    public void searchProductNotFoundTest() {
        ProductsPage productsPage = homePage.goToProductsPage();
        productsPage.searchProduct("NonExistingProduct");
        Assert.assertEquals(productsPage.getSearchedProductsTitle(), "SEARCHED PRODUCTS");
        Assert.assertTrue(productsPage.noProductsFound());
    }

}