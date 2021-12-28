package controller.utente;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ServletIscrizione", value = "/ServletIscrizione")
public class ServletIscrizione extends HttpServlet {
    private String message;
    private String address;
    HttpServletRequest request;
    HttpServletResponse response;
    @Override
    protected void doGet(HttpServletRequest request1, HttpServletResponse response) throws ServletException, IOException {
       // String cf= request.getParameter("CodiceFiscale");
        this.request=request1;
        this.response=response;
        address="index.jsp";
        message="";
        try {
            saveParameter();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void registraUtente(String fn,String ln,String cf, String email,String psw,String psw_rip,String via,
                                int numeroCivico,String cap,String telefono) throws ServletException, IOException, SQLException {
            UtenteDAO service=new UtenteDAO();
            Utente utente = new Utente();
            Pattern nome = Pattern.compile("^([a-z A-Z]{3,})$");
            Matcher matcher = nome.matcher(fn);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Il nome deve essere formato solo da lettere e deve contenere almeno tre caratteri";
            }
            Pattern cognome = Pattern.compile("^([a-z A-Z]{3,})$");
            matcher = cognome.matcher(ln);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Il cognome deve essere formato solo da lettere e deve contenere almeno tre caratteri";
            }

            Pattern codiceFiscale = Pattern.compile("(^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$)");
            matcher = codiceFiscale.matcher(cf);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Codice fiscale non valido";
            }

            Pattern e_mail = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$");
            matcher = e_mail.matcher(email);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Formato email non valido";
            }

            //Pattern password = Pattern.compile("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$)");
            Pattern password = Pattern.compile("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$)");
            matcher = password.matcher(psw);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "La password deve contenere almeno una lettera minuscola, una maiuscola e un numero";
            }

            if (!psw_rip.equals(psw)) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "La password non coincide con quella digitata precedentemente";
            }

            Pattern numCivico = Pattern.compile(("^[0-9]{1,3}$"));
            matcher = numCivico.matcher(Integer.toString(numeroCivico));
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Il numero civico deve contenere solo numeri (da una a tre cifre)";
            }
            Pattern codicePostale = Pattern.compile(("^[0-9]{5}$"));
            matcher = codicePostale.matcher(cap);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Il cap deve contenere esattamente 5 cifre";
            }
            Pattern numTelefono = Pattern.compile(("^[0-9]{10}$"));
            matcher = numTelefono.matcher(telefono);
            System.out.println(matcher.matches());
            if (!matcher.matches()) {
                address = "WEB-INF/pagine/iscriviti.jsp";
                message = "Il numero di telefono deve contenere esattamente 10 cifre";
            }

            utente.setNome(fn);
            utente.setCognome(ln);
            utente.setCodiceFiscale(cf);
            utente.setEmail(email);
            utente.criptPassword(psw);
            utente.setVia(via);
            utente.setNumeroCivico(numeroCivico);
            utente.setCap(cap);
            utente.setTelefono(telefono);

            if (address.contains("index")) {
                service.insertUtente(utente);
                HttpSession session = request.getSession();

                if (session.getAttribute("carrello") != null) {
                    Carrello carrello = (Carrello) session.getAttribute("carrello");
                    utente.setCarrello(carrello);
                }
                session.setAttribute("utente", utente);

            } else {
                request.setAttribute("iscriviti", message);
            }
        //}
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    private boolean isNotPresentCf(String cf) throws SQLException {
        UtenteDAO service=new UtenteDAO();
        ArrayList<String> codiciFiscali=service.doRetraiveByAllCodiciFiscali();
        if (codiciFiscali.contains(cf)){
            address = "WEB-INF/pagine/iscriviti.jsp";
            message="Questo codice fiscale è già presente";
            request.setAttribute("iscriviti", message);
            return  false;
        }
        return true;
    }

    private  void saveParameter( ) throws SQLException, ServletException, IOException {
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
        if (isNotPresentCf(cf)){
            registraUtente(fn,ln,cf,email,psw,psw_rip,via,numeroCivico,cap,telefono);
        }else {
            request.setAttribute("iscriviti", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

}
