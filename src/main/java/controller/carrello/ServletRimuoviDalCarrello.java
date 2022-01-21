package controller.carrello;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@WebServlet(name = "ServletRimuoviDalCarrello2", value = "/ServletRimuoviDalCarrello2")
public class ServletRimuoviDalCarrello extends HttpServlet {
    private ProdottoDAOMethod serviceProdotto;
    private Prodotto prodotto;
    private Utente utente;
    private Carrello carrello;
    private ArrayList<Prodotto> prodotti;

    public ServletRimuoviDalCarrello(ProdottoDAO serviceProdotto, Prodotto prodotto, Utente utente,Carrello carrello) {
        this.serviceProdotto = serviceProdotto;
        this.prodotto = prodotto;
        this.utente = utente;
        this.carrello=carrello;
    }

    public ServletRimuoviDalCarrello() throws SQLException {
        serviceProdotto=new ProdottoDAO();
        utente=new Utente();
        prodotto=new Prodotto();
        carrello= new Carrello();
        prodotti= new ArrayList<>();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
         prodotto=serviceProdotto.cercaProdotto(codiceProdotto);
         HttpSession session= request.getSession();
         utente=(Utente) session.getAttribute("utente");
        rimozioneDalCarrello(utente, prodotto, session);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp");
        dispatcher.forward(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Questo metodo ha la funzione di rimuovere un prodotto dal carrello
     * @param utente che vuole rimuovere il prodotto dal carrello
     * @param prodotto da rimuovere dal carrello dell'utente
     * @param session da cui prelevare il carrello
     * @throws ServletException
     * @throws IOException
     */
    public void rimozioneDalCarrello(Utente utente,Prodotto prodotto ,HttpSession session) throws ServletException, IOException {

        if(utente!=null){
            carrello= utente.getCarrello();
            carrello.sottraiTotale(prodotto.getPrezzo());
            prodotti= carrello.getProdotti();
            prodotti.remove(prodotto);
            carrello.setProdotti(prodotti);
            utente.setCarrello(carrello);

        }else{
            carrello=(Carrello) session.getAttribute("carrello");
            if(carrello!=null){
                carrello.sottraiTotale(prodotto.getPrezzo());
                prodotti= carrello.getProdotti();
                prodotti.remove(prodotto);
                carrello.setProdotti(prodotti);
            }
        }
    }

}
