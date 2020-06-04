package gherkin.hooks;

import br.com.core.setup.DriverManager;
import br.com.core.report.ExtentReports;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks extends DriverManager {


    private ExtentReports reports = new ExtentReports();


    @Before
    public void before(Scenario scenario) {
        testScenario = scenario;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.americanas.com.br/");
        reports.setCucumberLanguage();

    }

    @After
    public void after() {
        driver.close();
    }
}
