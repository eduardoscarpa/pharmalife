package application.utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletAssistenza", value = "/ServletAssistenza")
public class ServletAssistenza extends HttpServlet {

    @Generated
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/pagine/contatti.jsp");
        dispatcher.forward(request,response);
    }
}
