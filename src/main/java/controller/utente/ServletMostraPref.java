package controller.utente;

import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        visualizzaPreferiti(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void visualizzaPreferiti(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session != null) {
            UtenteDAO service = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");

            if(utente != null) {
                ArrayList<Prodotto> prodottiPref = new ArrayList<>();
                prodottiPref = service.preferiti(utente.getCodiceFiscale());
                request.setAttribute("prodottiPref", prodottiPref);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pagine/preferiti.jsp");
            dispatcher.forward(request, response);

        }
    }
}
