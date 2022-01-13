package utente;

import controller.utente.ServletRimuoviPreferito;
import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
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

import static org.mockito.Mockito.*;

public class ServletRimuoviPreferitoTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private Prodotto prodotto;
    @Mock
    private Utente utente;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletRimuoviPreferito servletRimuoviPreferito;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletRimuoviPreferito = new ServletRimuoviPreferito(utenteDAO);
    }

   @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("1");
        //int codiceProdotto = Integer.parseInt(request.getParameter("value"));
        servletRimuoviPreferito.doGet(request, response);

    }

    @Test
    public void rimuoviProdottoDaiPreferitiTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        session.setAttribute("utente", prodotto);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(request.getParameter("value")).thenReturn("1");
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        servletRimuoviPreferito.rimuoviProdottoDaiPreferiti(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}
