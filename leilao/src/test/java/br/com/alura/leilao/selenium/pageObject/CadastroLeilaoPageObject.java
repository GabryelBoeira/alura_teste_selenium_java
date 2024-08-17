package br.com.alura.leilao.selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPageObject extends PageObjectConfig {

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPageObject(WebDriver browser) {
        super(browser);
    }

    /**
     * Cadastrar um novo leilão.
     *
     * @param  nome          o nome do leilão
     * @param  valorInicial o valor inicial do leilão
     * @param  dataAbertura a data de abertura do leilão
     * @return               uma instância de LeiloesPageObject representando a página de leilões após o cadastro
     */
    public LeiloesPageObject cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {

        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPageObject(browser);
    }

    /**
     * Verifies if there are any error messages on the page.
     *
     * @return true if there are error messages, false otherwise
     */
    public boolean isMensageErrors() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
    }

}