package utente;

import controller.utente.ServletAccessoUtente;
import model.carrello.Carrello;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletAccessoUtenteTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private Utente utente;
    @Mock
    private Carrello carrello;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    private ServletAccessoUtente servletAccessoUtente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAccessoUtente= new ServletAccessoUtente(utenteDAO,utente,carrello);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp")).thenReturn(dispatcher);
    }

    @Test
    public void doGetValueIsLoginTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("login");
        String val=request.getParameter("value");
        assertEquals("login", val);
        servletAccessoUtente.doGet(request, response);
        verify(request,times(2)).getParameter("value");


    }
    @Test
    public void doGetValueIsLogoutTest() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("logout");
        String val=request.getParameter("value");
        assertEquals("logout", val);
        servletAccessoUtente.doGet(request, response);
        verify(request,times(2)).getParameter("value");
    }
    @Test
    public void logoutUtenteTest() throws IOException {
        when(session.getAttribute("utente")).thenReturn(utente);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        Utente utente=(Utente) session.getAttribute("utente");
        assertNotEquals(null, utente);
        servletAccessoUtente.logoutUtente(request, response);
        verify(session,times(2)).getAttribute("utente");
        verify(session).removeAttribute("utente");
        verify(session).getAttribute("carrello");
        verify(session).removeAttribute("carrello");
    }
    @Test
    public void loginUtenteIsNullTest() throws ServletException, IOException {
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente1=(Utente) session.getAttribute("utente");
        assertEquals(null, utente1);
        when(request.getParameter("emailUser")).thenReturn("carmine");
        when(request.getParameter("password")).thenReturn("psw");
        String email=request.getParameter("emailUser");
        String password=request.getParameter("password");
        assertEquals("carmine",email);
        assertEquals("psw", password);
        when(utenteDAO.cercaUtentebyEmail(email, password)).thenReturn(utente);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        servletAccessoUtente.loginUtente(request, response);
        verify(utenteDAO).cercaUtentebyEmail(email, password);
        verify(session).getAttribute("carrello");
        verify(session).removeAttribute("carrello");

    }
}
