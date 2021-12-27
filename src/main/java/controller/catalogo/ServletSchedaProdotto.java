package controller.catalogo;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletSchedaProdotto", value = "/ServletSchedaProdotto")
public class ServletSchedaProdotto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        VisualizzaSchedaProdotto(codiceProdotto, request);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/schedaProdotto.jsp");
        dispatcher.forward(request,response);
    }

    private void VisualizzaSchedaProdotto(int codiceProdotto, HttpServletRequest request) throws ServletException, IOException {
        ProdottoDAO prodottoDAO= new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        request.setAttribute("prodotto",prodotto);
    }
}
