package br.com.core.listener;


import br.com.core.report.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports report = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReport() {
        return report;
    }

    public static void setReport(ExtentReports report) {
        TestListener.report = report;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest test) {
        TestListener.test.set(test);
    }

    @Override
    public synchronized void onStart(ITestContext context) {
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        report.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = report.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
        test.get().assignAuthor(System.getProperty("user.name"));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {

    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
