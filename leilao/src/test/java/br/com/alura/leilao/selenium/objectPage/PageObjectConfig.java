package br.com.alura.leilao.selenium.objectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObjectConfig {

    protected static WebDriver browser;

    public PageObjectConfig() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_104");
        browser = new ChromeDriver();
    }

    protected WebDriver getBrowser() {
        return browser;
    }

    public void fechar() {
        browser.quit();
    }

    public void submitFormById(final String id) {
        browser.findElement(By.id(id)).submit();
    }

    public void findElementById(final String elementId) throws NoSuchElementException {
        browser.findElement(By.id(elementId));
    }

    public String getTextByElementId(final String elementId) throws NoSuchElementException {
        return browser.findElement(By.id(elementId)).getText();
    }

    public boolean findConteudoByPageSource(final String conteudo) {
        return browser.getPageSource().contains(conteudo);
    }

    public void navigateTo(final String url) {
        browser.navigate().to(url);
    }
}
