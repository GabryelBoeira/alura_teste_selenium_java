package br.com.alura.leilao.selenium.objectPage;

import org.openqa.selenium.By;

public class LoginPageObject extends PageObjectConfig {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILOES_2 = "http://localhost:8080/leiloes/2";

    public LoginPageObject() {
        super();
        getBrowser().navigate().to(URL_LOGIN);
    }

    public void preencherFormularioLogin(String username, String password) {
        getBrowser().findElement(By.id("username")).sendKeys(username);
        getBrowser().findElement(By.id("password")).sendKeys(password);
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().equalsIgnoreCase(URL_LOGIN);
    }

    public boolean isLoginPageError() {
        return browser.getCurrentUrl().equalsIgnoreCase(URL_LOGIN + "?error");
    }

    public void navegarParaPaginaDeLances() {
        navigateTo(URL_LEILOES_2);
    }

}
