package controller.utente;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletPreferiti", value = "/ServletPreferiti")
public class ServletPreferiti extends HttpServlet {
    private ProdottoDAOMethod prodottoDAO;
    private UtenteDAOMethod utenteDAO;

    public ServletPreferiti(){
        prodottoDAO= new ProdottoDAO();
        utenteDAO= new UtenteDAO();
    }
    public ServletPreferiti(ProdottoDAO prodottoDAO,UtenteDAO utenteDAO){
        this.prodottoDAO=prodottoDAO;
        this.utenteDAO=utenteDAO;
    }

    @Generated
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        inserisciProdottoAiPreferiti(request,response);
    }

    @Generated
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @pre //
     * @param request
     * @param response
     * @throws IOException
     * @post service.doRetrieveByAllPreferitiOfUtente.size=@pre.service.doRetrieveByAllPreferitiOfUtente.size+1
     */
    private void inserisciProdottoAiPreferiti(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
         prodottoDAO=new ProdottoDAO();
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        String risposta="";
        if(session != null) {
             utenteDAO = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");

            if (utente != null) {
                utenteDAO.insertPreferito(utente, prodotto);
                risposta="Prodotto aggiunto ai tuoi preferiti!";
            }

            response.getWriter().write(risposta);
        }
    }
}
