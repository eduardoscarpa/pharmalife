package controller.global;

import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@WebServlet(name = "ServletLink", value = "/ServletLink")

@Generated
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
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(pagina);
        dispatcher.forward(request,response);

    }
}
