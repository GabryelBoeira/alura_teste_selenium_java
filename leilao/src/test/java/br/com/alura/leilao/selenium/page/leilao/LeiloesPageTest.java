package br.com.alura.leilao.selenium.page.leilao;

import br.com.alura.leilao.selenium.objectPage.CadastroLeilaoPageObject;
import br.com.alura.leilao.selenium.objectPage.LeiloesPageObject;
import br.com.alura.leilao.selenium.objectPage.LoginPageObject;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LeiloesPageTest {

    private LeiloesPageObject leiloesPage;

    @AfterEach
    void afterEach() {
        leiloesPage.fechar();
    }

    @Test
    @DisplayName(value = "Deve criar um novo leilão")
    void deveCriarLeilao() {
        LoginPageObject paginaDeLogin = new LoginPageObject();
        paginaDeLogin.preencherFormularioLogin("fulano", "pass");
        this.leiloesPage = paginaDeLogin.submitForm();
        CadastroLeilaoPageObject paginaDeCadastro = leiloesPage.carregarFormulario();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";
        this.leiloesPage = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

}
