package utente;



import controller.utente.ServletIscrizione;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void registraUtenteTest() throws ServletException, SQLException, IOException {

        when(request.getSession()).thenReturn(s);
        when(request.getParameter("fn")).thenReturn("Matteo");
        when(request.getParameter("ln")).thenReturn("Rispoli");
        when(request.getParameter("cf")).thenReturn("RSPMTT80A01L245X");
        when(request.getParameter("email")).thenReturn("matt@live.it");
        when(request.getParameter("psw")).thenReturn("Matteo80");
        when(request.getParameter("psw_rip")).thenReturn("PassDiversa89");
        when(request.getParameter("via")).thenReturn("Vittorioveneto");
        when(request.getParameter("numeroCivico")).thenReturn("223");
        when(request.getParameter("cap")).thenReturn("80058");
        when(request.getParameter("telefono")).thenReturn("331-445-50-76");

        fn=request.getParameter("fn");
        address="WEB-INF/pagine/iscriviti.jsp";
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
        assertEquals("PassDiversa89",psw_rip);
        assertEquals("Vittorioveneto",via);
        assertEquals(223,numeroCivico);
        assertEquals("80058",cap);
        assertEquals("331-445-50-76",telefono);

    }
}
