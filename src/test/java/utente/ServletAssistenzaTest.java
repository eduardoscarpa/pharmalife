package utente;

import application.utente.ServletAssistenza;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ServletAssistenzaTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletAssistenza servletAssistenza;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAssistenza= new ServletAssistenza();

    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/contatti.jsp")).thenReturn(requestDispatcher);
        servletAssistenza.doPost(request,response);
        verify(requestDispatcher).forward(request, response);
    }
}
