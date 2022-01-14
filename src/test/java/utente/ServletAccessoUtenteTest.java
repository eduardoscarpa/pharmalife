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


    private ServletAccessoUtente servletAccessoUtente;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAccessoUtente = new ServletAccessoUtente(utenteDAO,utente,carrello);
    }

    @Test
    public void doGetTestLogout() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
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
    public void loginTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
       when(request.getSession()).thenReturn(session);
        when(request.getParameter("value")).thenReturn("login");
        when(session.getAttribute("utente")).thenReturn(utente);
        when(session.getAttribute("carrello")).thenReturn(carrello);
        servletAccessoUtente.loginUtente(request, response);
        String val=request.getParameter("value");
        assertEquals("login",val);

       // verify(session).removeAttribute("carrello");

    }


    @Test
    public void loginUtenteIsNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp")).thenReturn(requestDispatcher);
        when(session.getAttribute("utente")).thenReturn(null);
        servletAccessoUtente.loginUtente(request, response);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void logoutUtenteUtenteNotNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
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
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        assertEquals(null,utente);
    }

    @Test
    public void logoutUtenteCarrelloIsNullTest(){
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("carrello")).thenReturn(null);
        Carrello carrello=(Carrello) session.getAttribute("carrello");
        assertEquals(null,carrello);
    }

}
