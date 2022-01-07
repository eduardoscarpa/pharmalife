package admin;

import controller.admin.ServletInsertProdotto;
import model.categoria.CategoriaDAO;
import model.marchio.MarchioDAO;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import  static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletInsertProdottoTest {


    @Mock
    private MarchioDAO marchioDAO;
    @Mock
    private CategoriaDAO categoriaDAO;
    @Mock
    private ProdottoDAO prodottoDAO;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    private ServletInsertProdotto servletInsertProdotto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletInsertProdotto= new ServletInsertProdotto(marchioDAO,categoriaDAO,prodottoDAO);
    }

    @Test
    public void doGetTest(){
        when(request.getParameter("nomeProdotto")).thenReturn("Oki");
        when(request.getParameter("prezzo")).thenReturn("12.50");
        when(request.getParameter("marchio")).thenReturn("aveeno");
        when(request.getParameter("quantita")).thenReturn("10");
        when(request.getParameter("categoria")).thenReturn("farmaco da banco");
        when(request.getParameter("descrizione")).thenReturn("");
        when(request.getParameter("pathImmagine")).thenReturn("immagini/oki.jpg");

    }

    @Test
    public void aggiungiProdottoAlCatalogoTest(){

    }
}
