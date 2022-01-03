package test;

import controller.utente.ServletIscrizione;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import static  org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EsercitazioneTest {


    @Test
    public void provaTest() throws SQLException {
        ServletIscrizione servletIscrizione= Mockito.mock(ServletIscrizione.class);
        Mockito.when(servletIscrizione.isNotPresentCf("FRRCMN99R25A361J")).thenReturn(true);
        boolean val=servletIscrizione.isNotPresentCf("FRRCMN99R25A361J");
        Mockito.verify(servletIscrizione, Mockito.times(1)).isNotPresentCf("FRRCMN99R25A361J");

        assertEquals("Ok mi trovo", true,val);


    }
}
