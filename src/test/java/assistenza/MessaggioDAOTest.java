package assistenza;

import org.junit.Before;
import org.junit.Test;
import storage.messaggio.Messaggio;
import storage.messaggio.MessaggioDAO;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessaggioDAOTest {

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
