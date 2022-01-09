package dao;

import model.storage.ConPool;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConPool.class ,Connection.class})

public class UtenteDAOTest {


    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cercaUtenteTest() throws SQLException {
        PowerMockito.mockStatic(ConPool.class);
        when(ConPool.getConnection()).thenReturn(connection);

    }
}
