package storage.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@Generated
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
            System.out.println("Database Connection Creation Failed: " + ex.getMessage());
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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