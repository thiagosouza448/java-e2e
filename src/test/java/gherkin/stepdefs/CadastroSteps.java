package gherkin.stepdefs;

import br.com.core.americanas.TelaCadastro;
import br.com.core.americanas.TelaInicial;
import br.com.core.setup.DriverManager;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class CadastroSteps extends DriverManager {

    private TelaCadastro tela_cadastro;
    private TelaInicial tela_inicial;

    public CadastroSteps() {
        tela_cadastro = new TelaCadastro(driver);
        tela_inicial = new TelaInicial(driver);
    }


    @Dado("^que eu acesso a pagina de cadastro$")
    public void queEuAcessoAPaginaDeCadastro() {
        tela_inicial.entrarTelaCadastro();
    }

    @E("^preencho os dados de email \"([^\"]*)\" e senha para cadastro \"([^\"]*)\"$")
    public void preenchoOsDadosDeEmailESenhaParaCadastro(String email, String senha) throws Throwable {
        tela_cadastro.preencherEmaileSenha(email, senha);
    }

    @Quando("^eu confirmar o cadastro$")
    public void euConfirmarOCadastro() {
        tela_cadastro.confirmaCadastro();
    }

    @Entao("^devo ver a mensagem \"([^\"]*)\"$")
    public void devoVerAMensagem(String msg) throws Throwable {
        tela_cadastro.validaMsg(msg);
    }


    @E("^preencho o CPF \"([^\"]*)\"$")
    public void preenchoOCPF(String cpf) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        tela_cadastro.preencherCPF(cpf);
    }

    @E("^preencho os dados de nome, sobrenome, nascimento, sexo e telefone$")
    public void preenchoOsDadosDeNomeSobrenomeNascimentoSexoETelefone() {
        tela_cadastro.preencherDadosCliente();
    }


    @Entao("^serei direcionado para tela inicial e estarei logado automaticamente$")
    public void sereiDirecionadoParaTelaInicialEEstareiLogadoAutomaticamente() {
        tela_cadastro.clienteLogado();
    }
}
