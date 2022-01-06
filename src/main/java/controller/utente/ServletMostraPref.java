package controller.utente;

import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletMostraPref", value = "/ServletMostraPref")
public class ServletMostraPref extends HttpServlet {

    private UtenteDAO utenteDAO; //PRIMA ERA UtenteDAOMethod;
    private ArrayList<Prodotto> preferiti;

    public ServletMostraPref(){
        utenteDAO = new UtenteDAO();
    }

    public ServletMostraPref(UtenteDAO utenteDAO){
        this.utenteDAO = utenteDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        visualizzaPreferiti(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pagine/preferiti.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void visualizzaPreferiti(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            utenteDAO = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");
            if(utente != null) {
                ArrayList<Prodotto> prodottiPref = new ArrayList<>();
                prodottiPref = utenteDAO.doRetrieveByAllPreferitiOfUtente(utente.getCodiceFiscale());
                request.setAttribute("prodottiPref", prodottiPref);
            }
        }
    }

    public void setArrayPreferiti(ArrayList<Prodotto> preferiti){
        this.preferiti = preferiti;
    }
}
