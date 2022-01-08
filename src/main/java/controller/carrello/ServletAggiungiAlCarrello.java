package controller.carrello;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletAggiungiAlCarrello", value = "/ServletAggiungiAlCarrello")
public class ServletAggiungiAlCarrello extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletAggiungiAlCarrello(){
        prodottoDAO=new ProdottoDAO();
    }

    public ServletAggiungiAlCarrello(ProdottoDAO prodottoDAO){
        this.prodottoDAO=prodottoDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto=Integer.parseInt(request.getParameter("prodotto"));
        aggiuntaAlCarrello(idProdotto, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo serve per aggiungere un prodotto al carrello
     * @pre //
     * @param idProdotto del prodotto da aggiungere al carrello
     * @param request
     * @param response
     * @throws IOException
     * @post getNumProdotti = @pre getNumProdotti + 1
     */
    public void aggiuntaAlCarrello(int idProdotto, HttpServletRequest request, HttpServletResponse response) throws IOException{
        int totale=1;
        if(request.getParameter("totale")!=null){
            totale=Integer.parseInt(request.getParameter("totale"));
        }
        //int prezzoTotale=0;
        HttpSession session=request.getSession();
        Carrello carrello=(Carrello) session.getAttribute("carrello");

        Utente utente=(Utente) session.getAttribute("utente");

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
        response.getWriter().write("Prodotto aggiunto al carrello!");
    }
}
