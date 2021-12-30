package controller.carrello;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRimuoviDalCarrello", value = "/ServletRimuoviDalCarrello")
public class ServletRimuoviDalCarrello extends HttpServlet {
    private ProdottoDAOMethod serviceProdotto;
    private Prodotto prodotto;
    private Utente utente;

    public ServletRimuoviDalCarrello(ProdottoDAOMethod prodottoDAOMethod,Prodotto prodotto,Utente utente){
        serviceProdotto=prodottoDAOMethod;
        this.prodotto=prodotto;
        this.utente=utente;
    }
    public ServletRimuoviDalCarrello(){
        serviceProdotto=new ProdottoDAO();
        utente=new Utente();
        prodotto=new Prodotto();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        rimozioneDalCarrello(codiceProdotto, request);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     *
     * @param codiceProdotto del prodotto da eliminare dal carrello
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    private void rimozioneDalCarrello(int codiceProdotto, HttpServletRequest request) throws ServletException, IOException {
        serviceProdotto= new ProdottoDAO();
        prodotto=serviceProdotto.cercaProdotto(codiceProdotto);
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
    }
}
