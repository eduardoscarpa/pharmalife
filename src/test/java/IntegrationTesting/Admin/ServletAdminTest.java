package IntegrationTesting.Admin;

import controller.admin.ServletAdmin;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class ServletAdminTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private MessaggioDAO messaggioDAO;
    private UtenteDAO utenteDAO;
    private OrdineDAO ordineDAO;
    private ProdottoDAO prodottoDAO;
    private ArrayList<Messaggio> messaggi;
    private ArrayList<Utente> utenti;
    private ArrayList<Prodotto> prodotti;
    private ArrayList<Ordine> ordini;

    private ServletAdmin servletAdmin;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        servletAdmin= new ServletAdmin();
        messaggi= new ArrayList<>();
        utenti= new ArrayList<>();
        prodotti= new ArrayList<>();
        ordini= new ArrayList<>();
        messaggioDAO= new MessaggioDAO();
        ordineDAO = new OrdineDAO();
        prodottoDAO= new ProdottoDAO();
        utenteDAO=new UtenteDAO();
    }


    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("insertProdotto");
        String val=request.getParameter("value");
        assertEquals("insertProdotto", val);
    }

    @Test
    public void visualizzaStatisticheTest() throws SQLException {
        servletAdmin.visualizzaStatistiche(request, response);
        messaggi=messaggioDAO.doRetrieveByAllMessaggi();
        utenti=utenteDAO.doRetrieveByAllUtenti();
        ordini=ordineDAO.doRetraiveByAllOrdini();
        prodotti=prodottoDAO.doRetraiveByAllProdotti();
        int sizeMessaggi=messaggi.size();
        int sizeUtenti=utenti.size();
        int sizeProdotti=prodotti.size();
        int sizeOrdini=ordini.size();
        verify(request).setAttribute("messaggi", sizeMessaggi);
        verify(request).setAttribute("utenti",sizeUtenti);
        verify(request).setAttribute("prodotti", sizeProdotti);
        verify(request).setAttribute("ordini", sizeOrdini);
    }

    @Test
    public void visualizzaMessaggi(){
        messaggi=messaggioDAO.doRetrieveByAllMessaggi();
        servletAdmin.visualizzaMessaggi(request, response);
        request.setAttribute("messaggi", messaggi);
    }



}
