package controller;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletAggiungiAlCarrello", value = "/ServletAggiungiAlCarrello")
public class ServletAggiungiAlCarrello extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idProdotto=Integer.parseInt(request.getParameter("prodotto"));
       // int totale=Integer.parseInt(request.getParameter("totale"));
        int totale=1;
        if(request.getParameter("totale")!=null){
            totale=Integer.parseInt(request.getParameter("totale"));
        }
        int prezzoTotale=0;
        System.out.println("Id prodotto " + idProdotto );
        HttpSession session=request.getSession();
        Carrello carrello=(Carrello) session.getAttribute("carrello");

        Utente utente=(Utente) session.getAttribute("utente");
        ProdottoDAO prodottoDAO= new ProdottoDAO();
        Prodotto prodotto= prodottoDAO.cercaProdotto(idProdotto);
        if(utente!=null){

            if(utente.getCarrello()!=null){
                prodotto.setPrezzoQuantita(totale);
                utente.getCarrello().addProdotto(prodotto);
            }else {
                Carrello carrello1= new Carrello();
                prodotto.setPrezzoQuantita(totale);
                carrello1.addProdotto(prodotto);
                utente.setCarrello(carrello1);
            }
        }else{
           // Carrello carrello=(Carrello) session.getAttribute("carrello");
            if(carrello!=null) {
                prodotto.setPrezzoQuantita(totale);
                carrello.addProdotto(prodotto);
                session.setAttribute("carrello",carrello);
            }else{

                carrello= new Carrello();
                prodotto.setPrezzoQuantita(totale);
                carrello.addProdotto(prodotto);
                session.setAttribute("carrello",carrello);
                session.setMaxInactiveInterval(60);
            }


        }
       response.getWriter().write("Prodotto Aggiunto al carrello");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
