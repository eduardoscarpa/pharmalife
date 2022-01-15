package utente;

import controller.utente.ServletOrdini;
import model.carrello.Carrello;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
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
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletOrdiniTest  {

    @Mock
    OrdineDAO ordineDAO;
    @Mock
    Ordine ordine;
    @Mock
    Carrello carrello;
    @Mock
    Utente utente;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ArrayList<Prodotto> prodotti;

    private ServletOrdini servletOrdini;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletOrdini = new ServletOrdini(ordineDAO,ordine,utente);
    }

    @Test
    public void doPostSessionNotNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        utente.setCodiceFiscale("GFGHHH88U88V678G");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(utente.getCarrello()).thenReturn(carrello);
        ordine.setCarrello(carrello);
        Time time = new Time(11,30,0);
        ordine.setOra(time);
        Date date = new Date(2022, 0, 9);
        ordine.setDataOrdine(date);
        Time time1 = new Time(11, 30, 0);
        assertEquals(time1, time);
        Date date1 = new Date(2022, 0, 9);
        assertEquals(date1, date);

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(requestDispatcher);
        servletOrdini.doPost(request, response);
        verify(requestDispatcher).forward(request, response);

        verify(ordine).setOra(time);
        verify(ordine).setDataOrdine(date);
        verify(ordine,times(2)).setCarrello(utente.getCarrello());
        verify(session).setAttribute("utente", utente);
        ordineDAO.insertCarrello(ordine);
        verify(ordineDAO,times(2)).insertCarrello(ordine);
    }

    @Test
    public void doPostSessionNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(null);
        HttpSession session = request.getSession();
        servletOrdini.doPost(request,response);
        assertEquals(null, session);
    }

    @Test
    public void doPostUtenteNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        servletOrdini.doPost(request, response);
        assertEquals(null, utente);
        assertEquals(null, request.getAttribute("prodottiPref"));
    }

    @Test
    public void doPostCarrelloNullTest() {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        assertEquals(null, utente);
        assertEquals(null, request.getAttribute("prodottiPref"));
    }
}