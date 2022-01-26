package application.adminService;

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

@WebServlet(name = "ServletDeleteProdottoAdmin", value = "/ServletDeleteProdottoAdmin")
public class ServletDeleteProdottoAdmin extends HttpServlet {
    private ProdottoDAOMethod prodottoDAO;

    public ServletDeleteProdottoAdmin() throws SQLException {
        prodottoDAO=new ProdottoDAO();
    }
    public ServletDeleteProdottoAdmin(ProdottoDAO prodottoDAO){
        this.prodottoDAO=prodottoDAO;
    }

    /**
     *
     * @param request oggetto della Servlet contentente il valore dell'ide del prodotto da eliminare
     * @param response  oggetto della Servlet utile per eseguire il forward
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto=Integer.parseInt(request.getParameter("id"));
        eliminaProdottoDalCatalogo(idProdotto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/admin/areaAmministratore.jsp");
        dispatcher.forward(request,response);

    }

    /**
     *richiama il doGet
     */
    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo permette ad un Amministratore di eliminare un prodotto dal catalogo
     * @param idProdotto del prodotto da eliminare
     * @throws ServletException
     * @throws IOException
     * <p><b>post</b></p>  prodottoDAO.doRetrieveByAllProdotti.size=@pre prodottoDAO.doRetrieveByAllProdotti.size-1
     */
    public void eliminaProdottoDalCatalogo(int idProdotto) throws ServletException, IOException {
        prodottoDAO.deleteProdotto(idProdotto);
    }
}
