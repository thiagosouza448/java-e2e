package br.com.core.takescreenshoot;

import br.com.core.properties.PropertiesManager;
import br.com.core.setup.DriverManager;
import br.com.core.report.ExtentReports;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TakeScreenShoot extends DriverManager {

    static PropertiesManager setupProperties = new PropertiesManager("Setup.properties");

    /**
     * Create a file with a screenshot from the browser (Webdriver object)
     * @return The path to the file of the screenshot
     */
    public static String imageFile(WebDriver driver, String path){
        if(path == null) {
            path = System.getProperty("user.dir") + "\\target\\image\\";
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            path += new SimpleDateFormat("ddMMyyyyHHmmss").format(Calendar.getInstance().getTime()) + ".png";
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            ExtentReports.appendToReport(e.getMessage());
        }
        return path;
    }


    /**
     * Create a file with a screenshot from the browser in Base64 (Webdriver object)
     * @return The hash base64 of the image
     */
    public static String imageBase64(WebDriver driver){
        String hash = null;
        if (driver != null){
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        }
        return null;
    }

}
