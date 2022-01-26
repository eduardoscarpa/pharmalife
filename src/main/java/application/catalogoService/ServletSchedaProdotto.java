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

@WebServlet(name = "ServletSchedaProdotto", value = "/ServletSchedaProdotto")
public class ServletSchedaProdotto extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletSchedaProdotto() throws SQLException {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletSchedaProdotto(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Generated
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    /**
     *
     * @param request oggetto della Servlet contentente l'identificativo del prodotto da visualizzare
     * @param response oggetto della Servlet utile ad effettuare il forward
     * @throws ServletException
     * @throws IOException
     */
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
