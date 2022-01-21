package IntegrationTesting.Admin;

import controller.admin.ServletDeleteProdottoAdmin;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
