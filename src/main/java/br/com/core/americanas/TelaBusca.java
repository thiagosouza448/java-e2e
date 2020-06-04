package br.com.core.americanas;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TelaBusca extends DriverManager {


    private Base base;
    private By gridProdutos = By.xpath("//*[@data-component='productgrid']/div/div/div/div/div");


    public TelaBusca(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }

    public void selecionaProduto() {
        ExtentReports.appendToReport(driver);
        List<WebElement> produtosLista = base.procurarElementos(gridProdutos);
        if (produtosLista == null) {
            ExtentReports.appendToReport(driver);
            Assert.fail("Não houve retorno de produtos");
        }

        for (WebElement produto : produtosLista) {
            try {
                if (produto.isDisplayed()) {
                    produto.click();
                    break;
                }
            } catch (Exception e) {
            }

        }
        ExtentReports.appendToReport(driver);

    }


}
