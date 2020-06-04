package br.com.core.americanas;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TelaCesta extends DriverManager {


    private Base base;
    private By btnIncluiCesta = By.xpath("//*[@type='button'][contains(text(),'Incluir na cesta')]");
    private By textParcelamento = By.className("summary-totalInstallments");
    private By textValorTotal = By.className("summary-total");
    private By txtEsgotado = By.xpath("//*[@class='alert-content']/p");
    private By produtosCarrinho = By.xpath("//li[@class='basket-product']");

    public TelaCesta(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }


    public void incluirItemCesta() {
        ExtentReports.appendToReport(driver);
        List<WebElement> produtosIncluirCesta = base.procurarElementos(btnIncluiCesta);
        if (produtosIncluirCesta == null) {
            Assert.fail("Não há produtos para adicionar na cesta");
        }

        for (WebElement produto : produtosIncluirCesta) {
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



    public void validarParcelamento(String numeroParcelas) {
        String parcelas = base.procurarElemento(textParcelamento).getText();

        if (!parcelas.contains(numeroParcelas)) {
            ExtentReports.appendToReport(driver);
            Assert.fail("Não foi disponibilizado o numero de parcelas " + numeroParcelas + " para a compra");
        }
    }

    public void validarValorTotal(String totalCompra) {
        ExtentReports.appendToReport(driver);
        List<WebElement> alertasContent = base.procurarElementos(txtEsgotado);
        for(WebElement alerta : alertasContent){
            String textAlerta = alerta.getText();
            if (textAlerta.contains("Esgotado")){
                Assert.fail("Produto esgotado!");
            }
        }

        String valorTexto = base.procurarElemento(textValorTotal).getText();
        valorTexto = valorTexto.replace(".", "");
        valorTexto = valorTexto.replace(",", ".");
        valorTexto = valorTexto.replace("R$ ", "");
        Double valorSite = Double.parseDouble(valorTexto);
        if (valorSite > Double.parseDouble(totalCompra)) {
            ExtentReports.appendToReport(driver);
            Assert.fail("Valor apresentado no site " + valorSite + " é maior do que o esperado " + totalCompra);
        }
    }




    public void valiValidarProdutoCarrinho(String produto) {

        List<WebElement> ProdutosLista = base.procurarElementos(produtosCarrinho);
        if (ProdutosLista == null) {
            Assert.fail("Nenhum produto");

        }

        for (WebElement Prod : ProdutosLista) {
            String Item  = Prod.getText().toLowerCase();
            if(Item.contains(produto.toLowerCase())){
                ExtentReports.appendToReport(driver);
            }
            else{
                Assert.fail("Produto não encontrado na lista");
            }


        }


    }
}

