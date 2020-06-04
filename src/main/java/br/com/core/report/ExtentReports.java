package br.com.core.report;

import br.com.core.listener.TestListener;
import br.com.core.setup.DriverManager;
import br.com.core.properties.PropertiesManager;
import br.com.core.takescreenshoot.TakeScreenShoot;
import com.aventstack.extentreports.Status;
import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import org.openqa.selenium.WebDriver;
import org.picocontainer.classname.ClassName;

import java.io.UnsupportedEncodingException;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExtentReports extends DriverManager {

    private static PropertiesManager setupProperties = new PropertiesManager("src/test/resources/setup.properties");
    private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

    public synchronized void setExtentReportsPath() {
        LocalDate dia = LocalDate.now();
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        String reportPath = setupProperties.getProps().getProperty("ReportPath")
                + setupProperties.getProps().getProperty("ReportFile") + "_" + dia.getDayOfMonth() + "-" + dia.getMonthValue() + "-" + dia.getYear() + ".html";
        extentProperties.setReportPath(reportPath);
    }

    public static synchronized void appendToReport(String sMsg) {
        System.out.println("RESPONSE==> " + sMsg);
//        testScenario.write(sMsg);
        if (Reporter.getExtentReport() != null) {
            Reporter.addStepLog("<textarea style=\"margin: 0px; width: 593px; height: 27px;\">" + sMsg + "</textarea>");
        }
    }

    public String setupExtentReports() {
        String reportConfigPath = setupProperties.getProps().getProperty("reportConfigPath");
        Reporter.loadXMLConfig(reportConfigPath);
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("OS Name", System.getProperty("os.name"));
        Reporter.setSystemInfo("OS Version", System.getProperty("os.version"));
        Reporter.setSystemInfo("User language", System.getProperty("user.language"));
        Reporter.setSystemInfo("CPU", System.getProperty("cpu"));
        Reporter.assignAuthor(System.getProperty("user.name"));
        if (reportConfigPath != null) return reportConfigPath;
        else
            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

    public static void setCucumberLanguage() {
        try {
            Reporter.getExtentReport().setGherkinDialect(setupProperties.getProps().getProperty("Language"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.FINE, e.toString(), e);
        }
    }

    public synchronized static void appendToReport(WebDriver driver){
        appendPrintCucumberExtent(driver);
    }


    private synchronized static void appendPrintCucumberExtent(WebDriver driver){
        if (setupProperties.getProps().getProperty("GlobalEvidence").equalsIgnoreCase("true")) {
            if(Reporter.getExtentReport() != null) {
                Reporter.addStepLog(getHtmlImage(driver));
            }else if(TestListener.getTest() != null) {
                TestListener.getTest().log(Status.PASS,getHtmlImage(driver));
            }
        }
    }


    public synchronized static String getHtmlImage(WebDriver driver){
        String screenshotPath = TakeScreenShoot.imageBase64(driver);
        return "<div align=\"right\"><ul class='screenshots right'>" +
                "<li><img data-featherlight=\"image\" href=\"data:image/png;base64, "+screenshotPath+"\"  src=\"data:image/png;base64, " + screenshotPath + "\" alt=\"Red dot\" height=\"10\" width=\"3%\"  /></img></li>" +
                "</ul></div>";

    }

}

