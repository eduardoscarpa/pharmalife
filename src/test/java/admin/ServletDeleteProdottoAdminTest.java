package admin;

import controller.admin.ServletDeleteProdottoAdmin;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import  static  org.mockito.Mockito.*;
import static  org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDeleteProdottoAdminTest {

    @Mock
    private ProdottoDAO prodottoDAO;

    private ServletDeleteProdottoAdmin servletDeleteProdottoAdmin;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletDeleteProdottoAdmin= new ServletDeleteProdottoAdmin(prodottoDAO);
    }

   @Test
    public void doGetTest() throws ServletException, IOException {
        HttpServletRequest request= mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/areaAmministratore.jsp")).thenReturn(requestDispatcher);
        servletDeleteProdottoAdmin.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        int id=Integer.parseInt(request.getParameter("id"));
        assertEquals(1, id);
    }


    @Test
    public void eliminaProdottoDalCatalogoTest() throws ServletException, IOException {
        HttpServletRequest request= mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");
        int id=Integer.parseInt(request.getParameter("id"));
        servletDeleteProdottoAdmin.eliminaProdottoDalCatalogo(id);
        verify(prodottoDAO).deleteProdotto(id);
    }
}
