package controller.utente;

import model.carrello.Carrello;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ServletAccessoUtente", value = "/ServletAccessoUtente")
public class ServletAccessoUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  HttpSession session = request.getSession();
    //    Utente utente = (Utente) session.getAttribute("utente");
        String valore = request.getParameter("value");
        switch (valore) {
            case "login":

                loginUtente(request,response);

                break;
            case "logout":
                logoutUtente(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    private void logoutUtente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null) {

            session.removeAttribute("utente");
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if (carrello != null) {
                session.removeAttribute("carrello");
            }

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
        }

    }

    private void loginUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {

            String email = request.getParameter("emailUser");
            String password = request.getParameter("password");
            UtenteDAO utenteDAO = new UtenteDAO();
            utente = (Utente) utenteDAO.cercaUtentebyEmail(email, password);

            if (utente != null) {
                if (session.getAttribute("carrello") != null) {
                    session.removeAttribute("carrello");
                }

                session.setAttribute("utente", utente);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));

            } else {
                request.setAttribute("errore", "Utente non trovato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pagine/formLogin.jsp");
                dispatcher.forward(request, response);
            }

        }
    }
}
