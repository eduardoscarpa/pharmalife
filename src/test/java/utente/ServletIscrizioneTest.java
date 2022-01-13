package utente;



import controller.utente.ServletAssistenza;
import controller.utente.ServletIscrizione;
import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletIscrizioneTest {

    @Mock
    private Utente utente;

    @Mock
    private UtenteDAO utenteDAO;

    private String address;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletIscrizione servletIscrizione;




    @Mock
    private HttpSession s;

    private String fn;
    private String ln;
    private String cf;
    private String email;
    private String psw;
    private String psw_rip;
    private String via;
    private int numeroCivico;
    private String cap;
    private String telefono;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*private void setAttributesRequest(String fn,String ln,String cf, String email,String psw,String psw_rip,String via, int numeroCivico,String cap,String telefono){

        request.setAttribute("fn",fn);
        request.setAttribute("ln", ln);
        request.setAttribute("cf", cf);
        request.setAttribute("email", email);
        request.setAttribute("psw", psw);
        request.setAttribute("psw_rip", psw_rip);
        request.setAttribute("numeroCivico", numeroCivico);
        request.setAttribute("cap", cap);
        request.setAttribute("telefono", telefono);
    }*/



    @Test
    public void doGetTest() throws ServletException, IOException, SQLException {
    }

    
    @Test
    public void registraUtenteTest() throws ServletException, SQLException, IOException {

        when(request.getSession()).thenReturn(s);
        when(request.getParameter("fn")).thenReturn("Matteo");
        when(request.getParameter("ln")).thenReturn("Rispoli");
        when(request.getParameter("cf")).thenReturn("RSPMTT80A01L245X");
        when(request.getParameter("email")).thenReturn("matt@live.it");
        when(request.getParameter("psw")).thenReturn("Matteo80");
        when(request.getParameter("psw_rip")).thenReturn("Matteo80");
        when(request.getParameter("via")).thenReturn("Vittorioveneto");
        when(request.getParameter("numeroCivico")).thenReturn("223");
        when(request.getParameter("cap")).thenReturn("80058");
        when(request.getParameter("telefono")).thenReturn("331-445-50-76");
        //Utente utente=new Utente();
        fn=request.getParameter("fn");
        address="index.jsp";
         ln=request.getParameter("ln");
         cf=request.getParameter("cf");
        email=request.getParameter("email");
        psw=request.getParameter("psw");
        psw_rip=request.getParameter("psw_rip");
        via=request.getParameter("via");
        numeroCivico=Integer.parseInt(request.getParameter("numeroCivico"));
        cap=request.getParameter("cap");
        telefono=request.getParameter("telefono");
        servletIscrizione= new ServletIscrizione(utenteDAO,address);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(address)).thenReturn(requestDispatcher);
        servletIscrizione.registraUtente(fn,ln,cf,email,psw,psw_rip,via,numeroCivico,cap,telefono,request,response);
        verify(requestDispatcher).forward(request, response);
        assertParametersRequest(fn,ln,cf,email,psw,psw_rip,via,numeroCivico,cap,telefono);

    }



    @Test
    public void registraUtenteTestCarrelloAddressNoContainsIndexTest() throws ServletException, SQLException, IOException {
        address="dsafgasfsa";

        servletIscrizione.registraUtente(fn,ln,cf,email,psw,psw_rip,via,numeroCivico,cap,telefono,request,response);
        assertEquals(address.contains("index"),false);
        /*String message=null;
        verify(request).setAttribute("iscriviti",message);
    */
    }


    @Test
    public void isNotPresentCfTest() throws SQLException {
        ArrayList<String> codiciFiscali=new ArrayList<>();
        codiciFiscali.add("dsadas");
        codiciFiscali.add(cf);
        when(utenteDAO.doRetraiveByAllCodiciFiscali()).thenReturn(codiciFiscali);
        assertEquals(true,codiciFiscali.contains(cf));
    }

    @Test
    public void isNotPresentEmailTest() throws SQLException {
        ArrayList<String> arrayEmail=new ArrayList<>();
        arrayEmail.add("dsadas@hotmail.it");
        arrayEmail.add(email);
        when(utenteDAO.doRetraiveByAllCodiciFiscali()).thenReturn(arrayEmail);
        assertEquals(true,arrayEmail.contains(email));
    }






    private void assertParametersRequest(String fn,String ln,String cf, String email,String psw,String psw_rip,String via, int numeroCivico,String cap,String telefono){
        assertEquals("Matteo",fn);
        assertEquals("Rispoli",ln);
        assertEquals("RSPMTT80A01L245X",cf);
        assertEquals("matt@live.it",email);
        assertEquals("Matteo80",psw);
        assertEquals("Matteo80",psw_rip);
        assertEquals("Vittorioveneto",via);
        assertEquals(223,numeroCivico);
        assertEquals("80058",cap);
        assertEquals("331-445-50-76",telefono);

    }
    }
