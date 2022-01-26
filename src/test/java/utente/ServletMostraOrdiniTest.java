package utente;

import application.utenteService.ServletMostraOrdini;
import storage.ordine.Ordine;
import storage.ordine.OrdineDAO;
import storage.utente.Utente;
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
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ServletMostraOrdiniTest {

    @Mock
    private OrdineDAO ordineDAO;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletMostraOrdini servletMostraOrdini;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletMostraOrdini=new ServletMostraOrdini(ordineDAO);
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        Utente utente=new Utente();
        ArrayList<Ordine> ordini=new ArrayList<Ordine>();
        ordini.add(new Ordine());
        ordini.add(new Ordine());
        ordini.add(new Ordine());
        when(ordineDAO.doRetraiveByAllById(utente)).thenReturn(ordini);
        when(request.getRequestDispatcher("WEB-INF/pagine/mostraOrdini.jsp")).thenReturn(dispatcher);
        servletMostraOrdini.doPost(request,response);
        verify(dispatcher).forward(request,response);
    }
}
