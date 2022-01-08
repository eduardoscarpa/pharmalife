package utente;

import controller.utente.ServletMostraPref;
import controller.utente.ServletOrdini;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletOrdiniTest  {

    @Mock
    OrdineDAO ordineDAO;
    @Mock
    Ordine ordine;
    @Mock
    Utente utente;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletOrdini servletOrdini;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletOrdini = new ServletOrdini();
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        //when(request.getSession().getAttribute("utente")).thenReturn(session);
        session.setAttribute("utente", ordine);
        //Utente utente = new Utente();
        when(session.getAttribute("utente")).thenReturn(utente);
        //RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(requestDispatcher);
        servletOrdini.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}