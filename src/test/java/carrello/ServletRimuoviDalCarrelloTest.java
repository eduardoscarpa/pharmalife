package carrello;

import controller.carrello.ServletRimuoviDalCarrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;

public class ServletRimuoviDalCarrelloTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Utente utente;
    @Mock
    private Prodotto prodotto;

    private ServletRimuoviDalCarrello servletRimuoviDalCarrello;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletRimuoviDalCarrello= new ServletRimuoviDalCarrello(prodottoDAO,prodotto,utente);
    }

    @Test
    public void doGetTest(){
        when(request.getParameter("value")).thenReturn("1");

    }
}
