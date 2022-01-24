package assistenza;

import org.junit.Before;
import org.junit.Test;
import storage.messaggio.Messaggio;
import storage.messaggio.MessaggioDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class MessaggioDAOIntegrationTest {

    private MessaggioDAO messaggioDAO;

    @Before
    public void setUp() throws SQLException {
        messaggioDAO= new MessaggioDAO();
    }

    @Test
    public void doRetrieveByAllMessaggiTest(){
        ArrayList<Messaggio> messaggi= messaggioDAO.doRetrieveByAllMessaggi();
        assertNotEquals(null, messaggi);
    }
}
