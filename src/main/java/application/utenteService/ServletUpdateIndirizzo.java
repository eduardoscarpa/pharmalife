package application.utenteService;

import storage.utente.Utente;
import storage.utente.UtenteDAO;
import storage.utente.UtenteDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletUpdateIndirizzo", value = "/ServletUpdateIndirizzo")
public class ServletUpdateIndirizzo extends HttpServlet {

    private UtenteDAOMethod utenteDAO;
    private Utente utente;

    public ServletUpdateIndirizzo(UtenteDAO utenteDAO, Utente utente){
        this.utenteDAO=utenteDAO;
        this.utente = utente;
    }
    public ServletUpdateIndirizzo() throws SQLException {
        utenteDAO=new UtenteDAO();
        utente = new Utente();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String via=request.getParameter("via");
        int numero=Integer.parseInt(request.getParameter("numero"));
        String cap=request.getParameter("cap");
        String codiceFiscale=request.getParameter("codiceFiscale");
        aggiornaIndirizzoUtente(via,numero,cap,codiceFiscale,request,response);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/updateIndirizzo.jsp");
        dispatcher.forward(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo serve per modificare l'indirizzo dell'utente.
     * @param via
     * @param numero
     * @param cap
     * @param codiceFiscale
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void aggiornaIndirizzoUtente(String via,int numero,String cap,String codiceFiscale,
                                          HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        utente.setVia(via);
        utente.setNumeroCivico(numero);
        utente.setCap(cap);
        utente.setCodiceFiscale(codiceFiscale);
        if(utenteDAO.updateIndirizzoUtente(utente)){
            request.setAttribute("updateAddress","Il nuovo indirizzo è stato aggiornato correttamente.");
        }else {
            request.setAttribute("updateAddress","Errore durante l'aggiornamento dell'indirizzo.");
        }
    }
}
