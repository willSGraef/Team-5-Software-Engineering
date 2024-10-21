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
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        query = "SELECT count(*) FROM information_schema.tables\n" +
                "WHERE table_name = 'players'\n" +
                "LIMIT 1;";
        p = con.prepareStatement(query);
        rs = p.executeQuery();
        rs.next();
        if (rs.getInt(1) != 0) {
            return "Table already exists!";
        }
        else { 
            query = "CREATE TABLE players (\n" +
                "id INT,\n" +
                "codename VARCHAR(30)\n" +
                ");";
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
        query = "SELECT id, codename FROM players;";
        p = con.prepareStatement(query);
        rs = p.executeQuery();
        ArrayList<String> idArray = new ArrayList<String>();
        ArrayList<String> cnArray = new ArrayList<String>();

        while (rs.next()) {
            idArray.add(rs.getString(1));
            cnArray.add(rs.getString(2));
        }

        boolean idExists = false; //False if the id doesn't exist, true if it does
        System.out.println("Array size: " + idArray.size());
        if (idArray.size() >= 1) {
			for (int i = idArray.size() - 1; i > 0; i--) {
				if (idArray.get(i).equals(String.valueOf(id))) {
					idExists = true;
				}
			} 
		}
        //Return the associated codename of the player ID found in the table
        if (idExists == true) {
            String item = cnArray.get(idArray.indexOf(String.valueOf(id)));
            if (item.equals("PLACEHOLDER")) {
                return "PLACEHOLDER found";
            }
            else {
                return cnArray.get(idArray.indexOf(String.valueOf(id)));
            }
        }
        //Add ID in the case that it doesnt exist and add a placeholder name for codename to search for later
        else {
			String qFormatString = String.format("VALUES (%s, %s);", id, "\'PLACEHOLDER\'");
            query = "INSERT INTO players (id, codename)\n" +
                qFormatString;
            System.out.println(query);
            p = con.prepareStatement(query);
            p.executeUpdate();
            return "ID added";
        }
    }

    public void addCodename(String codename) throws SQLException {
        //Add codename to field labeled placeholder
        if (codename.length() != 0) {
			String qFormatString = String.format("SET codename = '%s'\n", codename);
			query = "UPDATE players\n" +
				qFormatString +
				"WHERE codename = 'PLACEHOLDER';";
			System.out.println(query);
			p = con.prepareStatement(query);
			p.executeUpdate();
        }
        else {
            System.out.println("That's a new ID please enter a codename!");
        }
    }

    public static void main(String[] args) throws SQLException {
        //Register Driver
        postgreSQL sql = new postgreSQL();
        sql.connect();
    }
}
