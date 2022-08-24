package br.com.alura.leilao.selenium.pages;

import br.com.alura.leilao.selenium.objectPage.LoginPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    private LoginPageObject loginPage;

    @BeforeEach
    void beforeEach() {
        this.loginPage = new LoginPageObject();
    }

    @AfterEach
    void afterEach() {
        loginPage.fechar();
    }

    @Test
    @DisplayName(value = "Deveria efeturar o login com os dados validos")
    void efetuarLoginComSucesso() {
        loginPage.preencherFormularioLogin("fulano", "pass");
        loginPage.submitFormById("login-form");

        assertFalse(loginPage.isLoginPage());
        assertEquals("fulano", loginPage.getTextByElementId("usuario-logado"));
    }

    @Test
    @DisplayName(value = "Nao pode permitir o login com usuario invalido")
    void naoEfetuarLoginComUsuarioInvalido() {
        loginPage.preencherFormularioLogin("username", "pass");
        loginPage.submitFormById("login-form");

        assertTrue(loginPage.isLoginPageError());
        assertTrue(loginPage.findConteudoByPageSource("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> loginPage.findElementById("usuario-logado"));
    }

    @Test
    @DisplayName(value = "Nao pode permitir o login com usuario com senha invalida")
    void naoEfetuarLoginComSenhaInvalido() {
        loginPage.preencherFormularioLogin("fulano", "10");
        loginPage.submitFormById("login-form");

        assertTrue(loginPage.isLoginPageError());
        assertTrue(loginPage.findConteudoByPageSource("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> loginPage.findElementById("usuario-logado"));
    }

    @Test
    @DisplayName(value = "Nao pode acessar pagina que necessida de login sem estar com o usuario logado")
    void naoAcessarPaginaRestridaSemLogin() {
        loginPage.navegarParaPaginaDeLances();

        assertTrue(loginPage.isLoginPage());
        assertFalse(loginPage.findConteudoByPageSource("Dados do Leilão"));
    }

}
