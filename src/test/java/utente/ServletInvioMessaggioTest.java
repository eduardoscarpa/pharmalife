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
    private Utente utente;
    @Mock
    private Messaggio messaggio;
    @Mock
    private HttpSession session;
    @Mock
    private MessaggioDAO messaggioDAO;
    @Mock
    private UtenteDAO utenteDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        session = mock(HttpSession.class);
        utente = mock(Utente.class);
        messaggio = mock(Messaggio.class);
        servletInvioMessaggio = new ServletInvioMessaggio(messaggioDAO, utenteDAO, messaggio, utente);
        when(utente.getNome()).thenReturn("Catello");
        when(utente.getCognome()).thenReturn("Scarpa");
        when(utente.getTelefono()).thenReturn("23423424");
        when(utente.getEmail()).thenReturn("eduardo@gmail.com");

    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("firstname")).thenReturn("Alfredo");
        when(request.getParameter("lastname")).thenReturn("Cannavaro");
        when(request.getParameter("telefono")).thenReturn("3282015490");
        when(request.getParameter("email")).thenReturn("alfredo@libero.it");
        when(request.getParameter("messaggio")).thenReturn("Ho problemi con il login");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(utente);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/messaggioInviato.jsp")).thenReturn(requestDispatcher);
        servletInvioMessaggio.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void invioMessaggioTest() throws ServletException, IOException {
        messaggio.setUtente(utente);
        messaggio.setTesto("asdasdasdas");
        messaggio.setCodiceMessaggio(2);
        messaggio.setOra(new Time(22, 25, 12));
        messaggio.setData(new Date(2000, 04, 02));
        servletInvioMessaggio.invioMessaggio(utente, messaggio);
        verify(messaggioDAO).insertMessaggio(messaggio);
    }
}
