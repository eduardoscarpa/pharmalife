package utente;

import controller.admin.ServletAdmin;
import controller.utente.ServletPreferiti;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
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
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletPreferitiTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private ProdottoDAO prodottoDAO;

    private ServletPreferiti servletPreferiti;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servletPreferiti = new ServletPreferiti(prodottoDAO, utenteDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        /*servletPreferiti.doGet(request, response);
        assertEquals();
        Prodotto prodotto = new Prodotto();
        verify(prodottoDAO).insertProdotto(prodotto);

         */
    }
}
