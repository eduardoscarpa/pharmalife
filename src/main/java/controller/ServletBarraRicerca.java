package controller;

import com.google.gson.Gson;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletBarraRicerca", value = "/ServletBarraRicerca")
public class ServletBarraRicerca extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valore=request.getParameter("value");

        ProdottoDAO prodottoDAO= new ProdottoDAO();
        ArrayList<Prodotto> prodotti=prodottoDAO.prodotttoSearch(valore);
        Gson gson= new Gson();
        String prodottiJson=gson.toJson(prodotti);


        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");

        response.getWriter().write(prodottiJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
