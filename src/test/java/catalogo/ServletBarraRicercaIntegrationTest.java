/*package catalogo;

import application.catalogo.ServletBarraRicerca;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ServletBarraRicercaIntegrationTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletBarraRicerca servletBarraRicerca;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        servletBarraRicerca= new ServletBarraRicerca();
    }

    @Test
    public void doGetTest() throws IOException, ServletException {
        when(request.getParameter("value")).thenReturn("OLIO");
        PrintWriter printWriter=mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servletBarraRicerca.doGet(request, response);
        verify(response).getWriter();
        verify(response).setContentType("application/json");
        verify(response).setContentType("text/plain;charset=UTF-8");
        assertNotEquals(null, response.getWriter());

    }

}
*/