package controller.catalogo;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletSchedaProdotto", value = "/ServletSchedaProdotto")
public class ServletSchedaProdotto extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletSchedaProdotto() {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletSchedaProdotto(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        VisualizzaSchedaProdotto(codiceProdotto, request);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/schedaProdotto.jsp");
        dispatcher.forward(request,response);
    }

    /**
     * Questo metodo permette di visualizzare la scheda di un prodotto con descrizione e prezzo
     * @pre //
     * @param codiceProdotto
     * @param request
     * @throws ServletException
     * @throws IOException
     * @post //
     */

    public void VisualizzaSchedaProdotto(int codiceProdotto, HttpServletRequest request) throws ServletException, IOException {

        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        request.setAttribute("prodotto",prodotto);
    }
}
