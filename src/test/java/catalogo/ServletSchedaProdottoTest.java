package catalogo;

import application.catalogo.ServletSchedaProdotto;
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

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServletSchedaProdottoTest {

    @Mock
    private ProdottoDAO prodottoDAO;

    private ServletSchedaProdotto servletSchedaProdotto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletSchedaProdotto = new ServletSchedaProdotto(prodottoDAO);
    }

    @Test // Completo
    public void doPostTest() throws ServletException, IOException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);

        when(request.getParameter("value")).thenReturn("5");

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/schedaProdotto.jsp")).thenReturn(requestDispatcher);

        servletSchedaProdotto.doGet(request, response);
        verify(requestDispatcher).forward(request, response);

        int codiceProdotto = Integer.parseInt(request.getParameter("value"));
        assertEquals(codiceProdotto, 5);
    }

    @Test // Completo
    public void visualizzaSchedaProdottoTest() throws ServletException, IOException {
        Prodotto prodotto = new Prodotto();
        when(prodottoDAO.cercaProdotto(1)).thenReturn(prodotto);

        HttpServletRequest request=mock(HttpServletRequest.class);
        servletSchedaProdotto.VisualizzaSchedaProdotto(1, request);
        verify(request).setAttribute("prodotto", prodotto);
    }
}
