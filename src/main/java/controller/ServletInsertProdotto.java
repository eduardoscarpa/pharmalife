package controller;

import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.marchio.Marchio;
import model.marchio.MarchioDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletInsertProdotto", value = "/ServletInsertProdotto")
public class ServletInsertProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nomeProdotto=request.getParameter("nome");
        double prezzoProdotto=Double.parseDouble(request.getParameter("prezzo"));
        String marchioProdotto=request.getParameter("marchio");
        int quantita=Integer.parseInt(request.getParameter("quantita"));
        String categoria=request.getParameter("categoria");
        String descrizione=request.getParameter("descrizione");
        String pathImmagine=request.getParameter("pathImmagine");

        Prodotto prodotto =new Prodotto();
        prodotto.setNome(nomeProdotto);
        prodotto.setPrezzo(prezzoProdotto);
        MarchioDAO marchioDAO= new MarchioDAO();
        Marchio marchio= marchioDAO.cercaMarchio(marchioProdotto);
       // marchio.setNomeMarchio(marchioProdotto);
        prodotto.setMarchio(marchio);
        prodotto.setQuantita(quantita);
       // System.out.println(prodotto.getMarchio().getNomeMarchio());
        CategoriaDAO categoriaDAO= new CategoriaDAO();
        Categoria categoria1= categoriaDAO.cercaCategoria(categoria);

        prodotto.setCategoria(categoria1);
        prodotto.setDescrrizione(descrizione);
        prodotto.setPathImmagine(pathImmagine);
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        prodottoDAO.insertProdotto(prodotto);
        System.out.println("Insert Completato");

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
