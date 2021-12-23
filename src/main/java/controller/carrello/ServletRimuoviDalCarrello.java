package controller.carrello;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRimuoviDalCarrello", value = "/ServletRimuoviDalCarrello")
public class ServletRimuoviDalCarrello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rimozioneDalCarrello(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void rimozioneDalCarrello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        ProdottoDAO prodottoDAO= new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        HttpSession session= request.getSession();
        Utente utente=(Utente) session.getAttribute("utente");
        if(utente!=null){
            utente.getCarrello().sottraiTotale(prodotto.getPrezzo());
            utente.getCarrello().getProdotti().remove(prodotto);

        }else{
            Carrello carrello=(Carrello) session.getAttribute("carrello");
            if(carrello!=null){
                carrello.sottraiTotale(prodotto.getPrezzo());
                carrello.getProdotti().remove(prodotto);
            }
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp");
        dispatcher.forward(request,response);
    }
}
