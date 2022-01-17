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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletMostraPref", value = "/ServletMostraPref")
public class ServletMostraPref extends HttpServlet {

    private UtenteDAOMethod utenteDAO; //PRIMA ERA UtenteDAOMethod;
    private ArrayList<Prodotto> preferiti;

    public ServletMostraPref() throws SQLException {
        utenteDAO = new UtenteDAO();
        preferiti = new ArrayList<>();
    }

    public ServletMostraPref(UtenteDAO utenteDAO){
        this.utenteDAO = utenteDAO;
        this.preferiti = new ArrayList<>();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        visualizzaPreferiti(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pagine/preferiti.jsp");
        dispatcher.forward(request, response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void visualizzaPreferiti(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            if(utente != null) {
                this.preferiti = utenteDAO.doRetrieveByAllPreferitiOfUtente(utente.getCodiceFiscale());
                request.setAttribute("prodottiPref", preferiti);
            }
        }
    }
}
