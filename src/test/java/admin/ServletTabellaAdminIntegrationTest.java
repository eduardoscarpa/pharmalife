package admin;

import application.admin.ServletTabellaAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
    }

    @Test
    public void visualizzaTabellaUtenti() throws IOException {
        servletTabellaAdmin.visualizzaTabellaUtenti(request, response);
    }
}
