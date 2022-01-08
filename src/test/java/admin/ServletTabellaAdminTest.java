/*package admin;

import com.google.gson.Gson;
import controller.admin.ServletTabellaAdmin;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
//import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
@PowerMockIgnore({"javax.net.ssl.*"})
@SuppressStaticInitializationFor({"Gson"})
public class ServletTabellaAdminTest {

    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;




    private ServletTabellaAdmin servletTabellaAdmin;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletTabellaAdmin= new ServletTabellaAdmin(utenteDAO, prodottoDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("lista")).thenReturn("utenti");
        String tableList=request.getParameter("lista");
        servletTabellaAdmin.doGet(request, response);
        verify(request, times(2)).getParameter("lista");
        verify(response).setContentType("application/json");
        verify(response).setContentType("text/plain;charset=UTF-8");
        assertEquals("utenti",tableList);
    }

    @Test

    public void visualizzaTabellProdottiTest(){

        ArrayList<Prodotto> prodotti= new ArrayList<>();
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
        when(prodottoDAO.doRetrieveByAllProdotti()).thenReturn(prodotti);
        Gson gson= PowerMockito.mock(Gson.class);
        when(gson.toJson(prodotti)).thenReturn("StringJson");
    }
}
*/