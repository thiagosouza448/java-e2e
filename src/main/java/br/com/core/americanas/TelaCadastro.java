package br.com.core.americanas;

import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TelaCadastro extends DriverManager {


    private Base base;

    private By cmpEmail = By.id("email-input");
    private By cmpSenha = By.id("password-input");
    private By cmpNome = By.id("name-input");
    private By cmpNascimento = By.id("birthday-input");
    private By radioGenderM = By.xpath("//*[@id='gender']/div[1]/label");
    private By cmpTelefone = By.id("phone-input");
    private By cmpCPF = By.id("cpf-input");
    private By btnConfirmaCadastro = By.xpath("//*[contains(text(),'Criar seu cadastro')]");
    private By msgErroEmail = By.className("inputGroup-error");
    private By msgErroSenha = By.className("passwordWrapper-msg");
    private By msgErroCPF = By.xpath("//*[@id='cpf']/div[contains(text(),'Campo inválido')]");
    private By txtLogado = By.className("usr-nick");


    public TelaCadastro(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }


    public void preencherEmaileSenha(String email, String senha) {
        base.preencherTexto(cmpEmail, email);
        if (senha.isEmpty()) {
            base.preencherTexto(cmpSenha, "senhaTeste123");
        } else {
            base.preencherTexto(cmpSenha, senha);
        }
        ExtentReports.appendToReport(driver);
//        driver.findElement(cmpEmail).sendKeys(email);
//        driver.findElement(cmpSenha).sendKeys("senhaTeste123");
    }

    public void confirmaCadastro() {
        base.clicarElemento(btnConfirmaCadastro);
        ExtentReports.appendToReport(driver);
//        driver.findElement(btnConfirmaCadastro).click();
    }

    public void validaMsg(String msg) {
        WebElement mensagem;
        if ("E-mail já cadastrado".equals(msg)) {
            mensagem = base.procurarElemento(msgErroEmail);
        } else if ("Senha Fraca".equals(msg)) {
            mensagem = base.procurarElemento(msgErroSenha);
        } else if ("Campo inválido".equals(msg)) {
            mensagem = base.procurarElemento(msgErroCPF);
        } else {
            mensagem = base.procurarElemento(msgErroEmail);
        }


        if (mensagem.isDisplayed()) {
            String erro = mensagem.getText();
            ExtentReports.appendToReport(driver);
            Assert.assertEquals(erro, msg);
        } else {
            ExtentReports.appendToReport(driver);
            Assert.fail("Não foi exibida mensagem de erro!");
        }
    }

    public void preencherCPF(String cpf) {
        base.preencherTexto(cmpCPF, cpf);
    }

    public void preencherDadosCliente() {
        Faker faker = new Faker();
//        private By cmpNome = By.id("name-input");
//        private By cmpNascimento = By.id("birthday-input");
//        private By radioGenderM = By.id("gender_M");
//        private By cmpTelefone = By.id("phone-input");


        base.preencherTexto(cmpNome, faker.name().fullName());
        base.preencherTexto(cmpNascimento, "22/05/1997");
        base.preencherTexto(cmpTelefone, faker.phoneNumber().cellPhone());
        base.clicarElemento(radioGenderM);

    }

    public void clienteLogado() {
        WebElement nickUsuario = base.procurarElemento(txtLogado);
        if (nickUsuario == null) {
            Assert.fail("Erro : cliente não logado / cadastro não concluido.");
        }
    }

}
