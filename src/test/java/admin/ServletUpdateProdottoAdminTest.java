package admin;

import application.admin.ServletUpdateProdottoAdmin;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
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

public class ServletUpdateProdottoAdminTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletUpdateProdottoAdmin servletUpdateProdottoAdmin;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletUpdateProdottoAdmin= new ServletUpdateProdottoAdmin(prodottoDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("01");
        int id=Integer.parseInt(request.getParameter("id"));
        Prodotto prodotto= new Prodotto();
        when(prodottoDAO.cercaProdotto(id)).thenReturn(prodotto);
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/admin/updateProdottoAdmin.jsp")).thenReturn(dispatcher);
        servletUpdateProdottoAdmin.doGet(request, response);
        verify(request).setAttribute("prodotto", prodotto);
        verify(dispatcher).forward(request, response);
        assertEquals(01, id);
    }

}
