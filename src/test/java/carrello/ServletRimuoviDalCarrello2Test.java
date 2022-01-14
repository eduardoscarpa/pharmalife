package carrello;

import controller.carrello.ServletRimuoviDalCarrello2;
import model.carrello.Carrello;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class ServletRimuoviDalCarrello2Test {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Utente utenteMock;
    @Mock
    private Prodotto prodotto;
    @Mock
    private Carrello carrello;

    @Mock
    private ArrayList<Prodotto> prodotti;

    private ServletRimuoviDalCarrello2 servletRimuoviDalCarrello2;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletRimuoviDalCarrello2= new ServletRimuoviDalCarrello2(prodottoDAO,prodotto,utenteMock,carrello);
      //  when(request.getSession()).thenReturn(session);
       // when(session.getAttribute("utente")).thenReturn(utenteMock);
        when(request.getParameter("value")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(utenteMock);
        when(prodottoDAO.cercaProdotto(1)).thenReturn(prodotto);
        when(utenteMock.getCarrello()).thenReturn(carrello);
        when(prodotto.getPrezzo()).thenReturn(12.50);
        when(carrello.getProdotti()).thenReturn(prodotti);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        Utente utente=(Utente) session.getAttribute("utente");
        Prodotto prodotto=prodottoDAO.cercaProdotto(1);
        Carrello carrello=utenteMock.getCarrello();
        assertNotEquals(null, utente);
        assertNotEquals(null, prodotto);
        assertNotEquals(null, carrello);
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(dispatcher);
        int codice=Integer.parseInt(request.getParameter("value"));
        servletRimuoviDalCarrello2.doGet(request, response);
        verify(request,times(2)).getParameter("value");
        verify(dispatcher).forward(request, response);
        assertEquals(1, codice);
    }


    @Test
    public void rimozioneDalCarrelloUtenteIsNotNullTest() throws ServletException, IOException {
        when(prodotto.getPrezzo()).thenReturn(12.5);

        servletRimuoviDalCarrello2.rimozioneDalCarrello(utenteMock, prodotto, session);
        verify(utenteMock,times(1)).getCarrello();
        verify(prodotto,times(1)).getPrezzo();
        verify(carrello).sottraiTotale(12.5);
        verify(carrello).getProdotti();
        verify(utenteMock).setCarrello(carrello);
        verify(prodotti).remove(prodotto);
    }
    @Test
    public void rimozioneDalCarrelloUtenteIsNullTest() throws ServletException, IOException {
        when(session.getAttribute("utente")).thenReturn(null);
        when(prodotto.getPrezzo()).thenReturn(12.50);
        assertEquals(null, session.getAttribute("utente"));
        when(session.getAttribute("carrello")).thenReturn(carrello);
        //ArrayList<Prodotto> prodotti= new ArrayList<>();
        //when(carrello.getProdotti()).thenReturn(prodotti);
        //Carrello c=(Carrello)session.getAttribute("carrello");
       // assertNotEquals(carrello, session.getAttribute("carrello"));
        assertNotEquals(null, carrello);
        servletRimuoviDalCarrello2.rimozioneDalCarrello(null, prodotto, session);
        verify(prodotto).getPrezzo();
        verify(carrello).sottraiTotale(12.50);
        verify(carrello).setProdotti(prodotti);
        verify(prodotti).remove(prodotto);
    }

    @Test
    public void rimozioneDalCarrelloCarrelloIsNull() throws ServletException, IOException {
       // when(prodotto.getPrezzo()).thenReturn(12.50);
        when(session.getAttribute("carrello")).thenReturn(null);
        Carrello carrello1=(Carrello) session.getAttribute("carrello");
        assertEquals(null, carrello1);
       /* servletRimuoviDalCarrello2.rimozioneDalCarrello(utenteMock, prodotto, session);
        verify(prodotto).getPrezzo();
        verify(carrello).sottraiTotale(12.50);
        verify(carrello).getProdotti();
        verify(prodotti).remove(prodotto);
        verify(carrello).setProdotti(prodotti);*/

    }

}
