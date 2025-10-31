package AutomationExerciseWebsite.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataProviderImp {


    public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // JSON To String 'common.io'
        String jsonContent = FileUtils.readFileToString(new File(filePath));
        // String To HashMap 'Jackson Databind'
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent
                , new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    @DataProvider
    public static Object[][] getValidData () throws IOException {
        List<HashMap<String,String>> validData = getJsonDataToMap("D:\\Testing\\Projects\\Automation\\Selenium\\AutomationExerciseWebsite\\src\\main\\resources\\ValidCridentials.json");
        return new Object[][] {{validData.get(0)}};
    }

    @DataProvider
    public static Object[][] getInvalidLoginData () throws IOException {
        List<HashMap<String,String>> invalidData = getJsonDataToMap("D:\\Testing\\Projects\\Automation\\Selenium\\AutomationExerciseWebsite\\src\\main\\resources\\InvalidLoginCridentials.json");
        return new Object[][] {{invalidData.get(0)}};
    }

    @DataProvider
    public static Object[][] getInvalidSignUpData () throws IOException {
        List<HashMap<String,String>> invalidSignUpData = getJsonDataToMap("D:\\Testing\\Projects\\Automation\\Selenium\\AutomationExerciseWebsite\\src\\main\\resources\\InvalidSignUpCridentials.json");
        return new Object[][] {{invalidSignUpData.get(0)}};
    }

    @DataProvider
    public static Object[][] getProductDetails () throws IOException {
        List<HashMap<String,String>> productDetails = getJsonDataToMap("D:\\Testing\\Projects\\Automation\\Selenium\\AutomationExerciseWebsite\\src\\main\\resources\\ProductDetails.json");
        return new Object[][] {{productDetails.get(0)}};
    }
}
