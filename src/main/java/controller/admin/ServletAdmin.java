package controller.admin;

import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.messaggio.MessaggioDAOMethod;
import model.ordine.OrdineDAO;
import model.ordine.OrdineDAOMethod;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

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
    private MessaggioDAOMethod messaggioDAO;
    private UtenteDAOMethod utenteDAO;
    private ProdottoDAOMethod prodottoDAO;
    private OrdineDAOMethod ordineDAO;

    public ServletAdmin(){
        messaggioDAO= new MessaggioDAO();
        utenteDAO= new UtenteDAO();
        prodottoDAO= new ProdottoDAO();
        ordineDAO= new OrdineDAO();
    }
    public ServletAdmin(MessaggioDAO messaggioDAO,UtenteDAO utenteDAO,ProdottoDAO prodottoDAO,OrdineDAO ordineDAO){
        this.messaggioDAO=messaggioDAO;
        this.utenteDAO=utenteDAO;
        this.prodottoDAO=prodottoDAO;
        this.ordineDAO=ordineDAO;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
    public void visualizzaMessaggi(HttpServletRequest request,HttpServletResponse response){
        messaggioDAO= new MessaggioDAO();
        ArrayList<Messaggio> messaggi= messaggioDAO.doRetrieveByAllMessaggi();
        request.setAttribute("messaggi",messaggi);
    }

    public void visualizzaStatistiche(HttpServletRequest request,HttpServletResponse response){
         messaggioDAO= new MessaggioDAO();
         prodottoDAO= new ProdottoDAO();
         utenteDAO= new UtenteDAO();
         ordineDAO= new OrdineDAO();
         request.setAttribute("messaggi",Integer.parseInt(String.valueOf(messaggioDAO.doRetrieveByAllMessaggi().size())));
         request.setAttribute("utenti",Integer.parseInt(String.valueOf(utenteDAO.doRetrieveByAllUtenti().size())));
         request.setAttribute("prodotti",Integer.parseInt(String.valueOf(prodottoDAO.doRetraiveByAllProdotti().size())));
         request.setAttribute("ordini",Integer.parseInt(String.valueOf(ordineDAO.doRetraiveByAllOrdini().size())));
    }
}
