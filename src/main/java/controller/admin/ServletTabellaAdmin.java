package controller.admin;


import com.google.gson.Gson;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
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

    public ServletTabellaAdmin() {
        this.prodottoDAO= new ProdottoDAO();
        this.utenteDAO= new UtenteDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // String dati="";
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
       // String dati="";
       // utenteDAO= new UtenteDAO();
        ArrayList<Utente> utenti=utenteDAO.doRetrieveByAllUtenti();
        Gson gson= new Gson();
        dati=gson.toJson(utenti);
       /* response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(dati);*/
    }

    /**
     * Questo metodo permette all'amministratore di visualizzare una tabella contenete la lista di tutti i prodotti presenti nel catalogo
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void visualizzaTabellaProdotti(HttpServletRequest request,HttpServletResponse response) throws IOException {
       // String dati="";

        //prodottoDAO= new ProdottoDAO();
        ArrayList<Prodotto> prodotti=prodottoDAO.doRetrieveByAllProdotti();
        Gson gson1= new Gson();
        dati=gson1.toJson(prodotti);
       /* response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(dati);*/
    }
}
