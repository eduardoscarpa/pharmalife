package admin;
import application.adminService.ServletAdmin;
import storage.messaggio.Messaggio;
import storage.messaggio.MessaggioDAO;
import storage.ordine.Ordine;
import storage.ordine.OrdineDAO;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
import storage.utente.Utente;
import storage.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletAdminTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private MessaggioDAO messaggioDAO;
    @Mock
    private OrdineDAO ordineDAO;
    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    private ArrayList<Messaggio> messaggi;
    private ArrayList<Utente> utenti;
    private ArrayList<Prodotto> prodotti;
    private ArrayList<Ordine> ordini;
    private ServletAdmin servletAdmin;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAdmin= new ServletAdmin(messaggioDAO,utenteDAO,prodottoDAO,ordineDAO);
        messaggi= new ArrayList<>();
        utenti= new ArrayList<>();
        prodotti= new ArrayList<>();
        ordini= new ArrayList<>();
    }

    @Test
    public void doPostValueIsMessaggiTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("messaggi");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/assistenzaUtenti.jsp")).thenReturn(dispatcher);
        servletAdmin.doPost(request,response);
        String val=request.getParameter("value");
        verify(dispatcher).forward(request, response);
        assertEquals("messaggi", val);
    }

    @Test
    public void doPostValueIsStatisticheTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("statistiche");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/statistiche.jsp")).thenReturn(dispatcher);
        servletAdmin.doPost(request,response);
        String val=request.getParameter("value");
        verify(dispatcher).forward(request, response);
        assertEquals("statistiche", val);
    }

    @Test
    public void doPostValueIsInsertProdottoTest() throws ServletException, IOException {

        when(request.getParameter("value")).thenReturn("insertProdotto");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/insertProdotto.jsp")).thenReturn(dispatcher);
        servletAdmin.doPost(request,response);
        String val=request.getParameter("value");
        verify(dispatcher).forward(request, response);
        assertEquals("insertProdotto", val);
    }

    @Test
    public void visualizzaMessaggiTest(){
        messaggi.add(new Messaggio());
        messaggi.add(new Messaggio());
        when(messaggioDAO.doRetrieveByAllMessaggi()).thenReturn(messaggi);
        int size=messaggi.size();
        servletAdmin.visualizzaMessaggi(request);
        verify(messaggioDAO).doRetrieveByAllMessaggi();
        assertEquals(2, size);
    }

    @Test
    public void visualizzaStatisticheTest(){
        utenti.add(new Utente());
        utenti.add(new Utente());
        when(utenteDAO.doRetrieveByAllUtenti()).thenReturn(utenti);
        int num = utenti.size();
        request.setAttribute("messaggi", num);
        servletAdmin.visualizzaStatistiche(request);
        verify(messaggioDAO).doRetrieveByAllMessaggi();
        verify(request).setAttribute("messaggi", num);
        assertEquals(2, num);
    }
}
