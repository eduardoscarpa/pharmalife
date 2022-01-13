package carrello;

import controller.carrello.ServletAggiungiAlCarrello;
import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletAggiungiAlCarrelloTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private Carrello carrello;
    @Mock
    private Utente utente;
    @Mock
    private Prodotto prodotto;
    @Mock
    private  HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private ServletAggiungiAlCarrello servletAggiungiAlCarrello;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAggiungiAlCarrello= new ServletAggiungiAlCarrello(prodottoDAO,carrello,utente,prodotto);
    }

    @Test
    public void doGetTest(){

    }

    @Test
    public void aggiuntaAlCarrelloTest(){

    }
}
