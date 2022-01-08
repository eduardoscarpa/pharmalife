package utente;

import controller.admin.ServletAdmin;
import controller.utente.ServletAssistenza;
import model.messaggio.Messaggio;
import model.messaggio.MessaggioDAO;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
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
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ServletAssistenzaTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private ServletAssistenza servletAssistenza;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        servletAssistenza= new ServletAssistenza();

    }

    @Test
    public void doPostTest() throws ServletException, IOException {

        RequestDispatcher requestDispatcher=mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/pagine/contatti.jsp")).thenReturn(requestDispatcher);
        servletAssistenza.doPost(request,response);
        verify(requestDispatcher).forward(request, response);
    }
}
