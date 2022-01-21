package application.admin;


import com.google.gson.Gson;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
import storage.prodotto.ProdottoDAOMethod;
import storage.utente.Utente;
import storage.utente.UtenteDAO;
import storage.utente.UtenteDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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

@WebServlet(name = "ServletTabellaAdmin", value = "/ServletTabellaAdmin")

@Generated
public class ServletTabellaAdmin extends HttpServlet {
    private UtenteDAOMethod utenteDAO;
    private ProdottoDAOMethod prodottoDAO;
    private String dati;

    public ServletTabellaAdmin(UtenteDAO utenteDAO, ProdottoDAO prodottoDAO) {
        this.utenteDAO = utenteDAO;
        this.prodottoDAO = prodottoDAO;
    }

    public ServletTabellaAdmin() throws SQLException {
        this.prodottoDAO= new ProdottoDAO();
        this.utenteDAO= new UtenteDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lista=request.getParameter("lista");
        switch (lista){
            case "utenti" :
               visualizzaTabellaUtenti(request,response);
            break;
            case "prodotti":
               visualizzaTabellaProdotti(request,response);
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(dati);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    /**
     * Questo metodo permette all'amministratore di visualizzare una tabella contenete
     * la lista di tutti gli utenti presenti iscritti alla piattaforma
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void visualizzaTabellaUtenti(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ArrayList<Utente> utenti=utenteDAO.doRetrieveByAllUtenti();
        Gson gson= new Gson();
        dati=gson.toJson(utenti);
    }

    /**
     * Questo metodo permette all'amministratore di visualizzare una tabella contenete la lista di tutti i prodotti presenti nel catalogo
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void visualizzaTabellaProdotti(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ArrayList<Prodotto> prodotti=prodottoDAO.doRetrieveByAllProdotti();
        Gson gson1= new Gson();
        dati=gson1.toJson(prodotti);
    }
}
