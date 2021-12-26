package controller.utente;

import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
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
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "ServletInvioMessaggio", value = "/ServletInvioMessaggio")
public class ServletInvioMessaggio extends HttpServlet {
    private String avviso;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        invioMessaggio(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void invioMessaggio (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "WEB-INF/pagine/messaggioInviato.jsp";
        String nomeUtente=request.getParameter("firstname");
        String cognome=request.getParameter("lastname");
        String telefono=request.getParameter("telefono");
        String email=request.getParameter("email");
        String messaggio=request.getParameter("messaggio");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        Date data2 = new Date(System.currentTimeMillis());
        Time time1 = new Time(System.currentTimeMillis());

        MessaggioDAO service = new MessaggioDAO();
        Messaggio message = new Messaggio();
        Utente u=new Utente();
        u.setNome(nomeUtente);
        u.setCognome(cognome);
        u.setTelefono(telefono);
        u.setEmail(email);
        u.setCodiceFiscale(utente.getCodiceFiscale());
        message.setTesto(messaggio);
        message.setData(data2);
        message.setOra(time1);

        UtenteDAO utenteDAO= new UtenteDAO();
        Utente utente1 = (Utente) utenteDAO.cercaUtente(utente.getCodiceFiscale());
        System.out.println(utente.getCodiceFiscale());
        //Utente utente1=utenteDAO.cercaUtente(nomeUtente);
         avviso="";

        if (utente1 != null) {
            if (!utente1.getNome().equals(u.getNome())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il nome non coincide con quello dell'utente loggato";
            }
            if (!utente1.getCognome().equals(u.getCognome())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il cognome non coincide con quello dell'utente loggato";
            }
            if (!utente1.getTelefono().equals(u.getTelefono())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="Il numero di telefono non coincide con quello dell'utente loggato";
            }
            if (!utente1.getEmail().equals(u.getEmail())) {
                address = "WEB-INF/pagine/assistenza.jsp";
                avviso="L'email non coincide con quella dell'utente loggato";
            }
        }

        message.setUtente(u);
        service.insertMessaggio(message);

        request.setAttribute("assistenza",avviso);
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pagine/messaggioInviato.jsp");
        dispatcher.forward(request, response);
    }
}

