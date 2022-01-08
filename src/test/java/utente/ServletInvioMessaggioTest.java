package utente;

import controller.utente.ServletAccessoUtente;
import controller.utente.ServletInvioMessaggio;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServletInvioMessaggioTest {


    private ServletInvioMessaggio servletInvioMessaggio;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletInvioMessaggio = new ServletInvioMessaggio();
    }


    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("firstname")).thenReturn("Alfredo");
        when(request.getParameter("lastname")).thenReturn("Cannavaro");
        when(request.getParameter("telefono")).thenReturn("3282015490");
        when(request.getParameter("email")).thenReturn("alfredo@libero.it");
        when(request.getParameter("messaggio")).thenReturn("Ho problemicon il login");
        HttpSession session=mock(HttpSession.class);
        Utente utente=new Utente();
        when(session.getAttribute("utente")).thenReturn(utente);

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
    }


    @Test
    public void invioMessaggioTest(){

    }
    @Test
    public void checkUtenteTest(){

    }
}
