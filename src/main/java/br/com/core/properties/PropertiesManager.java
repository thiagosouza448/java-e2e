package br.com.core.properties;

import br.com.core.report.ExtentReports;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class PropertiesManager
{

    private Properties props = new Properties();
    private String filePath;
    /**
     * Constructor for getting data from a properties file
     * @param filePath set path of the properties file
     */
    public PropertiesManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return java.util.Properties
     */
    public Properties getProps()
    {
        try
        {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            props.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e)
        {
            ExtentReports.appendToReport(e.getMessage());
        }
        return props;
    }

    public void setProperty(String property, String value)
    {

        try
        {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            props.load(fileInputStream);
            fileInputStream.close();
            props.setProperty(property,value);
            FileOutputStream fos = new FileOutputStream(file);
            props.store(fos,"file saved");
            fos.close();
        }catch (IOException e)
        {
            ExtentReports.appendToReport(e.getMessage());
        }

    }

    public static String getPropertyPom(String value)
    {
        Model model;
        FileReader reader;
        MavenXpp3Reader mavenreader;
        Properties properties = null;
        try
        {
            mavenreader = new MavenXpp3Reader();
            reader = new FileReader("pom.xml"); // <-- pomfile is your pom.xml
            model = mavenreader.read(reader);
            properties = model.getProperties();
        } catch(Exception e)
        {
            ExtentReports.appendToReport(e.getMessage());
        }
        assert properties != null;
        return properties.getProperty(value);
    }

    public static void setPropertyPom(String property, String value)
    {
        Model model;
        FileReader reader;
        MavenXpp3Reader mavenreader;
        Properties properties = null;
        try
        {
            mavenreader = new MavenXpp3Reader();
            reader = new FileReader("pom.xml"); // <-- pomfile is your pom.xml
            model = mavenreader.read(reader);
            properties = model.getProperties();
            properties.setProperty(property,value);
        } catch(Exception e)
        {
            ExtentReports.appendToReport(e.getMessage());
        }
    }
}
