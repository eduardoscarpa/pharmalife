package carrello;

import controller.carrello.ServletRimuoviDalCarrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;
import java.io.IOException;

import static org.mockito.Mockito.*;

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
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("1");
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(dispatcher);
        int codice=Integer.parseInt(request.getParameter("value"));
        servletRimuoviDalCarrello.doGet(request, response);
        verify(request,times(2)).getParameter("value");
        verify(dispatcher).forward(request, response);
        assertEquals(1, codice);


    }
}
