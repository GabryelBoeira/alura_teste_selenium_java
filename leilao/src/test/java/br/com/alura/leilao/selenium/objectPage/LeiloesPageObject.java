package br.com.alura.leilao.selenium.objectPage;

import org.openqa.selenium.By;

public class LeiloesPageObject extends PageObjectConfig {

    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes";

    public LeiloesPageObject() {
        super();
        getBrowser().navigate().to(URL_LEILOES);
    }

    public void carregarFormulario(String username, String password) {
        getBrowser().findElement(By.id("username")).sendKeys(username);
        getBrowser().findElement(By.id("password")).sendKeys(password);
    }

    public void preencherFormulario(String username, String password) {
        getBrowser().findElement(By.id("username")).sendKeys(username);
        getBrowser().findElement(By.id("password")).sendKeys(password);
    }
}
