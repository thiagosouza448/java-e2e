package gherkin.stepdefs;

import br.com.core.americanas.*;
import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class CompraSteps extends DriverManager {

    private TelaBusca tela_busca;
    private TelaProduto tela_produto;
    private TelaGarantia tela_garantia;
    private TelaCesta tela_cesta;
    private TelaInicial tela_inicial;

    public CompraSteps() {
        tela_busca = new TelaBusca(driver);
        tela_produto = new TelaProduto(driver);
        tela_garantia = new TelaGarantia(driver);
        tela_cesta = new TelaCesta(driver);
        tela_inicial = new TelaInicial(driver);
    }

    @Dado("^que eu busco pelo produto \"([^\"]*)\"$")
    public void queEuBuscoPeloProduto(String produto) throws Throwable {
        tela_inicial.buscarProduto(produto);

    }

    @E("^seleciono o primeiro retorno$")
    public void selecionoOPrimeiroRetorno() {
        tela_busca.selecionaProduto();
    }



    @E("^incluir mais um produto na cesta$")
    public void incluirMaisUmProdutoNaCesta() {
        tela_cesta.incluirItemCesta();
    }




    @Quando("^clico em comprar$")
    public void clicoEmComprar() {
        tela_produto.clicarComprar();
        tela_garantia.clicarContinuar();
    }

    @Entao("^valido o \"([^\"]*)\" no carrinho$")
    public void validoONoCarrinho(String produto) throws Throwable {
        tela_cesta.valiValidarProdutoCarrinho(produto);
    }
}
