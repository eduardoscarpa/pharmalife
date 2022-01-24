package admin;

import application.admin.ServletDeleteProdottoAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServletDeleteProdottoAdminIntegrationTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    private ProdottoDAO prodottoDAO;
    private ServletDeleteProdottoAdmin servletDeleteProdottoAdmin;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        servletDeleteProdottoAdmin= new ServletDeleteProdottoAdmin();
    }

    @Test
    public  void  doGet() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/areaAmministratore.jsp")).thenReturn(dispatcher);
        servletDeleteProdottoAdmin.doGet(request, response);
        verify(request).getParameter("id");
        assertEquals("1", request.getParameter("id"));
    }

    @Test
    public  void eliminaProdottoDalCatalogoTest() throws ServletException, IOException, SQLException {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(2);
        assertNotEquals(null, prodotto);
        servletDeleteProdottoAdmin.eliminaProdottoDalCatalogo(2);
        ArrayList<Prodotto> prodotti=prodottoDAO.doRetraiveByAllProdotti();
        assertTrue(!prodotti.contains(prodotto));
    }
}
