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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

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
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("totale")).thenReturn("0");
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("prodotto")).thenReturn("1");
        int id=Integer.parseInt(request.getParameter("prodotto"));
        when(session.getAttribute("utente")).thenReturn(null);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(prodottoDAO.cercaProdotto(id)).thenReturn(prodotto);
        PrintWriter printWriter=mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        assertEquals(1,id);
        servletAggiungiAlCarrello.doGet(request, response);
        verify(request,times(2)).getParameter("prodotto");
        verify(session).getAttribute("utente");
        verify(session).getAttribute("carrello");
        verify(prodottoDAO).cercaProdotto(id);
        verify(response).getWriter();
        verify(printWriter).write("Prodotto aggiunto al carrello!");
    }

    @Test
    public void doGetUteneNotNullTest() throws ServletException, IOException {
        when(request.getParameter("prodotto")).thenReturn("1");
        int id=Integer.parseInt(request.getParameter("prodotto"));
        when(session.getAttribute("utente")).thenReturn(utente);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(prodottoDAO.cercaProdotto(id)).thenReturn(prodotto);
        PrintWriter printWriter=mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        assertEquals(1,id);
        servletAggiungiAlCarrello.doGet(request, response);
        verify(request,times(2)).getParameter("prodotto");
        verify(session).getAttribute("utente");
        verify(session).getAttribute("carrello");
        verify(prodottoDAO).cercaProdotto(id);
        verify(response).getWriter();
        verify(printWriter).write("Prodotto aggiunto al carrello!");
    }
    @Test
    public void aggiuntaAlCarrelloUtenteIsNotNullTest() throws IOException {
        when(utente.getCarrello()).thenReturn(carrello);
        int totale=Integer.parseInt(request.getParameter("totale"));
        Carrello carrello= utente.getCarrello();
        assertNotEquals(null,carrello );
        servletAggiungiAlCarrello.aggiuntaAlCarrello(1,session);
        verify(prodotto).setPrezzoQuantita(totale);
        verify(utente.getCarrello()).addProdotto(prodotto);
    }

    @Test
    public void aggiuntaAlCarrelloUtenteIstNullTest() throws IOException {
        when(utente.getCarrello()).thenReturn(carrello);
        int totale=Integer.parseInt(request.getParameter("totale"));
        Carrello carrello= utente.getCarrello();
        assertNotEquals(null,carrello );
        servletAggiungiAlCarrello.aggiuntaAlCarrello(1,session);
        verify(prodotto).setPrezzoQuantita(totale);
        verify(utente.getCarrello()).addProdotto(prodotto);
    }
}
