package br.com.core.americanas;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaGarantia extends DriverManager {

    private Base base;
    private By btnContinue = By.xpath("//*[@id='btn-continue']/div");

    public TelaGarantia(WebDriver driver) {
        this.driver = driver;

        base = new Base(driver);
    }

    public void clicarContinuar() {
        base.clicarElemento(btnContinue);
        ExtentReports.appendToReport(driver);
    }
}
