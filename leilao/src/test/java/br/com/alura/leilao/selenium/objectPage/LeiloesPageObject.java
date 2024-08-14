package br.com.alura.leilao.selenium.objectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPageObject extends PageObjectConfig {

    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public LeiloesPageObject(WebDriver browser) {
        super(browser);
        getBrowser().navigate().to(URL_LEILOES);
    }

    public LeiloesPageObject() {
        super();
        getBrowser().navigate().to(URL_LEILOES);
    }

    public void cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
            WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
            WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
            WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
            WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

            return colunaNome.getText().equals(nome)
                    && colunaDataAbertura.getText().equals(data)
                    && colunaValorInicial.getText().equals(valor);
    }


    public CadastroLeilaoPageObject carregarFormulario() {
        browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPageObject(browser);
    }

}
