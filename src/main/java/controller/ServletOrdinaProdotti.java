package controller;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "ServletOrdinaProdotti", value = "/ServletOrdinaProdotti")
public class ServletOrdinaProdotti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ordine=request.getParameter("ordine");
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        ArrayList<Prodotto> prodotti=prodottoDAO.doRetraiveByAllProdotti();
        String opzione="ordina";


        if(ordine.equalsIgnoreCase("crescente"))
            prodotti=prodottoDAO.OrdinaDallaAallaZ(prodotti);
        else if(ordine.equalsIgnoreCase("decrescente")){
            prodotti=prodottoDAO.OrdinaDallaAallaZ(prodotti);
            Collections.reverse(prodotti);
        }
        else if(ordine.equalsIgnoreCase("menoCaro")){
            prodotti=prodottoDAO.OrdinaDalMenoCaroAlPiuCaro(prodotti);

        }
        else {
            prodotti=prodottoDAO.OrdinaDalMenoCaroAlPiuCaro(prodotti);
            Collections.reverse(prodotti);
        }



        request.setAttribute("prodotti",prodotti);
        request.setAttribute("opzione",opzione);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
