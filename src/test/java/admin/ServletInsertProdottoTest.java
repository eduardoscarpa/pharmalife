package admin;

import controller.admin.ServletInsertProdotto;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.marchio.Marchio;
import model.marchio.MarchioDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import  static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.WeakHashMap;

public class ServletInsertProdottoTest {


    @Mock
    private MarchioDAO marchioDAO;
    @Mock
    private CategoriaDAO categoriaDAO;
    @Mock
    private ProdottoDAO prodottoDAO;

    private String nomeProdotto;
    private double prezzoProdotto;
    private String marchioProdotto;
    private int quantita;
    private String categoria;
    private String descrizione;
    private String pathImmagine;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    private ServletInsertProdotto servletInsertProdotto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletInsertProdotto= new ServletInsertProdotto(marchioDAO,categoriaDAO,prodottoDAO);
        when(request.getParameter("nomeProdotto")).thenReturn("Oki");
        when(request.getParameter("prezzo")).thenReturn("12.50");
        when(request.getParameter("marchio")).thenReturn("aveeno");
        when(request.getParameter("quantita")).thenReturn("10");
        when(request.getParameter("categoria")).thenReturn("farmaco da banco");
        when(request.getParameter("descrizione")).thenReturn("");
        when(request.getParameter("pathImmagine")).thenReturn("immagini/oki.jpg");
    }

    @Test
    public void doGetTest() throws ServletException, IOException {

         nomeProdotto=request.getParameter("nome");
         prezzoProdotto=Double.parseDouble(request.getParameter("prezzo"));
         marchioProdotto=request.getParameter("marchio");
         quantita=Integer.parseInt(request.getParameter("quantita"));
         categoria=request.getParameter("categoria");
         descrizione=request.getParameter("descrizione");
         pathImmagine=request.getParameter("pathImmagine");
        servletInsertProdotto.doGet(request, response);
        //verify(response).sendRedirect(response.encodeRedirectURL(request.getContextPath()));
        verify(request).getContextPath();
    }

    @Test
    public void aggiungiProdottoAlCatalogoTest(){
        Prodotto prodotto= new Prodotto();
        prodotto.setNome(nomeProdotto);
        prodotto.setPrezzo(prezzoProdotto);
        Marchio marchio= new Marchio();
        prodotto.setMarchio(marchio);
        prodotto.setQuantita(quantita);
        Categoria categoriaProdotto= new Categoria();
        prodotto.setCategoria(categoriaProdotto);
        prodotto.setDescrrizione(descrizione);
        prodotto.setPathImmagine(pathImmagine);
        when(marchioDAO.cercaMarchio("Aveeno")).thenReturn(marchio);
        when(categoriaDAO.cercaCategoria("Farmaco da anco")).thenReturn(categoriaProdotto);
        servletInsertProdotto.aggiungiProdottoAlCatalogo(nomeProdotto,prezzoProdotto,marchioProdotto,quantita,categoria,descrizione,
                pathImmagine);
      //  verify(prodottoDAO).updateProdotto(prodotto);
     verify(prodottoDAO).insertProdotto(prodotto);

    }
}
