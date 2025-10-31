package AutomationExerciseWebsite.Utils;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static java.nio.file.Files.newInputStream;

public class AllureUtils {

    public static void cleanAllureResultsFolder() {
        FileUtils.deleteQuietly(new File("test-outputs/allure-results"));
    }

    public static void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("JDK", "21.0.1")
                        .put("URL", "https://automationexercise.com/")
                        .build(), "D://Testing//Projects//Automation//Selenium//AutomationExerciseWebsite//test-outputs/allure-results/");
    }

    public static void attachScreenshotToAllure(String screenName, String screenPath) {
        try {
            Allure.addAttachment(screenName, newInputStream(Path.of(screenPath)));
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
