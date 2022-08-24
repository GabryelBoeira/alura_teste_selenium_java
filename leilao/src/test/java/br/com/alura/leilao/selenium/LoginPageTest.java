package br.com.alura.leilao.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILOES_2 = "http://localhost:8080/leiloes/2";

    @BeforeAll
    static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver_104");
    }

    @BeforeEach
    void beforeEach(){
        browser = new ChromeDriver();
    }

    @AfterEach
    void afterEach() {
        browser.quit();
    }

    @Test
    @DisplayName(value = "Deveria efeturar o login com os dados validos")
    void efetuarLoginComSucesso() {
        String username = "fulano";

        browser.navigate().to(URL_LOGIN);
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertFalse(browser.getCurrentUrl().equalsIgnoreCase(URL_LOGIN));
        assertEquals(username, browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    @DisplayName(value = "Nao pode permitir o login com usuario invalido")
    void naoEfetuarLoginComUsuarioInvalido() {
        browser.navigate().to(URL_LOGIN);
        browser.findElement(By.id("username")).sendKeys("username");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertTrue(browser.getCurrentUrl().equalsIgnoreCase( URL_LOGIN + "?error"));
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    @DisplayName(value = "Nao pode permitir o login com usuario com senha invalida")
    void naoEfetuarLoginComSenhaInvalido() {
        browser.navigate().to(URL_LOGIN);
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("10");
        browser.findElement(By.id("login-form")).submit();

        assertTrue(browser.getCurrentUrl().equalsIgnoreCase( URL_LOGIN + "?error"));
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    @DisplayName(value = "Nao pode acessar pagina que necessida de login sem estar com o usuario logado")
    void naoAcessarPaginaRestridaSemLogin() {
        browser.navigate().to(URL_LEILOES_2);

        assertTrue(browser.getCurrentUrl().equalsIgnoreCase(URL_LOGIN));
        assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}
