/*package IntegrationTesting.admin;

import application.admin.ServletDeleteProdottoAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import storage.prodotto.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ServletDeleteProdottoAdminTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private ProdottoDAO prodottoDAO;
    private ServletDeleteProdottoAdmin servletDeleteProdottoAdmin;

    @Before
    public void setUp() throws SQLException {
        servletDeleteProdottoAdmin= new ServletDeleteProdottoAdmin();
    }

    @Test
    public  void  doGet() throws ServletException, IOException {

        when(request.getParameter("id")).thenReturn("1");
        servletDeleteProdottoAdmin.doGet(request, response);
        int id=Integer.parseInt(request.getParameter("id"));
        assertEquals(1, id);
    }

    @Test
    public  void eliminaProdottoDalCatalogoTest(){

    }
}
*/