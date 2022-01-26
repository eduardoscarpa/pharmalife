package application.carrelloService;
import storage.carrello.Carrello;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
import storage.prodotto.ProdottoDAOMethod;
import storage.utente.Utente;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletAggiungiAlCarrello", value = "/ServletAggiungiAlCarrello")
public class ServletAggiungiAlCarrello extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;
    private Carrello carrello;
    private Utente utente;
    private Prodotto prodotto;
    private int totale;

    public ServletAggiungiAlCarrello() throws SQLException {
        prodottoDAO=new ProdottoDAO();
        utente= new Utente();
        prodotto= new Prodotto();
        carrello= new Carrello();
    }

    public ServletAggiungiAlCarrello(ProdottoDAO prodottoDAO,Carrello carrello,Utente utente,Prodotto prodotto){
        this.prodottoDAO=prodottoDAO;
        this.carrello=carrello;
        this.utente=utente;
        this.prodotto=prodotto;
    }

    /**
     *
     * @param request oggetto della Servlet contenente i valori inviati dal client relativi all'identificativo del prodotto da aggiungere
     * al carrello
     * @param response oggetto della Servlet utile per inviare l'esisto dell'operazione al client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto=Integer.parseInt(request.getParameter("prodotto"));
         totale=1;
        if(request.getParameter("totale")!=null){
            totale=Integer.parseInt(request.getParameter("totale"));
        }
        HttpSession session=request.getSession();
        carrello=(Carrello) session.getAttribute("carrello");
        utente=(Utente) session.getAttribute("utente");
        prodotto= prodottoDAO.cercaProdotto(idProdotto);
        aggiuntaAlCarrello(idProdotto,session);
        PrintWriter printWriter= response.getWriter();
        printWriter.write("Prodotto aggiunto al carrello!");
    }

    @Generated
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo serve per aggiungere un prodotto al carrello
     * @pre //
     * @param idProdotto del prodotto da aggiungere al carrello
     * @param session
     * @throws IOException
     * <p><b>post</b></p> Carrello.getNumProdotti = @pre Carrello.getNumProdotti + 1
     */
    public void aggiuntaAlCarrello(int idProdotto,HttpSession session) throws IOException{
        if(utente!=null){
            if(utente.getCarrello()!=null){
                prodotto.setPrezzoQuantita(totale);
                utente.getCarrello().addProdotto(prodotto);
            }else {
                carrello= new Carrello();
                prodotto.setPrezzoQuantita(totale);
                carrello.addProdotto(prodotto);
                utente.setCarrello(carrello);
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
    }
}
