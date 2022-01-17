package controller.utente;

import model.carrello.Carrello;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.SQLException;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@WebServlet(name = "ServletAccessoUtente", value = "/ServletAccessoUtente")
public class ServletAccessoUtente extends HttpServlet {

    private UtenteDAOMethod serviceUtenteDAO;
    private Utente utente;
    private Carrello carrello;

    public ServletAccessoUtente() throws SQLException {
        this.utente=new Utente();
        this.serviceUtenteDAO=new UtenteDAO();
        this.carrello=new Carrello();
    }

    public ServletAccessoUtente(UtenteDAO serviceUtenteDAO,Utente utente,Carrello carrello){
        this.utente=utente;
        this.serviceUtenteDAO=serviceUtenteDAO;
        this.carrello=carrello;
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String valore = request.getParameter("value");
        switch (valore) {
            case "login":
                loginUtente(request,response);
                break;
            case "logout":
                logoutUtente(request,response);
        }
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Questo metodo serve per effettuare il logout di un Utente, per poter effettuare questa operazione è
     * necessario che ci sia un Utente loggato in sessione, al termine dell'esecuzione del metodo non sarà
     * più presente nella sessione
     * @pre session.contains(utente)
     * @param request
     * @param response
     * @throws IOException
     * @post !session.contains(utente)
     */
    public void logoutUtente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
         utente = (Utente) session.getAttribute("utente");
        if (utente != null) {
            session.removeAttribute("utente");
            carrello = (Carrello) session.getAttribute("carrello");
            if (carrello != null) {
                session.removeAttribute("carrello");
            }
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));

        }

    }




    /**
     * Questo metodo serve per effettuare il login di un Utente, al termine dell'esecuzione del metodo l'Utente
     * sarà presente in sessione
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @post session.contains(utente)
     */
    public void loginUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
         utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            String email = request.getParameter("emailUser");
            String password = request.getParameter("password");
             //serviceUtenteDAO = new UtenteDAO();
            utente = (Utente) serviceUtenteDAO.cercaUtentebyEmail(email, password);
            if (utente != null) {
                if (session.getAttribute("carrello") != null) {
                    session.removeAttribute("carrello");
                }
                session.setAttribute("utente", utente);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
            } else {
                request.setAttribute("errore", "Utente non trovato!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
