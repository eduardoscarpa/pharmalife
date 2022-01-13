package controller.catalogo;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletSchedaProdottoSearch", value = "/ServletSchedaProdottoSearch")
public class ServletSchedaProdottoSearch extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletSchedaProdottoSearch() {
        prodottoDAO =  new ProdottoDAO();
    }

    public ServletSchedaProdottoSearch(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    @Generated
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ricercaSchedaProdotto(request, response);
    }

    /**
     * Questo metodo consente di ricercare la scheda di un prodotto nel catalogo.
     * @pre //
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @post
     */

    public void ricercaSchedaProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nomeProdotto=request.getParameter("search");
        Prodotto prodotto= prodottoDAO.cercaProdottoByNome(nomeProdotto);
        request.setAttribute("prodotto",prodotto);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/schedaProdotto.jsp");
        dispatcher.forward(request,response);
    }
}
