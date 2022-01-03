package controller.utente;

import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.messaggio.MessaggioDAOMethod;
import model.utente.Utente;
import model.utente.UtenteDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "ServletInvioMessaggio", value = "/ServletInvioMessaggio")
public class ServletInvioMessaggio extends HttpServlet {
    private String avviso;
    private MessaggioDAOMethod serviceMessaggio;
    private UtenteDAOMethod serviceUtente;
    private String address;


    public ServletInvioMessaggio(MessaggioDAOMethod messaggioDAOMethod,UtenteDAOMethod utenteDAOMethod){
        serviceMessaggio =messaggioDAOMethod;
        serviceUtente =utenteDAOMethod;
    }
    public ServletInvioMessaggio(){
        serviceMessaggio =new MessaggioDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // invioMessaggio(request, response);
        address = "WEB-INF/pagine/messaggioInviato.jsp";
        String nomeUtente=request.getParameter("firstname");
        String cognome=request.getParameter("lastname");
        String telefono=request.getParameter("telefono");
        String email=request.getParameter("email");
        String messaggio=request.getParameter("messaggio");
        HttpSession session = request.getSession();
        Utente utenteLoggato = (Utente) session.getAttribute("utente");

        Date data2 = new Date(System.currentTimeMillis());
        Time time1 = new Time(System.currentTimeMillis());

        Messaggio message = new Messaggio();
        Utente utente=new Utente();
        utente.setNome(nomeUtente);
        utente.setCognome(cognome);
        utente.setTelefono(telefono);
        utente.setEmail(email);
        utente.setCodiceFiscale(utenteLoggato.getCodiceFiscale());
        message.setTesto(messaggio);
        message.setData(data2);
        message.setOra(time1);
        if (checkUtente(utenteLoggato,utente)){
            invioMessaggio(utenteLoggato,message);
        }
        request.setAttribute("assistenza",avviso);
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     *
     * @pre not checkUtente(utenteLoggato,utente)
     * @param utenteLoggato
     * @param message
     * @throws ServletException
     * @throws IOException
     * @post serviceMessaggio.doRetrieveByAllMessaggi.size = @pre.serviceMessaggio.size.doRetrieveByAllMessaggi()+1.size
     */
    public void invioMessaggio (Utente utenteLoggato, Messaggio message) throws ServletException, IOException {
        message.setUtente(utenteLoggato);
        serviceMessaggio.insertMessaggio(message);
    }

    /**
     *
     * @param utenteLoggato che si trova in sessione
     * @param utente
     * @return
     */
    public boolean checkUtente(Utente utenteLoggato,Utente utente){
        if (utenteLoggato != null) {
            if (!utenteLoggato.getNome().equals(utente.getNome())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il nome non coincide con quello dell'utente loggato.";
                return  false;
            }
            if (!utenteLoggato.getCognome().equals(utente.getCognome())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il cognome non coincide con quello dell'utente loggato.";
                return  false;
            }
            if (!utenteLoggato.getTelefono().equals(utente.getTelefono())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il numero di telefono non coincide con quello dell'utente loggato.";
                return  false;
            }
            if (!utenteLoggato.getEmail().equals(utente.getEmail())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="L'e-mail non coincide con quella dell'utente loggato.";
                return  false;
            }
        }
        return  true;
    }
}

