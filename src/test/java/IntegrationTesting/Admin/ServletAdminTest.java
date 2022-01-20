package IntegrationTesting.Admin;

import controller.admin.ServletAdmin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class ServletAdminTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletAdmin servletAdmin;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        servletAdmin= new ServletAdmin();
    }


    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getParameter("value")).thenReturn("insertProdotto");
        String val=request.getParameter("value");
        assertEquals("insertProdotto", val);
    }



}
