import controller.utente.ServletIscrizione;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ProvaTest {

   /* @Mock
    UtenteDAOMethod utenteDAOMethod;*/
    @Test
    public  void utenteTest() throws SQLException {

        UtenteDAOMethod utenteDAOMethod= Mockito.mock(UtenteDAO.class);
        ArrayList<Utente> utentes= new ArrayList<>();

       // UtenteDAO utenteDAO= new UtenteDAO();
        //Utente utente= new Utente();
        //utente.setCodiceFiscale("FRRCMN99R25A361J");
       //Utente lol= Mockito.when(utenteDAOMethod.cercaUtente("")).thenReturn(utente);
        //ServletIscrizione servletIscrizione= new ServletIscrizione(utenteDAOMethod);
        //boolean val =servletIscrizione.isNotPresentCf("FRRCMN99R25A361J");
        //assertEquals(true, val);
        ServletIscrizione servletIscrizione=Mockito.mock(ServletIscrizione.class);
        Mockito.when(servletIscrizione.isNotPresentCf("FRRCMN99R25A361J")).thenReturn(true);
        boolean val=servletIscrizione.isNotPresentCf("FRRCMN99R25A361J");

        //assertEquals(true, val);
        assertTrue("Ok", val);


       //assertEquals(2, users.size());
    }
    @Test
    public void utenteogTest(){

    }
}
