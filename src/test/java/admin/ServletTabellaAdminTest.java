package admin;

import com.google.gson.Gson;
import controller.admin.ServletTabellaAdmin;
import model.prodotto.ProdottoDAO;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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

  /*  @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("lista")).thenReturn("utenti");
        String tableList=request.getParameter("lista");
        servletTabellaAdmin.doGet(request, response);
        verify(request).getParameter("utenti");
        assertEquals("utenti",tableList);
    }*/

    @Test
    @PrepareForTest(Gson.class)
    public void visualizzaTabellProdottiTest(){

    }
}
