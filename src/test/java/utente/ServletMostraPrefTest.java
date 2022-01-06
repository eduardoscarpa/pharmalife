package utente;

import controller.admin.ServletAdmin;
import controller.utente.ServletMostraPref;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;
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
import static org.mockito.Mockito.*;

public class ServletMostraPrefTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private ServletMostraPref servletMostraPref;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ArrayList<Prodotto> preferiti;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletMostraPref = new ServletMostraPref(utenteDAO);
        preferiti = new ArrayList<>();
        servletMostraPref.setArrayPreferiti(preferiti);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        //request = mock(HttpServletRequest.class);
        //response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/pagine/preferiti.jsp")).thenReturn(requestDispatcher);
        servletMostraPref.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void visualizzaPreferitiTest() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn("GFGHHH88U88V678G");
        preferiti.add(new Prodotto());
        preferiti.add(new Prodotto());
        when(utenteDAO.doRetrieveByAllPreferitiOfUtente("GFGHHH88U88V678G")).thenReturn(preferiti);
        int numPref = utenteDAO.doRetrieveByAllPreferitiOfUtente("GFGHHH88U88V678G").size();
        //continuare
        //servletMostraPref.visualizzaPreferiti(request);
        verify(utenteDAO).doRetrieveByAllPreferitiOfUtente("GFGHHH88U88V678G");
        verify(request).setAttribute("prodottiPref", numPref);
        assertEquals(2, numPref);
    }
}
