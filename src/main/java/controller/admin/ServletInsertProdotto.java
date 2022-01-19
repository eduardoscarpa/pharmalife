package controller.admin;

import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.categoria.CategoriaDAOMethod;
import model.marchio.Marchio;
import model.marchio.MarchioDAO;
import model.marchio.MarchioDAOMethod;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletInsertProdotto", value = "/ServletInsertProdotto")
public class ServletInsertProdotto extends HttpServlet {
    private MarchioDAOMethod marchioDAO;
    private CategoriaDAOMethod categoriaDAO;
    private ProdottoDAOMethod prodottoDAO;

    public ServletInsertProdotto(MarchioDAO marchioDAO, CategoriaDAO categoriaDAO, ProdottoDAO prodottoDAO) {
        this.marchioDAO = marchioDAO;
        this.categoriaDAO = categoriaDAO;
        this.prodottoDAO = prodottoDAO;
    }

    public ServletInsertProdotto() throws SQLException {
        marchioDAO= new MarchioDAO();
        categoriaDAO= new CategoriaDAO();
        prodottoDAO= new ProdottoDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nomeProdotto=request.getParameter("nome");
        double prezzoProdotto=Double.parseDouble(request.getParameter("prezzo"));
        String marchioProdotto=request.getParameter("marchio");
        int quantita=Integer.parseInt(request.getParameter("quantita"));
        String categoria=request.getParameter("categoria");
        String descrizione=request.getParameter("descrizione");
        String pathImmagine=request.getParameter("pathImmagine");
        aggiungiProdottoAlCatalogo(nomeProdotto,prezzoProdotto,marchioProdotto,quantita,categoria,descrizione,pathImmagine);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    /**
     * Questo metodo permette ad un Amministratore di aggiungere un prodotto nel catalogo
     * @param nomeProdotto indica il nome del prodotto
     * @param prezzoProdotto indica il prezzo del prodotto
     * @param marchioProdotto indica il marchio del prodotto
     * @param quantita indica la quantità disponibile per quel prodotto
     * @param categoria indica la categoria del prodotto
     * @param descrizione fornisce ulteriori info aggiuntive sul prodotto
     * @param pathImmagine indica la directory all'interno del progetto dove è posizionata l'immagine del prodotto
     * @post prodottoDAO.doRetrieveByAllProdotti.size=@pre prodottoDAO.doRetrieveByAllProdotti.size+1
     * */
    public void aggiungiProdottoAlCatalogo(String nomeProdotto,double prezzoProdotto,String marchioProdotto,int quantita,
                                           String categoria, String descrizione, String pathImmagine){
        Prodotto prodotto =new Prodotto();
        prodotto.setNome(nomeProdotto);
        prodotto.setPrezzo(prezzoProdotto);
        Marchio marchio= marchioDAO.cercaMarchio(marchioProdotto);
        prodotto.setMarchio(marchio);
        prodotto.setQuantita(quantita);
        Categoria categoria1= categoriaDAO.cercaCategoria(categoria);
        prodotto.setCategoria(categoria1);
        prodotto.setDescrizione(descrizione);
        prodotto.setPathImmagine(pathImmagine);
        prodottoDAO.insertProdotto(prodotto);
    }
}
