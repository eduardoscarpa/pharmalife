package utente;

import controller.utente.ServletMostraPref;
import controller.utente.ServletUpdateIndirizzo;
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

public class ServletUpdateIndirizzoTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletUpdateIndirizzo servletUpdateIndirizzo;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletUpdateIndirizzo = new ServletUpdateIndirizzo(utenteDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("via")).thenReturn("ponte");
        when(request.getParameter("numero")).thenReturn("123");
        when(request.getParameter("cap")).thenReturn("80050");
        when(request.getParameter("codiceFiscale")).thenReturn("GFGHHH88U88V678G");
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/updateIndirizzo.jsp")).thenReturn(requestDispatcher);
        servletUpdateIndirizzo.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        String via = request.getParameter("via");
        assertEquals("ponte", via);
        /*
        int numeroCivico = Integer.parseInt(request.getParameter("numero"));
        assertEquals(123, numeroCivico);
        String cap = request.getParameter("cap");
        assertEquals("80050", cap);
        String codice = request.getParameter("codiceFiscale");
        assertEquals("GFGHHH88U88V678G", codice);
         */
    }

    @Test
    public void aggiornaIndirizzoUtenteTest() throws ServletException, IOException {
        Utente utente = new Utente();
        utente.setVia("pontone");
        utente.setNumeroCivico(234);
        utente.setCap("80000");
        utente.setCodiceFiscale("AFGHHH88U88V678Z");
        String via = utente.getVia();
        int numCivico = utente.getNumeroCivico();
        String cap = utente.getCap();
        String codFiscale = utente.getCodiceFiscale();
        utenteDAO.updateIndirizzoUtente(utente);
        verify(utenteDAO).updateIndirizzoUtente(utente);
        assertEquals("pontone", via);
        assertEquals(234, numCivico);
        assertEquals("80000", cap);
        assertEquals("AFGHHH88U88V678Z", codFiscale);
    }
}
