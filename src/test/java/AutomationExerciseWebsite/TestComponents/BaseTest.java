package AutomationExerciseWebsite.TestComponents;

import AutomationExerciseWebsite.PageObjects.HomePage;
import AutomationExerciseWebsite.Utils.AllureUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public HomePage homePage;

    public WebDriver intializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("D:\\Testing\\Projects\\Automation\\Selenium\\AutomationExerciseWebsite\\src\\main\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (browserName.contains("headless")) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1440,900");
            }
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public void takeScreenShot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("/test-outputs/screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            AllureUtils.attachScreenshotToAllure(name, dest.getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication () throws IOException {
        driver = intializeDriver();
        homePage = new HomePage(driver);
        homePage.goToLandingPage();
        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () {
        driver.quit();
    }
}
