package controller;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletListaProdotti", value = "/ServletListaProdotti")
public class ServletListaProdotti extends HttpServlet {
    private   static int start=0;
    private static  final  int end=9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opzione="Categoria";
        int idCategoria=Integer.parseInt(request.getParameter("value"));
        String nomejsp=request.getParameter("nomejsp");
        System.out.println("Nome jsp " + nomejsp);

        ProdottoDAO prodottoDAO= new ProdottoDAO();
        if(nomejsp.equals("header")){
            start=0;
        }else {
            start+=9;
        }
        ArrayList<Prodotto> prodotti= prodottoDAO.cercaProdotti(idCategoria,start,end);


        HttpSession session=request.getSession();
       // System.out.println("start " + start + " end " +end);
        request.setAttribute("prodotti",prodotti);
        request.setAttribute("idCategoria",idCategoria);
        request.setAttribute("opzione",opzione);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp");
        dispatcher.forward(request,response);
    }
}
