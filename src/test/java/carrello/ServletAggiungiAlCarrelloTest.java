package carrello;

import controller.carrello.ServletAggiungiAlCarrello;
import model.prodotto.ProdottoDAO;
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
    private  HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private ServletAggiungiAlCarrello servletAggiungiAlCarrello;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAggiungiAlCarrello= new ServletAggiungiAlCarrello(prodottoDAO);
    }

    @Test
    public void doGetTest(){

    }

    @Test
    public void aggiuntaAlCarrelloTest(){

    }
}
