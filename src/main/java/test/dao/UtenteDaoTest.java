package test.dao;

import model.utente.Utente;
import model.utente.UtenteDAO;
import org.junit.Test;
import static org.mockito.Mock.*;
import static org.junit.Assert.*;

public class UtenteDaoTest {


    @Test
    public void cercaUtenteTest(){
        UtenteDAO utenteDAO= new UtenteDAO();
        String utente=utenteDAO.cercaUtente("BBRRTUAN").getNome();
        //assertNotEquals("Ok mi trovo", "Matteyo", utente.getNome());
        assertNotEquals("Non mi trovo ", "Matteo", utente);
    }

}
