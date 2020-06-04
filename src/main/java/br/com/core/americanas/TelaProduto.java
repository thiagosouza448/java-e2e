package br.com.core.americanas;
import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TelaProduto extends DriverManager {


    private Base base;
    private By btnComprar = By.id("btn-buy");



    public TelaProduto(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);

    }

    public void clicarComprar() {
        base.clicarElemento(btnComprar);
        ExtentReports.appendToReport(driver);
    }
}
