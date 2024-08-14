package br.com.alura.leilao.selenium.objectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPageObject extends PageObjectConfig {

    public CadastroLeilaoPageObject(WebDriver browser) {
        super(browser);
    }

    public LeiloesPageObject cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {

        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPageObject(browser);
    }

}