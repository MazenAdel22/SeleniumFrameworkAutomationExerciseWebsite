package AutomationExerciseWebsite.PageTests;

import AutomationExerciseWebsite.PageObjects.*;
import AutomationExerciseWebsite.TestComponents.BaseTest;
import AutomationExerciseWebsite.TestComponents.DataProviderImp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class OrderTests extends BaseTest {

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getValidData")
    public void subscriptionTest(HashMap<String,String> input) {
        Assert.assertEquals(homePage.getSubscriptionTitle(), "SUBSCRIPTION");
        homePage.enterSubscriptionEmail(input.get("email"));
        Assert.assertEquals(homePage.getSubscriptionSuccessMessage(), "You have been successfully subscribed!");
    }

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getProductDetails")
    public void searchProductTest(HashMap<String,String> input) {
        ProductsPage productsPage = homePage.goToProductsPage();
        productsPage.searchProduct(input.get("SearchedProductName"));
        Assert.assertEquals(productsPage.getSearchedProductsTitle(), "SEARCHED PRODUCTS");
        Assert.assertTrue(productsPage.ProductsListContainsSearchedProduct(input.get("SearchedProductName")));
    }

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getProductDetails")
    public void viewProductDetailsTest(HashMap<String,String> input) {
        ProductsPage productsPage = homePage.goToProductsPage();
        ProductDetailsPage productDetailsPage = productsPage.viewProductDetails(input.get("ProductName"));
        Assert.assertEquals(productDetailsPage.getProductNameTitle(), input.get("ProductName"));
        Assert.assertEquals(productDetailsPage.getProductCategoryTitle(), input.get("Category"));
        Assert.assertEquals(productDetailsPage.getProductAvailabilityTitle(), input.get("Availability"));
        Assert.assertEquals(productDetailsPage.getProductConditionTitle(), input.get("Condition"));
        Assert.assertEquals(productDetailsPage.getProductBrandTitle(), input.get("Brand"));
        Assert.assertEquals(productDetailsPage.getProductPriceTitle(), input.get("Price"));
    }

    @Test (dataProviderClass = DataProviderImp.class, dataProvider = "getValidData")
    public void endToEndTest(HashMap<String,String> input) {
        SignUpAndLoginPage signUpAndLoginPage = homePage.goToSignUpLoginPage();
        signUpAndLoginPage.fillLoginForm(input.get("email"), input.get("password"));
        homePage.addProductToCart(input.get("firstProductName"));
        Assert.assertEquals(homePage.getAddedToCartMessage(), "Added!");
        homePage.clickContinueShoppingButton();
        homePage.addProductToCart(input.get("firstProductName"));
        Assert.assertEquals(homePage.getAddedToCartMessage(), "Added!");
        homePage.clickContinueShoppingButton();
        homePage.addProductToCart(input.get("secondProductName"));
        Assert.assertEquals(homePage.getAddedToCartMessage(), "Added!");
        homePage.clickContinueShoppingButton();
        homePage.addProductToCart(input.get("thirdProductName"));
        Assert.assertEquals(homePage.getAddedToCartMessage(), "Added!");
        homePage.clickContinueShoppingButton();
        CartPage cartPage = homePage.goToCartPage();
        cartPage.removeProductFromCart(input.get("thirdProductName"));
        Assert.assertEquals(cartPage.getSingleProductPriceInCart(input.get("firstProductName")), input.get("firstProductPrice"));
        Assert.assertEquals(cartPage.getProductQuantity(input.get("firstProductName")), input.get("firstProductQuantity"));
        Assert.assertEquals(cartPage.getProductTotalPriceInCartAsInt(input.get("firstProductName")), cartPage.getProductManipulatedPriceAsInt(input.get("firstProductName")));
        Assert.assertEquals(cartPage.getSingleProductPriceInCart(input.get("secondProductName")), input.get("secondProductPrice"));
        Assert.assertEquals(cartPage.getProductQuantity(input.get("secondProductName")), input.get("secondProductQuantity"));
        Assert.assertEquals(cartPage.getProductTotalPriceInCartAsInt(input.get("secondProductName")), cartPage.getProductManipulatedPriceAsInt(input.get("secondProductName")));
        CheckoutPage checkoutPage = cartPage.ProceedToCheckoutButton();
        Assert.assertEquals(checkoutPage.getDeliveryAddressName(), input.get("name"));
        Assert.assertEquals(checkoutPage.getDeliveryAddressCountry(), input.get("country"));
        Assert.assertEquals(checkoutPage.getDeliveryAddressPhone(), input.get("mobileNumber"));
        Assert.assertEquals(checkoutPage.getBillingAddressName(), input.get("name"));
        Assert.assertEquals(checkoutPage.getBillingAddressCountry(), input.get("country"));
        Assert.assertEquals(checkoutPage.getBillingAddressPhone(), input.get("mobileNumber"));
        Assert.assertEquals(checkoutPage.getTotalPrice() , checkoutPage.calculateProductsTotalPrice() );
        checkoutPage.enterOrderComments("Good Order");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrderButton();
        paymentPage.fillPaymentDetails(input.get("cardName"), input.get("cardNumber"), input.get("cvc"), input.get("expiryMonth"), input.get("expiryYear"));
        PaymentDonePage paymentDonePage = paymentPage.clickPayAndConfirmOrderButton();
        Assert.assertEquals(paymentDonePage.getOrderPlacedSuccessfullyMessage(), "Congratulations! Your order has been confirmed!");
    }
}
