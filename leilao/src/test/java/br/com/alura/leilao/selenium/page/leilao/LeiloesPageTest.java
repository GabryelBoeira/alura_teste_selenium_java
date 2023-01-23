package br.com.alura.leilao.selenium.page.leilao;

import br.com.alura.leilao.selenium.objectPage.LeiloesPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class LeiloesPageTest {

    private LeiloesPageObject leiloesPage;

    @BeforeEach
    void beforeEach() {
        this.leiloesPage = new LeiloesPageObject();
    }

    @AfterEach
    void afterEach() {
        leiloesPage.fechar();
    }


}
