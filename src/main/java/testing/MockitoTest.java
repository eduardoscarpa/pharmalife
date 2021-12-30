package testing;


import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MockitoTest {

   /* @Mock
    UtenteDAOMethod utenteDAOMethod;*/
    @Test
    public  void test1(){
        //utenteDAOMethod=new UtenteDAO();
       // Mockito.when(utenteDAOMethod.cercaUtente("BBRRTUAN")).thenReturn(, )
        //UtenteDAOMethod utenteDAOMethod= Mockito.mock(UtenteDAO.class);
        UtenteDAO utenteDAO= new UtenteDAO();
       ArrayList<Utente> users=utenteDAO.doRetrieveByAllUtenti();

       assertEquals(2, users.size());

    }
}
