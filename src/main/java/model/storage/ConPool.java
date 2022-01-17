package model.storage;
        import org.apache.tomcat.jdbc.pool.PoolProperties;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.TimeZone;

/**
 *
 * @author Deny Prasetyo
 */
public class ConPool {

    private static ConPool instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/PharmaLife?serverTimezone="+ TimeZone.getDefault().getID();
    private String username = "root";
    private String password = "1234";

    private ConPool() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConPool();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConPool();
        }

        return instance;
    }
}