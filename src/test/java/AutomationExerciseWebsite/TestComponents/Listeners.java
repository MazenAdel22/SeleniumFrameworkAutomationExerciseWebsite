package AutomationExerciseWebsite.TestComponents;

import AutomationExerciseWebsite.Utils.AllureUtils;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener, IExecutionListener, IInvokedMethodListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenShot(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot(result.getName());
    }

    @Override
    public void onExecutionStart() {
        AllureUtils.cleanAllureResultsFolder();
    }

    @Override
    public void onExecutionFinish() {
        AllureUtils.setAllureEnvironment();
    }
}
