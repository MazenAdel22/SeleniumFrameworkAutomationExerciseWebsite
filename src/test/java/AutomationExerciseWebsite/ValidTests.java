package AutomationExerciseWebsite;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ValidTests {

    @Test(priority = 1)
    public static void signUpTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Mazen Adel");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("sanij69099@cerisun.com");
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        driver.findElement(By.cssSelector("#id_gender1")).click();
        driver.findElement(By.cssSelector("#password")).sendKeys("v99ztYi3He@5FmV");
        WebElement days = driver.findElement(By.cssSelector("#days"));
        Select daysDropDown = new Select(days);
        daysDropDown.selectByVisibleText("21");
        WebElement months = driver.findElement(By.cssSelector("#months"));
        Select monthsDropDown = new Select(months);
        monthsDropDown.selectByVisibleText("July");
        WebElement years = driver.findElement(By.cssSelector("#years"));
        Select yearsDropDown = new Select(years);
        yearsDropDown.selectByVisibleText("2000");
        driver.findElement(By.cssSelector("#newsletter")).click();
        driver.findElement(By.cssSelector("#optin")).click();
        driver.findElement(By.cssSelector("#first_name")).sendKeys("Mazen");
        driver.findElement(By.cssSelector("#last_name")).sendKeys("Adel");
        driver.findElement(By.cssSelector("#company")).sendKeys("Testing Company");
        driver.findElement(By.cssSelector("#address1")).sendKeys("Alexandria");
        WebElement countries = driver.findElement(By.cssSelector("#country"));
        Select countriesDropDown = new Select(countries);
        countriesDropDown.selectByVisibleText("United States");
        driver.findElement(By.xpath("//input[@data-qa='state']")).sendKeys("New York");
        driver.findElement(By.xpath("//input[@data-qa='city']")).sendKeys("Miami");
        driver.findElement(By.cssSelector("#zipcode")).sendKeys("66001");
        driver.findElement(By.cssSelector("#mobile_number")).sendKeys("+20789456123");
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        String successSignupMessage = driver.findElement(By.xpath("//h2[@data-qa='account-created']")).getText();
        Assert.assertEquals(successSignupMessage, "ACCOUNT CREATED!");
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("b")).getText().contains("Mazen Adel"));
        driver.findElement(By.xpath("//a[@href='/logout'] /i")).click();
        driver.quit();
    }

    @Test(priority = 3)
    public static void loginTestAndDeletingEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        String loginTitle = driver.findElement(By.xpath("//div[@class='login-form'] /h2")).getText();
        Assert.assertEquals(loginTitle, "Login to your account");
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("sanij69099@cerisun.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("v99ztYi3He@5FmV");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("b")).getText().contains("Mazen Adel"));
        driver.findElement(By.xpath("//a[@href='/delete_account'] /i")).click();
        String sucessDeletedMessage = driver.findElement(By.xpath("//h2[@class='title text-center'] /b")).getText();
        Assert.assertEquals(sucessDeletedMessage, "ACCOUNT DELETED!");
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        driver.quit();
    }

    @Test(priority = 4)
    public void LoginTestWithDeletedAccount() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        String loginTitle = driver.findElement(By.xpath("//div[@class='login-form'] /h2")).getText();
        Assert.assertEquals(loginTitle, "Login to your account");
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("sanij69099@cerisun.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("v99ztYi3He@5FmV");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//form[@action='/login'] /p")).getText().contains("Your email or password is incorrect!"));
        driver.quit();
    }

    @Test(priority = 2)
    public void signUpTestWithExistingEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Mazen Adel");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("sanij69099@cerisun.com");
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();


        Assert.assertTrue(driver.findElement(By.xpath("//form[@action='/signup'] /p")).getText().contains("Email Address already exist!"));
        driver.quit();
    }

    @Test
    public void contactUsFormTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/contact_us']")).click();

    }

    @Test
    public void searchProductTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        driver.findElement(By.xpath("//input[@id='search_product']")).sendKeys("Jeans");
        driver.findElement(By.xpath("//button[@id='submit_search']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='title text-center']")));
        Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='title text-center']")).getText(), "SEARCHED PRODUCTS");
        List<WebElement> searchedProducts = driver.findElements(By.xpath("//div[@class='productinfo text-center'] /p"));
        Assert.assertTrue(searchedProducts.stream().anyMatch(s -> s.getText().contains("Jeans")));
        driver.quit();
    }

    @Test
    public void viewProductDetailsTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        List<WebElement> products = driver.findElements(By.cssSelector(".productinfo.text-center"));
        WebElement productNo1 = products.stream().filter(s -> s.findElement(By.cssSelector("p")).getText().equals("Frozen Tops For Kids")).findFirst().orElse(null);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productNo1);
        WebElement container = productNo1.findElement(By.xpath("./ancestor::div[@class='product-image-wrapper']"));
        container.findElement(By.cssSelector(".choose a")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".product-information h2")).getText(),"Frozen Tops For Kids");

        String actualCategory = driver.findElement(By.xpath("//div[@class='product-information'] /p[1]")).getText().split(": ")[1].trim();
        Assert.assertEquals(actualCategory,"Kids > Tops & Shirts");

        String actualAvailability = driver.findElement(By.xpath("//div[@class='product-information'] /p[2]")).getText().split(": ")[1].trim();
        Assert.assertEquals(actualAvailability,"In Stock");

        String actualCondition = driver.findElement(By.xpath("//div[@class='product-information'] /p[3]")).getText().split(": ")[1].trim();
        Assert.assertEquals(actualCondition,"New");

        String actualBrand = driver.findElement(By.xpath("//div[@class='product-information'] /p[4]")).getText().split(": ")[1].trim();
        Assert.assertEquals(actualBrand,"Allen Solly Junior");

        Assert.assertEquals(driver.findElement(By.cssSelector(".product-information span span")).getText(),"Rs. 278");

        driver.quit();
    }

    @Test
    public void verifySubscriptionInHomePage() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='single-widget'] /h2")).getText(), "SUBSCRIPTION");
        driver.findElement(By.id("susbscribe_email")).sendKeys("sanij69099@cerisun.com");
        WebElement subscriptionButton = driver.findElement(By.id("subscribe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionButton);
        subscriptionButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success.alert")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success.alert")).getText(), "You have been successfully subscribed!");
        driver.quit();
    }

    @Test
    public void verifySubscriptionInCartPage() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='single-widget'] /h2")).getText(), "SUBSCRIPTION");
        driver.findElement(By.id("susbscribe_email")).sendKeys("sanij69099@cerisun.com");
        WebElement subscriptionButton = driver.findElement(By.id("subscribe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionButton);
        subscriptionButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success.alert")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success.alert")).getText(), "You have been successfully subscribed!");
        driver.quit();
    }

    @Test
    public void buyProductsAndCheckoutTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        String loginTitle = driver.findElement(By.xpath("//div[@class='login-form'] /h2")).getText();
        Assert.assertEquals(loginTitle, "Login to your account");
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("sanij69099@cerisun.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("v99ztYi3He@5FmV");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        List<WebElement> homepageProducts = driver.findElements(By.cssSelector(".productinfo.text-center"));

        WebElement productNo1 = homepageProducts.stream().filter(s->s.findElement(By.cssSelector("p")).getText().equals("Madame Top For Women")).findFirst().orElse(null);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productNo1);
        productNo1.findElement(By.cssSelector(".btn.btn-default.add-to-cart")).click();

        //wait until the modal appears
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title.w-100")));
        String addedToCartMessage = driver.findElement(By.cssSelector(".modal-title.w-100")).getText();
        Assert.assertEquals(addedToCartMessage, "Added!");
        driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();

        WebElement productNo2 = homepageProducts.stream().filter(s->s.findElement(By.cssSelector("p")).getText().equals("Blue Cotton Indie Mickey Dress")).findFirst().orElse(null);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productNo2);
        productNo2.findElement(By.cssSelector(".btn.btn-default.add-to-cart")).click();

        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("//tr/td[2][.//a]"));
        WebElement cartProductNo1 = cartProducts.stream().filter(s -> s.findElement(By.cssSelector("h4 a")).getText().equals("Blue Cotton Indie Mickey Dress")).findFirst().orElse(null);
        Assert.assertEquals(cartProductNo1.findElement(By.xpath("./following-sibling::td[@class='cart_price']/p")).getText(),"Rs. 1530");
        String product1totalPrice = cartProductNo1.findElement(By.xpath("./following-sibling::td[@class='cart_total']/p")).getText().split("Rs. ")[1].trim();
        int cartProductNo1Price = Integer.parseInt(product1totalPrice);

        WebElement deletedProduct = cartProducts.stream().filter(s -> s.findElement(By.cssSelector("h4 a")).getText().equals("Madame Top For Women")).findFirst().orElse(null);
        deletedProduct.findElement(By.xpath("./following-sibling::td[@class='cart_delete']/a")).click();


        driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();
        WebElement totalPriceElement = driver.findElement(By.xpath("//tr[last()]//p[@class='cart_total_price']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totalPriceElement);
        String totalPrice = totalPriceElement.getText().split("Rs. ")[1].trim();
        int totalCartPrice = Integer.parseInt(totalPrice);
        Assert.assertEquals(totalCartPrice, cartProductNo1Price);

        Assert.assertEquals(driver.findElement(By.cssSelector(".address.item.box .address_firstname.address_lastname")).getText(),"Mr. Mazen Adel");
        Assert.assertEquals(driver.findElement(By.cssSelector(".address.item.box .address_country_name")).getText(),"United States");
        Assert.assertEquals(driver.findElement(By.cssSelector(".address.item.box .address_phone")).getText(),"+20789456123");
        Assert.assertEquals(driver.findElement(By.cssSelector(".address.alternate_item.box .address_firstname.address_lastname")).getText(),"Mr. Mazen Adel");
        Assert.assertEquals(driver.findElement(By.cssSelector(".address.alternate_item.box .address_country_name")).getText(),"United States");
        Assert.assertEquals(driver.findElement(By.cssSelector(".address.alternate_item.box .address_phone")).getText(),"+20789456123");
        driver.findElement(By.cssSelector(".form-control")).sendKeys("Thank you for this Service");
        driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();

        driver.findElement(By.name("name_on_card")).sendKeys("Mazen Adel");
        driver.findElement(By.name("card_number")).sendKeys("98348763");
        driver.findElement(By.name("cvc")).sendKeys("532");
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p")).getText(),"Congratulations! Your order has been confirmed!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        driver.quit();
    }
}
