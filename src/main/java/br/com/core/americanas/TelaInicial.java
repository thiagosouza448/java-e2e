package br.com.core.americanas;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaInicial extends DriverManager {

    private Base base;
    private By lnkCadastro = By.className("usr-act-text");
    private By lnkClienteNovo = By.className("usr-signup");
    private By cmpBusca = By.id("h_search-input");
    private By btnSubmit = By.id("h_search-btn");

    public TelaInicial(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }

    public void entrarTelaCadastro() {
        base.clicarElemento(lnkCadastro);

        base.clicarElemento(lnkClienteNovo);


//        driver.findElement(lnkCadastro).click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(lnkClienteNovo));
//        driver.findElement(lnkClienteNovo).click();
    }


    public void buscarProduto(String produto) {
        base.preencherTexto(cmpBusca, produto);

        base.clicarElemento(btnSubmit);

    }

}
