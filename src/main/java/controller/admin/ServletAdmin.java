package controller.admin;

import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletAdmin", value = "/ServletAdmin")
public class ServletAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String valore=request.getParameter("value");
            String pagina="";
            switch (valore){
                case "insertProdotto" :
                    pagina="WEB-INF/pagine/admin/insertProdotto.jsp";
                break;
                case "messaggi":
                    visualizzaMessaggi(request,response);
                    pagina="WEB-INF/pagine/admin/assistenzaUtenti.jsp";
                break;
                case "statistiche":
                    visualizzaStatistiche(request,response);
                    pagina="WEB-INF/pagine/admin/statistiche.jsp";
                    break;
            }
            RequestDispatcher dispatcher=request.getRequestDispatcher(pagina);
            dispatcher.forward(request,response);
    }
    private  void visualizzaMessaggi(HttpServletRequest request,HttpServletResponse response){
        MessaggioDAO messaggioDAO= new MessaggioDAO();
        ArrayList<Messaggio> messaggi= messaggioDAO.doRetraiveByAllMessaggi();
        request.setAttribute("messaggi",messaggi);
    }

    private void visualizzaStatistiche(HttpServletRequest request,HttpServletResponse response){
        MessaggioDAO messaggioDAO1= new MessaggioDAO();
        ProdottoDAO prodottoDAO1= new ProdottoDAO();
        UtenteDAO utenteDAO= new UtenteDAO();
        OrdineDAO ordineDAO= new OrdineDAO();
        request.setAttribute("messaggi",Integer.parseInt(String.valueOf(messaggioDAO1.doRetraiveByAllMessaggi().size())));
        request.setAttribute("utenti",Integer.parseInt(String.valueOf(utenteDAO.doRetraiveByAllUtenti().size())));
        request.setAttribute("prodotti",Integer.parseInt(String.valueOf(prodottoDAO1.doRetraiveByAllProdotti().size())));
        request.setAttribute("ordini",Integer.parseInt(String.valueOf(ordineDAO.doRetraiveByAllOrdini().size())));
    }
}
