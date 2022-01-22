package IntegrationTesting.admin;

import application.admin.ServletDeleteProdottoAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import storage.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServletDeleteProdottoAdminTest {

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
    }

    @Test
    public  void eliminaProdottoDalCatalogoTest(){

    }
}
