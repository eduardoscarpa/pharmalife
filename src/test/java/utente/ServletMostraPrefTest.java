package utente;

import controller.utente.ServletMostraPref;
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
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class ServletMostraPrefTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletMostraPref servletMostraPref;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletMostraPref = new ServletMostraPref(utenteDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/pagine/preferiti.jsp")).thenReturn(requestDispatcher);
        servletMostraPref.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void visualizzaPreferitiSessionNotNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Utente utente= new Utente();
        utente.setCodiceFiscale("BBRRTUAN");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        ArrayList<Prodotto> preferiti= new ArrayList<>();
        preferiti.add(new Prodotto());
        preferiti.add(new Prodotto());
        when(utenteDAO.doRetrieveByAllPreferitiOfUtente(utente.getCodiceFiscale())).thenReturn(preferiti);
        when(request.getAttribute("utente")).thenReturn(utente);
        servletMostraPref.visualizzaPreferiti(request);
        verify(request).getSession();
        verify(session).getAttribute("utente");
        verify(utenteDAO).doRetrieveByAllPreferitiOfUtente(utente.getCodiceFiscale());
        verify(request).setAttribute("prodottiPref", preferiti);
        assertNotEquals(null, request.getAttribute("utente"));
        //assertEquals(utente, request.getAttribute("utente"));
    }

    @Test
    public void visualizzaPreferitiSessionIsNullTest() throws ServletException, IOException {
       // HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(null);
        HttpSession session=request.getSession();
        servletMostraPref.visualizzaPreferiti(request);
        assertEquals(null, session);
    }

    @Test
    public void visualizzaPreferitiUtenteIsNullTest(){
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        assertEquals(null, utente);
        assertEquals(null, request.getAttribute("prodottiPref"));
    }


}