package test;

import controller.admin.ServletUpdateProdotto;
import controller.utente.ServletIscrizione;
import model.prodotto.ProdottoDAO;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class EsercitazioneTest {


    @Test
    public void provaTest() throws SQLException {
        ServletIscrizione servletIscrizione= Mockito.mock(ServletIscrizione.class);
        Mockito.when(servletIscrizione.isNotPresentCf("BBRRTUAN")).thenReturn(true);
        boolean val=servletIscrizione.isNotPresentCf("BBRRTUAN");
        Mockito.verify(servletIscrizione, Mockito.times(1)).isNotPresentCf("BBRRTUAN");

        assertEquals("Ok", true,val);
    }

    @Test
    public  void dispatcherTest() throws ServletException, IOException {
        ProdottoDAO prodottoDAO=Mockito.mock(ProdottoDAO.class);
        HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("idProdotto")).thenReturn("1");
        Mockito.when(request.getParameter("nome")).thenReturn("sss");
        Mockito.when(request.getParameter("prezzo")).thenReturn("11");
        RequestDispatcher requestDispatcher=Mockito.mock(RequestDispatcher.class);
        Mockito.when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
        ServletUpdateProdotto servletUpdateProdotto= new ServletUpdateProdotto(prodottoDAO);
       // servletUpdateProdotto.doGet(request, response);
        servletUpdateProdotto.doGet(request, response);
        //Mockito.verify(requestDispatcher, Mockito.times(2)).forward(request,response);
        Mockito.verify(requestDispatcher).forward(request, response);

        //servletUpdateProdotto.service(request, response);
        //assertEquals("Ok", "", );
        //servletUpdateProdotto.
    }

    /*public void aggiornaProdottoTest() throws IOException {
        ProdottoDAO prodottoDAO=Mockito.mock(ProdottoDAO.class);
        ServletUpdateProdotto servletUpdateProdotto= new ServletUpdateProdotto(prodottoDAO);
        servletUpdateProdotto.aggiornaProdotto(1, "Oki",12.40 );
    }*/

}
