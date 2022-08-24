package br.com.alura.leilao.selenium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumHelloWordTest {

    @BeforeAll
    static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver_104");
    }

    @Test
    void helloWord() {
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();
    }
}
