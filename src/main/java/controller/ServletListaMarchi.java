package controller;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletListaMarchi", value = "/ServletListaMarchi")
public class ServletListaMarchi extends HttpServlet {
    private   static int start=0;
    private static  final  int end=9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opzione="Marchio";
        String nomeMarchio=(request.getParameter("value"));
        String nomejsp=request.getParameter("nomejsp");
        System.out.println("Nome jsp " + nomejsp);

        ProdottoDAO prodottoDAO= new ProdottoDAO();
        if(nomejsp.equals("header")){
            start=0;
        }else {
            start+=9;
        }
        ArrayList<Prodotto> prodotti= prodottoDAO.cercaProdottiMarchio(nomeMarchio,start,end);

        HttpSession session=request.getSession();
        System.out.println("start " + start + " end " +end);
        request.setAttribute("prodotti",prodotti);
        request.setAttribute("opzione",opzione);
        request.setAttribute("nomeMarchio",nomeMarchio);
        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/listaProdotti.jsp");
        dispatcher.forward(request,response);
    }
}

