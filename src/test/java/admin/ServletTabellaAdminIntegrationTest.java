package admin;

import application.adminService.ServletTabellaAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import storage.prodotto.Prodotto;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServletTabellaAdminIntegrationTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletTabellaAdmin servletTabellaAdmin;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        servletTabellaAdmin= new ServletTabellaAdmin();
    }
    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("lista")).thenReturn("utenti");
        PrintWriter printWriter= mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servletTabellaAdmin.doGet(request, response);
        verify(request).getParameter("lista");
        assertEquals("utenti", request.getParameter("lista"));
    }

    @Test
    public void visualizzaTabellaUtenti() throws IOException {
        //servletTabellaAdmin.visualizzaTabellaUtenti(request, response);
        ArrayList<Prodotto> prodotti= new ArrayList<>();
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
    }
}
