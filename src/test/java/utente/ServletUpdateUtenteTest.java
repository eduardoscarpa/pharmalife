package utente;

import controller.utente.ServletIscrizione;
import controller.utente.ServletUpdateUtente;
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

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ServletUpdateUtenteTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletUpdateUtente servletUpdateUtente;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servletUpdateUtente = new ServletUpdateUtente(utenteDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("codiceFiscale")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("catello");
        when(request.getParameter("cognome")).thenReturn("staiano");
        when(request.getParameter("email")).thenReturn("cat@gmail.it");
        when(request.getParameter("password")).thenReturn("Catello1");
        when(request.getParameter("newPassword")).thenReturn("Catello1");
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/InfoUtente.jsp")).thenReturn(requestDispatcher);
        servletUpdateUtente.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void aggiornaDatiUtenteTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Utente utente = new Utente();
        utente.setCodiceFiscale("AFGHHH88U88V678Z");
        utente.setNome("carmine");
        utente.setCognome("sicignano");
        utente.setEmail("car@gmail.it");
        utente.setPassword("Carmine1");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(utenteDAO.cercaUtente(utente.getCodiceFiscale())).thenReturn(utente);
        String nome = utente.getNome();
        String password = utente.getPassword();
        String codiceFiscale = utente.getCodiceFiscale();
    }

    @Test
    public void passwordValidatorIsFalseTest() {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Utente utente = new Utente();
        utente.setPassword("Carmine");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(utenteDAO.cercaUtente(utente.getCodiceFiscale())).thenReturn(utente);
        String password = utente.getPassword();
        String nome = utente.getNome();
        assertFalse(ServletIscrizione.formatPassword(password));
    }
}
