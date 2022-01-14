package utente;

import controller.utente.ServletInvioMessaggio;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
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
import java.sql.Date;
import java.sql.Time;

import static org.mockito.Mockito.*;

public class ServletInvioMessaggioTest {


    private ServletInvioMessaggio servletInvioMessaggio;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;



    @Mock
    private Utente utente1;

    @Mock
    private MessaggioDAO messaggioDAO;

    @Mock
    private UtenteDAO utenteDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletInvioMessaggio = new ServletInvioMessaggio(messaggioDAO,utenteDAO);
    }


    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("firstname")).thenReturn("Alfredo");
        when(request.getParameter("lastname")).thenReturn("Cannavaro");
        when(request.getParameter("telefono")).thenReturn("3282015490");
        when(request.getParameter("email")).thenReturn("alfredo@libero.it");
        when(request.getParameter("messaggio")).thenReturn("Ho problemicon il login");

        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        Utente utente=new Utente();
        when(session.getAttribute("utente")).thenReturn(utente);

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/messaggioInviato.jsp")).thenReturn(requestDispatcher);


    }


    @Test
    public void invioMessaggioTest() throws ServletException, IOException {
    Messaggio messaggio=new Messaggio();
    Utente utente=new Utente();
    messaggio.setUtente(utente);
    messaggio.setTesto("asdasdasdas");
    messaggio.setCodiceMessaggio(2);
    messaggio.setOra(new Time(22,25,12));
    messaggio.setData(new Date(2000,04,02));
    servletInvioMessaggio.invioMessaggio(utente,messaggio);
    verify(messaggioDAO).insertMessaggio(messaggio);

    }


   @Test
    public void checkUtenteTestEqualsCognome(){

    }

    @Test
    public void checkUtenteTestEqualsTelefono(){

    }

    @Test
    public void checkUtenteTestEqualsEmail(){

    }

}
