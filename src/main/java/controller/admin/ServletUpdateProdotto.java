package controller.admin;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateAdminDUE", value = "/ServletUpdateAdminDUE")
public class ServletUpdateProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //aggiornaProdotto(request,response);
        int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
        String nome=request.getParameter("nome");
        double prezzo=Double.parseDouble(request.getParameter("prezzo"));

        aggiornaProdotto(idProdotto,nome,prezzo);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     *
     * @param idProdotto identificativo del prodotto
     * @param nome  nome da aggiornare  del prodotto
     * @param prezzo  nuovo prezzo del prodotto
     */
    private void aggiornaProdotto(int idProdotto,String nome,double prezzo) throws  IOException {
        Prodotto prodotto = new Prodotto();
        prodotto.setCodiceProdotto(idProdotto);
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        prodottoDAO.updateProdotto(prodotto);

    }
}
