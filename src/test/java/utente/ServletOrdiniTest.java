package utente;

import controller.utente.ServletMostraPref;
import controller.utente.ServletOrdini;
import model.carrello.Carrello;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServletOrdiniTest  {

    @Mock
    OrdineDAO ordineDAO;
    @Mock
    Ordine ordine;
    @Mock
    Carrello carrello;
    @Mock
    Utente utente;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ArrayList<Prodotto> prodotti;

    private ServletOrdini servletOrdini;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletOrdini = new ServletOrdini();
    }

    @Test
    public void doPostSessionNotNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Utente utente= new Utente();
        utente.setCodiceFiscale("GFGHHH88U88V678G");
        session.setAttribute("utente", utente);
        when(session.getAttribute("utente")).thenReturn(utente);
        //StringBuilder prod = new StringBuilder();
        //Carrello carrello = new Carrello();
        Carrello carr = new Carrello();
        //when(utente.getCarrello()).thenReturn(carr);
        Ordine ordine = new Ordine();
        ordine.setCarrello(carr);
        Time time = new Time(11,30,0);
        ordine.setOra(time);
        Date date = new Date(2022, 0, 9);
        ordine.setDataOrdine(date);
        assertEquals(carr, carrello);
        Time time1 = new Time(11, 30, 0);
        assertEquals(time1, time);
        Date date1 = new Date(2022, 0, 9);
        assertEquals(date1, date);

        servletOrdini.doPost(request, response);

        //servletOrdini.doPost(request, response);
        ordineDAO.insertCarrello(ordine);
        verify(ordineDAO).insertCarrello(ordine);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(requestDispatcher);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPostSessionNullTest() throws ServletException, IOException {
        //HttpSession session = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(null);
        HttpSession session = request.getSession();
        servletOrdini.doPost(request,response);
        assertEquals(null, session);
    }

    @Test
    public void doPostUtenteNullTest() throws ServletException, IOException {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        servletOrdini.doPost(request, response);
        assertEquals(null, utente);
        assertEquals(null, request.getAttribute("prodottiPref"));
    }

    @Test
    public void doPostCarrelloNullTest() {
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        Utente utente=(Utente) session.getAttribute("utente");
        assertEquals(null, utente);
        assertEquals(null, request.getAttribute("prodottiPref"));
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        session.setAttribute("utente", ordine);
        when(session.getAttribute("utente")).thenReturn(utente);

        //Carrello carrello = new Carrello();
        Ordine ordine1 = new Ordine();
        ordine1.setCarrello(carrello);
        System.out.println(ordine1.getCarrello());
        Time time = new Time(11,30,0);
        ordine1.setOra(time);
        System.out.println(ordine1.getOra());
        Date date = new Date(2022, 0, 9);
        ordine1.setDataOrdine(date);

        //Carrello carrello = utente.getCarrello();

        assertEquals(carrello, carrello);
        //assertEquals(11, time);
        Time time1 = new Time(11, 30, 0);
        assertEquals(time1, time);

        Date date1 = new Date(2022, 0, 9);
        assertEquals(date1, date);
        /*
        Utente utente1 = new Utente();
        utente1.setCodiceFiscale("GFGHHH88U88V678A");
        utente1.setNome("catello");
        utente1.setEmail("cat@gmail.it");
        utente1.setPassword("Catello1");
        utente1.setVia("ponte");
        utente1.setNumeroCivico(123);
        utente1.setCap("78900");
        utente1.setTelefono("5465767890");


        System.out.println(utente1.getCodiceFiscale());
        String codiceFiscale = utente1.getCodiceFiscale();
        String nome = utente1.getNome();
        String cognome = utente1.getCognome();
        String email = utente1.getEmail();
        String password = utente1.getPassword();
        String via = utente1.getVia();
        String cap = utente1.getCap();
        String telefono = utente1.getTelefono();
        int numeroCivico = utente1.getNumeroCivico();

         */
        /*Utente utente = new Utente(codiceFiscale, nome, cognome, email,
                password, via, numeroCivico, cap, telefono, false, prodotti);

         */
        /*
        ordine.setUtente(utente1);
        assertEquals("GFGHHH88U88V678A", codiceFiscale);
        assertEquals("catello", nome);
        assertEquals("cat@gmail.it", email);
        assertEquals("Catello1", password);
         */
        //assertEquals();

        when(request.getRequestDispatcher("WEB-INF/pagine/carrello.jsp")).thenReturn(requestDispatcher);
        servletOrdini.doPost(request, response);
        ordineDAO.insertCarrello(ordine1);
        verify(ordineDAO).insertCarrello(ordine1);
        verify(requestDispatcher).forward(request, response);
    }
}