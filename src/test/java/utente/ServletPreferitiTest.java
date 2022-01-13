package utente;

import controller.admin.ServletAdmin;
import controller.utente.ServletPreferiti;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
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
import java.io.PrintWriter;
import java.io.StringWriter;
//import java.io.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletPreferitiTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private Prodotto prodotto;
    @Mock
    private Utente utente;

    private ServletPreferiti servletPreferiti;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servletPreferiti = new ServletPreferiti(prodottoDAO, utenteDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("1");
        servletPreferiti.doGet(request, response);
    }

    @Test
    public void inserisciProdottoAiPreferitiTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        //HttpServletResponse response=mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(session);
        session.setAttribute("utente", prodotto);
        when(session.getAttribute("utente")).thenReturn(prodotto);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(request.getParameter("value")).thenReturn("1");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        //String risposta = "Prodotto aggiunto ai tuoi preferiti!";
        when(response.getWriter()).thenReturn(writer);
        servletPreferiti.inserisciProdottoAiPreferiti(request, response);
    }
}
