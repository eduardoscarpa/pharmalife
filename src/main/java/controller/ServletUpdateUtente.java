package controller;

import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ServletUpdateUtente", value = "/ServletUpdateUtente")
public class ServletUpdateUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codiceFiscale=request.getParameter("codiceFiscale");
        String nomeUtente=request.getParameter("nome");
        System.out.println("Nome utente  "+ nomeUtente);
        String cognomeUtente=request.getParameter("cognome");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String nuovaPassword=request.getParameter("newPassword");
        Utente utente= new Utente();
        utente.setCodiceFiscale(codiceFiscale);
        utente.setNome(nomeUtente);
        utente.setCognome(cognomeUtente);
        utente.setEmail(email);
        utente.criptPassword(password);
        //utente.criptPassword(nuovaPassword);

        UtenteDAO utenteDAO= new UtenteDAO();
        HttpSession session= request.getSession();

        Utente utente1=utenteDAO.cercaUtente(codiceFiscale);

        Pattern pattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})$");
        Matcher matcher = pattern.matcher(nuovaPassword);
        String up="";

        if (!matcher.matches()) {
            up="La nuova password non rispetta il formato del pattern: deve contenere almeno una lettera minuscola, una maiuscola e un numero";
        } else
        if (utente1 != null) {

            if (utente1.getPassword().equals(utente.getPassword())) {
                utente.criptPassword(nuovaPassword);

                if (password.equals(nuovaPassword)) {
                    up = "La nuova password deve essere diversa da quella precedente";
                    System.out.println("goooooooollllllll");
                } else {

                    if (utenteDAO.updateUtente(utente)) {
                        up = "Dati Aggiornati Correttamente";
                        Utente u = (Utente) utenteDAO.cercaUtente(utente.getCodiceFiscale());

                        if (utente != null) {
                            session.setAttribute("utente", u);
                        }
                    }
                }
            } else {
                up = "La password non corrisponde con quella dell'utente loggato";
            }
        }

        request.setAttribute("update",up);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/InfoUtente.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
