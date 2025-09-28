package AutomationExerciseWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Mazen Adel");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("pJ4s099@cerisun.com");
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

        String loggedInAs = driver.findElement(By.cssSelector("b")).getText();
        Assert.assertTrue(loggedInAs.contains("Mazen Adel"));

        List<WebElement> products = driver.findElements(By.cssSelector(".productinfo.text-center"));

        WebElement productNo1 = products.stream().filter(s->s.findElement(By.cssSelector("p")).getText().equals("Madame Top For Women")).findFirst().orElse(null);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productNo1);
        productNo1.findElement(By.cssSelector(".btn.btn-default.add-to-cart")).click();

        //wait until the modal appears
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title.w-100")));
        String addedToCartMessage = driver.findElement(By.cssSelector(".modal-title.w-100")).getText();
        Assert.assertEquals(addedToCartMessage, "Added!");
        driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();

        WebElement productNo2 = products.stream().filter(s->s.findElement(By.cssSelector("p")).getText().equals("Blue Cotton Indie Mickey Dress")).findFirst().orElse(null);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productNo2);
        productNo2.findElement(By.cssSelector(".btn.btn-default.add-to-cart")).click();

        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}


// //a[@href='/product_details/29']  OR //tr/td[@class='cart_description']

//  .btn.btn-default.check_out