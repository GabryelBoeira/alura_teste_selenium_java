package br.com.alura.leilao.selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObjectConfig {

    protected static WebDriver browser;

    /**
     * Constructs a new PageObjectConfig object with the given WebDriver instance.
     *
     * @param  webDriver  the WebDriver instance to be used by this PageObjectConfig object
     */
    public PageObjectConfig(final WebDriver webDriver) {
        if (System.getProperty("os.name").contains("Windows"))
            System.setProperty("webdriver.chrome.driver", "drivers/windows/chrome/chromedriver.exe");
        else
            System.setProperty("webdriver.chrome.driver", "drivers/linux/chrome/chromedriver_104");

        if (webDriver == null)
            this.browser = new ChromeDriver();
        else
            this.browser = webDriver;
    }

    protected WebDriver getBrowser() {
        return browser;
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

    /**
     * Closes the browser instance.
     */
    public void fechar() {
        browser.quit();
    }

}
