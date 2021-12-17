package controller;

import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletLink", value = "/ServletLink")
public class ServletLink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String richiesta=request.getParameter("scelta");
        HttpSession session= request.getSession();
        Utente utente=(Utente) session.getAttribute("utente");
        System.out.println("Sessione " );
        String pagina="";
        switch (richiesta){

            case "home": pagina="/index.jsp";
                break;
            case "login" :
                if(utente!=null) {
                    pagina="WEB-INF/pagine/mioAccount.jsp";

                }else {
                    pagina = "WEB-INF/pagine/formLogin.jsp";
                }
                break;
            case "iscriviti" :
                pagina="/WEB-INF/pagine/iscriviti.jsp";
                break;
            case "mioAccount" :
                pagina="WEB-INF/pagine/mioAccount.jsp";
                break;
            case "preferiti"  :
                pagina="WEB-INF/pagine/preferiti.jsp";
                break;
            case "assistenza" :
                pagina="WEB-INF/pagine/assistenza.jsp";
                break;
            case "infoAzienda" :
                pagina="WEB-INF/pagine/infoAzienda.jsp";
                break;
            case "contatti"   :
                pagina="WEB-INF/pagine/contatti.jsp";
                break;
            case "prodotti" :
                pagina="WEB-INF/pagine/listaProdotti.jsp";
                break;
            case "admin"    :
                pagina="WEB-INF/pagine/admin/areaAmministratore.jsp";
                break;
            case "termini"  :
                pagina="WEB-INF/pagine/terminiCondizioni.jsp";
                break;
            case "faq"      :
                pagina="WEB-INF/pagine/faq.jsp";
                break;
            case "info"     :
                pagina="WEB-INF/pagine/InfoUtente.jsp";
                break;
            case "carrello" :
                pagina="WEB-INF/pagine/carrello.jsp";
                break;
            case "indirizzo":
                pagina="WEB-INF/pagine/updateIndirizzo.jsp";
                break;
            case "ordini" :
                pagina="ServletMostraOrdini";
                break;
          /*  default:pagina="webapp/index.jsp";
                break;*/
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(pagina);
        dispatcher.forward(request,response);

    }
}
