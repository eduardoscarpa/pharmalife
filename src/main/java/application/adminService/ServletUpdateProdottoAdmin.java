package application.adminService;

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

@WebServlet(name = "ServletUpdateProdottoAdmin", value = "/ServletUpdateProdottoAdmin")
public class ServletUpdateProdottoAdmin extends HttpServlet {
    private ProdottoDAOMethod prodottoDAO;
    public ServletUpdateProdottoAdmin() throws SQLException {
        prodottoDAO= new ProdottoDAO();
    }
    public ServletUpdateProdottoAdmin(ProdottoDAO prodottoDAO){
        this.prodottoDAO=prodottoDAO;
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idProdotto=Integer.parseInt(request.getParameter("id"));
        Prodotto prodotto= prodottoDAO.cercaProdotto(idProdotto);
        request.setAttribute("prodotto",prodotto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/admin/updateProdottoAdmin.jsp");
        dispatcher.forward(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
