package catalogo;

import application.catalogoService.ServletListaProdotti;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletListaProdottiTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletListaProdotti servletListaProdotti;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletListaProdotti = new ServletListaProdotti(prodottoDAO);
    }

    @Test // Completo
    public void visualizzaListaProdottiHeaderOK() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("value")).thenReturn("3");
        when(request.getParameter("nomejsp")).thenReturn("header");

        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        when(prodottoDAO.cercaProdotti(3, 0,9)).thenReturn(prodotti);

        String opzione = "Categoria";
        String idCategoria = request.getParameter("value");
        String nomeJsp = request.getParameter("nomejsp");
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp")).thenReturn(dispatcher);
        servletListaProdotti.visualizzaListaProdotti(request, response);
        verify(dispatcher).forward(request, response);

        verify(request).setAttribute("prodotti", prodotti);

        assertEquals("3", idCategoria);
        assertEquals("header", nomeJsp);
    }

    @Test
    public void visualizzaListaProdottiNotHeader() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("value")).thenReturn("3");
        when(request.getParameter("nomejsp")).thenReturn("notheader");

        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        when(prodottoDAO.cercaProdotti(3, 9,18)).thenReturn(prodotti);

        String opzione = "Categoria";
        String idCategoria = request.getParameter("value");
        String nomeJsp = request.getParameter("nomejsp");
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp")).thenReturn(dispatcher);
        servletListaProdotti.visualizzaListaProdotti(request, response);
        verify(dispatcher).forward(request, response);

        verify(request).setAttribute("prodotti", prodotti);

        assertEquals("3", idCategoria);
        assertEquals("notheader", nomeJsp);
    }
}
