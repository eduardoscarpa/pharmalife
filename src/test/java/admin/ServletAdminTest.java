package admin;

import controller.admin.ServletAdmin;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.OrdineDAO;
import model.prodotto.ProdottoDAO;
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


    private ServletAdmin servletAdmin;
    @Before
    public void startUp(){
        MockitoAnnotations.initMocks(this);
        servletAdmin= new ServletAdmin(messaggioDAO,utenteDAO,prodottoDAO,ordineDAO);
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
       // HttpServletRequest request=mock(HttpServletRequest.class);
        //HttpServletResponse response=mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getParameter("value")).thenReturn("messaggi");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/assistenzaUtenti.jsp")).thenReturn(requestDispatcher);
        //ServletAdmin servletAdmin= new ServletAdmin();
        servletAdmin.doPost(request,response);
        String val=request.getParameter("value");
        verify(requestDispatcher).forward(request, response);
        assertEquals("messaggi", val);
        //assertNotEquals("Non uguale", "messaggi", val);
    }

    @Test
    public void visualizzaMessaggiTest(){
        ArrayList<Messaggio> messaggi=new ArrayList<>();
        messaggi.add(new Messaggio());
        messaggi.add(new Messaggio());
        messaggi.add(new Messaggio());
        when(messaggioDAO.doRetrieveByAllMessaggi()).thenReturn(messaggi);
        int size=messaggioDAO.doRetrieveByAllMessaggi().size();
        servletAdmin.visualizzaMessaggi(request,response);
        verify(messaggioDAO).doRetrieveByAllMessaggi();
        assertEquals(3, size);
    }
}
