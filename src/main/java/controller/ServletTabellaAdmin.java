package controller;


import com.google.gson.Gson;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletTabellaAdmin", value = "/ServletTabellaAdmin")
public class ServletTabellaAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dati="";
        String lista=request.getParameter("lista");
        switch (lista){
            case "utenti" :
                UtenteDAO utenteDAO= new UtenteDAO();
                ArrayList<Utente> utenti=utenteDAO.doRetraiveByAllUtenti();
                Gson gson= new Gson();
                 dati=gson.toJson(utenti);
            break;
            case "prodotti":
                ProdottoDAO prodottoDAO= new ProdottoDAO();
                ArrayList<Prodotto> prodotti=prodottoDAO.doRetraiveByAllProdotti();
                Gson gson1= new Gson();
                dati=gson1.toJson(prodotti);
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");

        response.getWriter().write(dati);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
