package ClassiStub;

public class ConPoolStub {

   // private DataSource dataSource;

    public static ConnectionStub getConnection(){

        ConnectionStub connectionS= new ConnectionStub();
        return connectionS;
    }

}
