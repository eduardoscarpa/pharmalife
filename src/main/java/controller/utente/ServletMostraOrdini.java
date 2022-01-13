package controller.utente;

import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.ordine.OrdineDAOMethod;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletMostraOrdini", value = "/ServletMostraOrdini")
public class ServletMostraOrdini extends HttpServlet {
    private OrdineDAOMethod ordineDAO;

    public ServletMostraOrdini(){
        ordineDAO= new OrdineDAO();
    }
    public ServletMostraOrdini(OrdineDAO ordineDAO){
        this.ordineDAO=ordineDAO;
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente utente=(Utente) session.getAttribute("utente");
        ArrayList<Ordine> ordini= ordineDAO.doRetraiveByAllById(utente);
        request.setAttribute("ordini",ordini);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/mostraOrdini.jsp");
        dispatcher.forward(request,response);
    }
}
