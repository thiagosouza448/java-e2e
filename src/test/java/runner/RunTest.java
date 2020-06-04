package runner;


//import br.com.core.report.ExtentReports;
//import br.com.core.services.ServiceFactory;

import br.com.core.report.ExtentReports;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.io.*;


@CucumberOptions
        (
                features = "features",
                glue = {"gherkin.stepdefs", "gherkin.hooks"},
                tags = {"@COMPRACT001"},
                plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"},
                monochrome = true
        )

public class RunTest {
    private static ExtentReports reports = new ExtentReports();

    private static TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void SetConfigClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        reports.setExtentReportsPath();
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass
    private static void DownClass() {
        reports.setupExtentReports();
        testNGCucumberRunner.finish();
    }
}