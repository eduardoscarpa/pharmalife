package controller.catalogo;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletListaProdotti", value = "/ServletListaProdotti")
public class ServletListaProdotti extends HttpServlet {
    private   static int start=0;
    private static  final  int end=9;
    private ProdottoDAOMethod prodottoDAO;

    public ServletListaProdotti() throws SQLException {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletListaProdotti(ProdottoDAO prodottoDAO) {
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
        visualizzaListaProdotti(request, response);
    }

    /**
     * Questo metodo permette di visualizzare la lista di tutti i prodotti presenti nel catalogo
     * @pre
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @post
     */

    public void visualizzaListaProdotti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String opzione="Categoria";
        int idCategoria=Integer.parseInt(request.getParameter("value"));
        String nomejsp=request.getParameter("nomejsp");

        if(nomejsp.equals("header")){
            start=0;
        }else {
            start+=9;
        }

        ArrayList<Prodotto> prodotti= prodottoDAO.cercaProdottiRoot(idCategoria);

        HttpSession session=request.getSession();
        request.setAttribute("prodotti",prodotti);
        request.setAttribute("idCategoria",idCategoria);
        request.setAttribute("opzione",opzione);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp");
        dispatcher.forward(request,response);
    }
}
