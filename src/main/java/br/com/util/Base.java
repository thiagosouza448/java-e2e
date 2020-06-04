package br.com.util;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class Base extends DriverManager {

    public Base(WebDriver driver) {
        this.driver = driver;
    }


    public void clicarElemento(By elemento) {
        esperarElemento(elemento, 15);
        if (elemento != null) {
            if (driver.findElement(elemento).isDisplayed()) {
                try {
                    driver.findElement(elemento).click();
                    ExtentReports.appendToReport(driver);
                } catch (Exception e) {

                    if (driver.findElement(elemento).isDisplayed()) {
                        WebElement element = driver.findElement(elemento);
                        JavascriptExecutor executor = (JavascriptExecutor)driver;
                        executor.executeScript("arguments[0].click();", element);

                    }
                    else{
                        ExtentReports.appendToReport(driver);
                        Assert.fail("Elemento não clicavel.");
                    }




                }

            } else {
                ExtentReports.appendToReport(driver);
                Assert.fail("Elemento não encontrado");
            }
        }
        ExtentReports.appendToReport(driver);
    }

    public void preencherTexto(By elemento, String texto) {
        esperarElemento(elemento, 15);
        if (texto == null || texto == "") {
            return;
        }
        esperarElemento(elemento, 15);
        ExtentReports.appendToReport(driver);
        procurarElemento(elemento).sendKeys(texto);
        ExtentReports.appendToReport(driver);

    }

    public WebElement procurarElemento(By locator) {
        esperarElemento(locator, 15);
        try {
            WebElement elemento = driver.findElement(locator);
            return elemento;
        } catch (Exception e) {
            return null;
        }
    }


    public List<WebElement> procurarElementos(By locator) {
        esperarElemento(locator, 15);
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            return null;
        }

    }

    public void esperarElemento(By locator, Integer time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {

        }
    }

}
