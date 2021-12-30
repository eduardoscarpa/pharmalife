package controller.utente;

import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletUpdateIndirizzo", value = "/ServletUpdateIndirizzo")
public class ServletUpdateIndirizzo extends HttpServlet {

    private UtenteDAOMethod serviceUtente;

    public ServletUpdateIndirizzo(UtenteDAOMethod utenteDAOMethod){
        serviceUtente=utenteDAOMethod;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String via=request.getParameter("via");
        int numero=Integer.parseInt(request.getParameter("numero"));
        String cap=request.getParameter("cap");
        String codiceFiscale=request.getParameter("codiceFiscale");
        aggiornaIndirizzoUtente(via,numero,cap,codiceFiscale,request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * @pre //
     * @param via
     * @param numero
     * @param cap
     * @param codiceFiscale
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @post //
     */
    private  void aggiornaIndirizzoUtente(String via,int numero,String cap,String codiceFiscale,
                                          HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Utente utente= new Utente();
        utente.setVia(via);
        utente.setNumeroCivico(numero);
        utente.setCap(cap);
        utente.setCodiceFiscale(codiceFiscale);
         serviceUtente= new UtenteDAO();
        if(serviceUtente.updateIndirizzoUtente(utente)){
            request.setAttribute("updateAddress","Indirizzo aggiornato correttamente");
        }else {
            request.setAttribute("updateAddress","Errore durante l'aggiornamento");
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/updateIndirizzo.jsp");
        dispatcher.forward(request,response);
    }
}
