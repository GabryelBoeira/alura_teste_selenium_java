package br.com.alura.leilao.selenium.page.leilao;

import br.com.alura.leilao.selenium.pageObject.CadastroLeilaoPageObject;
import br.com.alura.leilao.selenium.pageObject.LeiloesPageObject;
import br.com.alura.leilao.selenium.pageObject.LoginPageObject;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LeiloesPageTest {

    private LeiloesPageObject leiloesPage;
    private CadastroLeilaoPageObject cadastroLeiloesPage;

    @BeforeEach
    void beforeEach() {
        LoginPageObject paginaDeLogin = new LoginPageObject();
        paginaDeLogin.preencherFormularioLogin("fulano", "pass");
        this.leiloesPage = paginaDeLogin.submitForm();
        this.cadastroLeiloesPage = leiloesPage.carregarFormulario();
    }

    @AfterEach
    void afterEach() {
        leiloesPage.fechar();
    }

    @Test
    @DisplayName(value = "Deveria criar um novo leilão")
    void deveCriarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = cadastroLeiloesPage.cadastrarLeilao(nome, valor, hoje);
        Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    @DisplayName(value = "Deveria validar o cadastro de leilão")
    void validarCadastroLeilao() {
        this.leiloesPage = cadastroLeiloesPage.cadastrarLeilao("", "", "");

        Assert.assertTrue(this.leiloesPage.isPaginaAtual());
        Assert.assertFalse(this.cadastroLeiloesPage.isPaginaAtual());
        Assert.assertTrue(this.cadastroLeiloesPage.isMensageErrors());
    }

}
