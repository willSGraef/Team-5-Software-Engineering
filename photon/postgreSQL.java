package photon;
//Java postgreSQL Connection File

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class postgreSQL {
    //Init connection parameters
    String jdbcURL = "jdbc:postgresql://localhost:5432/photon";
    String username = "student";
    String password = "student";
    /*Init basic sql components, be sure to properly look at the documentation 
    for these under java.sql to know their functionaility*/
    Connection con;
    PreparedStatement p;
    ResultSet rs;
    //String for different queries we use in this class
    String query;
    
    //Connect method
    public void connect() throws SQLException {
        this.con = DriverManager.getConnection(jdbcURL, username, password);
    }

    //Disconnect method
    public void disconnect() throws SQLException {
        this.con.close();
    }

    //Read Player Table method
    //Check to see if player name and player ID is already on the table
    public boolean checkForPlayer() throws SQLException {

        return true;
    }
    //Creates players table
    public String createTable() throws SQLException {
        //Check for table named players already
        query = 
            """
                SELECT count(*) FROM information_schema.tables
                WHERE table_name = 'players' 
                LIMIT 1;    
            """;
        p = con.prepareStatement(query);
        rs = p.executeQuery();
        rs.next();
        if (rs.getInt(1) != 0) {
            return "Table already exists!";
        }
        else { 
            query = 
            """
                CREATE TABLE players (
                id INT,
                codename VARCHAR(30)
                );
            """;
            //Create table
            p = con.prepareStatement(query);
            rs = p.executeQuery();
            return "Players table created!";
        }
    }

    public String addID(int id) throws SQLException{
        /*Returns player codename associated with already created ID if an id is found 
        OR adds the id to the database and returns "ID has been added"*/
        //Check if id already exists
        query = 
            """
                SELECT id, codename FROM players;
            """;
        p = con.prepareStatement(query);
        rs = p.executeQuery();
        ArrayList<String> idArray = new ArrayList<String>();
        ArrayList<String> cnArray = new ArrayList<String>();

        while (rs.next()) {
            idArray.add(rs.getString(0));
            cnArray.add(rs.getString(1));
        }

        boolean idExists = false; //False if the id doesn't exist, true if it does
        for (int i = 0; i <= idArray.size(); i++) {
            if (idArray.get(i).equals(String.valueOf(id))) {
                idExists = true;
            }
        } 
        //Return the associated codename of the player ID found in the table
        if (idExists = true) {
            return cnArray.get(idArray.indexOf(String.valueOf(id)));
        }
        //Add ID in the case that it doesnt exist and add a placeholder name for codename to search for later
        else {
            query = 
            """
                INSERT INTO players (id, codename)
                VALUES (%s, %s)
            """.formatted(id, "PLACEHOLDER");
            p = con.prepareStatement(query);
            rs = p.executeQuery();
            return "ID added";
        }
    }

    public void addCodename(String codename) throws SQLException {
        //Add codename to field labeled placeholder
        query =
        """
            UPDATE players
            SET codename = %s
            WHERE codename = 'PLACEHOLDER';
        """.formatted(codename);
        p = con.prepareStatement(query);
        rs = p.executeQuery();
    }

    public static void main(String[] args) throws SQLException {
        //Register Driver
        postgreSQL sql = new postgreSQL();
        sql.connect();
    }
}