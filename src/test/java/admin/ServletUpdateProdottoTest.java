package admin;

import application.admin.ServletUpdateProdotto;
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

public class ServletUpdateProdottoTest {

    @Mock
    private ProdottoDAO prodottoDAO;

    private ServletUpdateProdotto servletUpdateProdotto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletUpdateProdotto= new ServletUpdateProdotto(prodottoDAO);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        when(request.getParameter("idProdotto")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("Oki");
        when(request.getParameter("prezzo")).thenReturn("12.12");
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
        int id=Integer.parseInt(request.getParameter("idProdotto"));
        String nome=request.getParameter("nome");
        double prezzo=Double.parseDouble(request.getParameter("prezzo"));
        servletUpdateProdotto.doGet(request, response);
        verify(requestDispatcher).forward(request, response);

        verify(request,times(2)).getParameter("idProdotto");
        verify(request,times(2)).getParameter("nome");
        verify(request,times(2)).getParameter("prezzo");
        assertEquals(1,id );
        assertEquals("Oki", nome);
        assertEquals(12.12, prezzo, .12);
    }

    @Test
    public void aggiornaProdottoTest() throws ServletException, IOException {

        Prodotto prodotto= new Prodotto();
        prodotto.setCodiceProdotto(1);
        prodotto.setNome("Oki");
        prodotto.setPrezzo(12);
        prodottoDAO.updateProdotto(prodotto);
        verify(prodottoDAO).updateProdotto(prodotto);
    }
}
