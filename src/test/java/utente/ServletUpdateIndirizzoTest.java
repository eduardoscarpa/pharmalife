package utente;

import controller.utente.ServletUpdateIndirizzo;
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
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletUpdateIndirizzoTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Utente utente;

    private String via;
    private int numeroCivico;
    private String cap;
    private String codiceFiscale;

    private ServletUpdateIndirizzo servletUpdateIndirizzo;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletUpdateIndirizzo = new ServletUpdateIndirizzo(utenteDAO, utente);
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
        String numero=request.getParameter("numero");
        String cap=request.getParameter("cap");
        String codiceFiscale=request.getParameter("codiceFiscale");
        assertEquals("ponte", via);
        assertEquals("123", numero);
        assertEquals("cap", "80050");
        assertEquals("GFGHHH88U88V678G", codiceFiscale);
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

    @Test
    public void aggiornaIndirizzoUtenteTestIsTrue() throws ServletException, IOException {
        utente.setVia("Via Dante");
        utente.setNumeroCivico(24);
        utente.setCap("80058");
        utente.setCodiceFiscale("SAD232DWQF23");
        when(utenteDAO.updateIndirizzoUtente(utente)).thenReturn(true);
        servletUpdateIndirizzo.aggiornaIndirizzoUtente(via, numeroCivico, cap, codiceFiscale, request, response);
        verify(request).setAttribute("updateAddress","Il nuovo indirizzo è stato aggiornato correttamente.");
    }
}
