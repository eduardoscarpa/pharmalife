package controller;

import model.carrello.Carrello;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ServletIscrizione", value = "/ServletIscrizione")
public class ServletIscrizione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controllo lato server
        String address="index.jsp";
        String fn=request.getParameter("nome");
        String ln=request.getParameter("cognome");
        String cf= request.getParameter("CodiceFiscale");
        String email=request.getParameter("email");
        String psw=request.getParameter("psw");
        String psw_rip=request.getParameter("psw-rip");
        String via=request.getParameter("via");
        int numeroCivico=Integer.parseInt(request.getParameter("numeroCivico"));
        String cap=request.getParameter("cap");
        String telefono=request.getParameter("telefono");
        System.out.println(request.getParameter("telefono"));
        UtenteDAO service=new UtenteDAO();
        Utente c=new Utente();
        String up="";

        Pattern nome = Pattern.compile("^([a-z A-Z]{3,})$");
        Matcher matcher = nome.matcher(fn);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Il nome deve essere formato solo da lettere e deve contenere almeno tre caratteri";
        }

        Pattern cognome = Pattern.compile("^([a-z A-Z]{3,})$");
        matcher = cognome.matcher(ln);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Il cognome deve essere formato solo da lettere e deve contenere almeno tre caratteri";
        }

        Pattern codiceFiscale = Pattern.compile("(^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$)");
        matcher = codiceFiscale.matcher(cf);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Codice fiscale non valido";
        }

        Pattern e_mail = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$");
        matcher = e_mail.matcher(email);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Formato email non valido";
        }

        //Pattern password = Pattern.compile("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$)");
        Pattern password = Pattern.compile("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$)");
        matcher = password.matcher(psw);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "La password deve contenere almeno una lettera minuscola, una maiuscola e un numero";
        }

        if (!psw_rip.equals(psw)) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "La password non coincide con quella digitata precedentemente";
        }

        Pattern numCivico = Pattern.compile(("^[0-9]{1,3}$"));
        matcher = numCivico.matcher(Integer.toString(numeroCivico));
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Il numero civico deve contenere solo numeri (da una a tre cifre)";
        }

        Pattern codicePostale = Pattern.compile(("^[0-9]{5}$"));
        matcher = codicePostale.matcher(cap);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Il cap deve contenere esattamente 5 cifre";
        }

        Pattern numTelefono = Pattern.compile(("^[0-9]{10}$"));
        matcher = numTelefono.matcher(telefono);
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            address = "WEB-INF/pagine/iscriviti.jsp";
            up = "Il numero di telefono deve contenere esattamente 10 cifre";
        }

        c.setNome(fn);
        c.setCognome(ln);
        c.setCodiceFiscale(cf);
        c.setEmail(email);
        c.criptPassword(psw);
        c.setVia(via);
        c.setNumeroCivico(numeroCivico);
        c.setCap(cap);
        c.setTelefono(telefono);

        if(address.contains("index")) {
            service.insertUtente(c);
           // request.setAttribute("customer", c);
            HttpSession session=request.getSession();
            if(session.getAttribute("carrello")!=null){
                Carrello carrello=(Carrello) session.getAttribute("carrello");
                c.setCarrello(carrello);
            }
           session.setAttribute("utente",c);

        }else{
            request.setAttribute("iscriviti", up);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
