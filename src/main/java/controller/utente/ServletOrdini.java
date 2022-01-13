package controller.utente;

import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.ordine.OrdineDAOMethod;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "ServletOrdini", value = "/ServletOrdini")
public class ServletOrdini extends HttpServlet {
    private OrdineDAOMethod ordineDAO;

    public ServletOrdini(){
        ordineDAO= new OrdineDAO();
    }
    public ServletOrdini(OrdineDAO ordineDAO){
        this.ordineDAO=ordineDAO;
    }

    @Generated
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Utente utente = null;
        if(session != null)
            utente=(Utente) session.getAttribute("utente");
        if(utente!=null){
            if(utente.getCarrello()!=null){
                utente.getCarrello().prodottiToString();
                Ordine ordine= new Ordine();
                ordine.setCarrello(utente.getCarrello());
                Date date= new Date(System.currentTimeMillis());
                Time time= new Time(System.currentTimeMillis());
                ordine.setOra(time);
                ordine.setDataOrdine(date);
                ordine.setUtente(utente);
                //ordineDAO= new OrdineDAO();
                ordineDAO.insertCarrello(ordine);
                utente.setCarrello(null);
            }
            RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/logic.carrello.jsp");
            dispatcher.forward(request,response);
        }
    }
}
