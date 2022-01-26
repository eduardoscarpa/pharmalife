package catalogo;

import application.catalogoService.ServletSchedaProdottoSearch;
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

public class ServletSchedaProdottoSearchTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletSchedaProdottoSearch servletSchedaProdottoSearch;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletSchedaProdottoSearch = new ServletSchedaProdottoSearch(prodottoDAO);
    }

    @Test // Completo
    public void ricercaSchedaProdottoTest() throws ServletException, IOException {
        when(request.getParameter("search")).thenReturn("TACHIPIRINA");
        Prodotto prodotto = new Prodotto();
        when(prodottoDAO.cercaProdottoByNome("TACHIPIRINA")).thenReturn(prodotto);

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/schedaProdotto.jsp")).thenReturn(requestDispatcher);

        servletSchedaProdottoSearch.doPost(request, response);
        verify(requestDispatcher).forward(request, response);
        String search=request.getParameter("search");
        assertEquals("TACHIPIRINA", search);
    }
}
