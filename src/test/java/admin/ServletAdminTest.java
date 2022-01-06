package admin;

import controller.admin.ServletAdmin;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.OrdineDAO;
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
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
    private ArrayList<Messaggio> messaggi;


    private ServletAdmin servletAdmin;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAdmin= new ServletAdmin(messaggioDAO,utenteDAO,prodottoDAO,ordineDAO);
        messaggi= new ArrayList<>();
    }

    @Test
    public void doPostTest() throws ServletException, IOException {

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getParameter("value")).thenReturn("messaggi");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/assistenzaUtenti.jsp")).thenReturn(requestDispatcher);
        ServletAdmin servletAdmin= new ServletAdmin(messaggioDAO,utenteDAO,prodottoDAO,ordineDAO);
        servletAdmin.doPost(request,response);
        String val=request.getParameter("value");
        verify(requestDispatcher).forward(request, response);
        assertEquals("messaggi", val);
        //assertNotEquals("Non uguale", "messaggi", val);
    }

    @Test
    public void visualizzaMessaggiTest(){



        messaggi.add(new Messaggio());
        messaggi.add(new Messaggio());
        servletAdmin.setArrayMessaggi(messaggi);
        when(messaggioDAO.doRetrieveByAllMessaggi()).thenReturn(messaggi);
        int size=messaggioDAO.doRetrieveByAllMessaggi().size();
        //messaggioDAO.doRetrieveByAllMessaggi();
        servletAdmin.visualizzaMessaggi(request,response);
        verify(messaggioDAO).doRetrieveByAllMessaggi();
        assertEquals(2, size);
    }
}
