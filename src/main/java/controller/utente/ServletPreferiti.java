package controller.utente;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletPreferiti", value = "/ServletPreferiti")
public class ServletPreferiti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* HttpSession session = request.getSession();
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        String risposta="";
        if(session != null) {
            UtenteDAO service = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");

            if (utente != null) {
                service.insertPreferito(utente, prodotto);
                risposta="Prodotto Aggiunto ai Preferiti";
            }

            response.getWriter().write(risposta);
        }*/
        inserisciProdottoAiPreferiti(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void inserisciProdottoAiPreferiti(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        String risposta="";
        if(session != null) {
            UtenteDAO service = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");

            if (utente != null) {
                service.insertPreferito(utente, prodotto);
                risposta="Prodotto Aggiunto ai Preferiti";
            }

            response.getWriter().write(risposta);
        }
    }
}
