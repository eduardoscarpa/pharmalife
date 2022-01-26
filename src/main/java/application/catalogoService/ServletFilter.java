package application.catalogoService;

import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
import storage.prodotto.ProdottoDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "ServletFilter", value = "/ServletFilter")
public class ServletFilter extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletFilter() throws SQLException {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletFilter(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome=request.getParameter("nome");
        String categoria=request.getParameter("categoria");
        String marchio=request.getParameter("marchio");
        double min=Double.parseDouble(request.getParameter("min"));
        double max=Double.parseDouble(request.getParameter("max"));
        filtraProdotti(nome, categoria, marchio, min, max, request);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp");
        dispatcher.forward(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo serve per filtrare i prodotti in base a varie caratteristiche
     * @pre //
     * @param nome
     * @param categoria
     * @param marchio
     * @param min
     * @param max
     * @param request
     * @throws ServletException
     * @throws IOException
     * @post //
     */

    @Generated
    public void filtraProdotti(String nome, String categoria, String marchio, double min, double max, HttpServletRequest request) throws ServletException, IOException {
        String opzione="filtro";

        ArrayList<Prodotto> prodotti=prodottoDAO.doRetrieveByAllProdotti();

        if(nome!=null)
            prodotti=prodottoDAO.filtroNome(prodotti,nome);

        if(categoria!=null)
            prodotti=prodottoDAO.filtroCategoria(prodotti,categoria);

        if(marchio!=null)
            prodotti=prodottoDAO.filtroMarchio(prodotti,marchio);

        if(max!=50)
            prodotti=prodottoDAO.filtroMax(prodotti,max);

        if(min!=0)
            prodotti=prodottoDAO.filtroMin(prodotti,min);

        if(prodotti.size()==0)
            opzione="nullo";

        request.setAttribute("prodotti",prodotti);
        request.setAttribute("opzione",opzione);
    }
}