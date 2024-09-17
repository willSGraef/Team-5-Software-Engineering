//Java postgreSQL Connection File

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class postgreSQL {
    //Init connection parameters
    String jdbcURL = "jdbc:postgresql://[10.0.2.15]:5432/photon";
    String username = "student";
    String password = "student";
    Connection connection;
    
    //Connect method
    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(jdbcURL, username, password);
    }

    //Disconnect method
    public void disconnect() throws SQLException {
        this.connection.close();
    }

    public static void main(String[] args) throws SQLException {
        //Register Driver
        Class.forName("org.postgresql.Driver");
        postgreSQL sql = new postgreSQL();
        sql.connect();
    }
}