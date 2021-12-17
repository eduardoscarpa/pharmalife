package controller;

import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletUpdateIndirizzo", value = "/ServletUpdateIndirizzo")
public class ServletUpdateIndirizzo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String via=request.getParameter("via");
        int numero=Integer.parseInt(request.getParameter("numero"));
        String cap=request.getParameter("cap");
        String codiceFiscale=request.getParameter("codiceFiscale");
        Utente utente= new Utente();
        utente.setVia(via);
        utente.setNumeroCivico(numero);
        utente.setCap(cap);
        utente.setCodiceFiscale(codiceFiscale);
        UtenteDAO utenteDAO= new UtenteDAO();
        if(utenteDAO.updateIndirizzoUtente(utente)){
            request.setAttribute("updateAddress","Indirizzo aggiornato correttamente");
        }else {
            request.setAttribute("updateAddress","Errore durante l'aggiornamento");
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/updateIndirizzo.jsp");
        dispatcher.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
