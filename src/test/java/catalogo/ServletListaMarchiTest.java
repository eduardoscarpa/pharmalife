package catalogo;

import controller.catalogo.ServletListaMarchi;
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

public class ServletListaMarchiTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletListaMarchi servletListaMarchi;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletListaMarchi = new ServletListaMarchi(prodottoDAO);
    }

    /*
    @Test // Da finire
    public void doPostTest() throws ServletException, IOException {

    }
    */

    @Test // Completo
    public void visualizzaListaMarchi() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("value")).thenReturn("AVEENO");
        when(request.getParameter("nomejsp")).thenReturn("header");

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        when(prodottoDAO.cercaProdottiMarchio("AVEENO", 0,9)).thenReturn(prodotti);

        String opzione = "Marchio";
        String nomeMarchio = request.getParameter("value");
        String nomeJsp = request.getParameter("nomejsp");
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp")).thenReturn(dispatcher);
        servletListaMarchi.visualizzaListaMarchi(request, response);
        verify(dispatcher).forward(request, response);

        assertEquals("AVEENO", nomeMarchio);
        assertEquals("header", nomeJsp);

        /*
        verify(request, times(2)).setAttribute("prodotti", prodotti);
        verify(request, times(2)).setAttribute("opzione", opzione);
        verify(request, times(2)).setAttribute("nomeMarchio", nomeMarchio);
         */

    }
}