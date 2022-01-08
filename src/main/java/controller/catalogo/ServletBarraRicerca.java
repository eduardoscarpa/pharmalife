package controller.catalogo;

import com.google.gson.Gson;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletBarraRicerca", value = "/ServletBarraRicerca")
public class ServletBarraRicerca extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletBarraRicerca() {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletBarraRicerca(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valore=request.getParameter("value");

        ArrayList<Prodotto> prodotti=prodottoDAO.prodottoSearch(valore);
        Gson gson= new Gson();
        String prodottiJson=gson.toJson(prodotti);
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(prodottiJson);
        ricercaProdotto(valore);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Questo meteodo serve per ricercare un prodotto nel catalogo mediante barra di ricerca
     * @pre //
     * @param valore
     * @throws IOException
     * @post //
     */

    public void ricercaProdotto(String valore) throws IOException {

            ArrayList<Prodotto> prodotti = prodottoDAO.prodottoSearch(valore);
            Gson gson = new Gson();
            String prodottiJson = gson.toJson(prodotti);
    }
}
