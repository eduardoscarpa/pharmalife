package utente;

import controller.utente.ServletAccessoUtente;
import model.carrello.Carrello;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class ServletAccessoUtenteTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private UtenteDAO utenteDAO;

    @Mock
    private Utente utente;

    @Mock
    private Carrello carrello;

    @Mock
    private HttpServletResponse response;

    private HttpSession session;
    private RequestDispatcher dispatcher;

    private ServletAccessoUtente servletAccessoUtente;


    @Before
    public void setUp(){
        session=mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        MockitoAnnotations.initMocks(this);
        servletAccessoUtente = new ServletAccessoUtente(utenteDAO,utente,carrello);
    }

    @Test
    public void doGetTestLogin() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("value")).thenReturn("login");
        when(request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp")).thenReturn(dispatcher);
        String val = request.getParameter("value");
        servletAccessoUtente.doGet(request, response);
        assertEquals("login", val);
        verify(request, times(2)).getParameter("value");
    }

    @Test
    public void doGetTestLogout() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("value")).thenReturn("logout");
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        String val = request.getParameter("value");
        servletAccessoUtente.doGet(request, response);
        assertEquals("logout", val);
        verify(request, times(2)).getParameter("value");
    }

    @Test
    public void logoutUtenteTest() throws ServletException, IOException {
        when(session.getAttribute("carrello")).thenReturn(carrello);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("value")).thenReturn("logout");
        servletAccessoUtente.logoutUtente(request,response);
        String val=request.getParameter("value");
        assertEquals("logout",val );
        assertNotEquals(null,carrello);
        assertNotEquals(null,utente);

       verify(session).getAttribute("carrello");
       verify(session).removeAttribute("carrello");
        verify(session).removeAttribute("utente");
    }

    @Test
    public void loginUtenteTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("value")).thenReturn("login");
        when(session.getAttribute("utente")).thenReturn(utente);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        assertNotEquals(null,session.getAttribute("utente"));
        assertNotEquals(null,session.getAttribute("carrello"));
        servletAccessoUtente.loginUtente(request, response);
        String val=request.getParameter("value");
        assertEquals("login",val);
    }


    @Test
    public void loginUtenteIsNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp")).thenReturn(dispatcher);
        when(session.getAttribute("utente")).thenReturn(null);
        servletAccessoUtente.loginUtente(request, response);
        verify(dispatcher).forward(request, response);
    }


    @Test
    public void logoutUtenteUtenteNotNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente utente= new Utente();
        utente.setCodiceFiscale("BBRRTUAN");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        Carrello carrello=new Carrello();
        carrello.addProdotto(new Prodotto());
        carrello.addProdotto(new Prodotto());
        when(session.getAttribute("carrello")).thenReturn(carrello);
        assertNotEquals(null, carrello);
        assertNotEquals(null, utente);
    }


    @Test
    public void logoutUtenteUtenteIsNullTest(){
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        assertEquals(null,utente);
    }

    @Test
    public void logoutUtenteCarrelloIsNullTest(){
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("carrello")).thenReturn(null);
        Carrello carrello=(Carrello) session.getAttribute("carrello");
        assertEquals(null,carrello);
    }

}
