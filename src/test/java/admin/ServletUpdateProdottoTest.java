package admin;

import controller.admin.ServletUpdateProdotto;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import static org.mockito.Mockito.*;
import  static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUpdateProdottoTest {

    @Mock
    private ProdottoDAO prodottoDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {

        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        when(request.getParameter("idProdotto")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("Oki");
        when(request.getParameter("prezzo")).thenReturn("12");
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
        ServletUpdateProdotto servletUpdateProdotto= new ServletUpdateProdotto(prodottoDAO);
        servletUpdateProdotto.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        int id=Integer.parseInt(request.getParameter("idProdotto"));

         //  verify(request).getParameter("idProdotto");
        // verify(request).getParameter("nome");
       // verify(request).getParameter("prezzo");

        assertEquals(2,id );
    }

    @Test
    public void aggiornaProdottoTest() throws ServletException, IOException {

        //ProdottoDAO prodottoDAO= Mockito.mock(ProdottoDAO.class);
        Prodotto prodotto= new Prodotto();
        prodotto.setCodiceProdotto(1);
        prodotto.setNome("Oki");
        prodotto.setPrezzo(12);

       /* ServletUpdateProdotto servletUpdateProdotto= new ServletUpdateProdotto(prodottoDAO);
        HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("idProdotto")).thenReturn("1");
        Mockito.when(request.getParameter("nome")).thenReturn("1");
        Mockito.when(request.getParameter("prezzo")).thenReturn("1");*/


      //  RequestDispatcher requestDispatcher=Mockito.mock(RequestDispatcher.class);
       // Mockito.when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
       // servletUpdateProdotto.doGet(request, response);
        //servletUpdateProdotto.aggiornaProdotto(1, "Oki", 12);

        prodottoDAO.updateProdotto(prodotto);
       // prodottoDAO.updateProdotto(prodotto);
        //Mockito.verify(prodottoDAO, Mockito.times(2)).updateProdotto(prodotto);
       // Mockito.verify(prodottoDAO).updateProdotto(prodotto);
        verify(prodottoDAO).updateProdotto(prodotto);
    }
}
