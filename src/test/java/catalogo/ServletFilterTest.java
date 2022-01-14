package catalogo;

import controller.catalogo.ServletFilter;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletFilterTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletFilter servletFilter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletFilter = new ServletFilter(prodottoDAO);
    }

    @Test // Completo
    public void doGetTest() throws ServletException, IOException {
        when(request.getParameter("nome")).thenReturn("TACHIPIRINA");
        when(request.getParameter("categoria")).thenReturn("INTEGRATORI");
        when(request.getParameter("marchio")).thenReturn("AVEENO");
        when(request.getParameter("min")).thenReturn("5");
        when(request.getParameter("max")).thenReturn("25");
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp")).thenReturn(requestDispatcher);
        servletFilter.doGet(request, response);
        verify(requestDispatcher).forward(request, response);

        String nome = request.getParameter("nome");
        assertEquals(nome, "TACHIPIRINA");
    }

    @Test // Da finire
    public void filtraProdottiTest(){
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
        when(prodottoDAO.doRetrieveByAllProdotti()).thenReturn(prodotti);

    }
}
