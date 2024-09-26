package photon;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	private ArrayList<Player> database;
	
	private Player[] redTeam = new Player[15];
	private Player[] greenTeam = new Player[15];

	private postgreSQL SQL = new postgreSQL();

	public Model(){
		database = new ArrayList<Player>();
		for (int i = 0; i < 15; i++){
			redTeam[i] = new Player();
		}
	}

	// called when a new id-codeName pairing is added, adding to both the database and team
	public void addPlayer(int id, String codeName, char team, int index){

		// first, add to the database (functionality missing)

		Player tempPlayer = new Player(id, codeName, team);
			if(team =='g')
				greenTeam[index] = tempPlayer;
			else if(team == 'r')
				redTeam[index] = tempPlayer;

		System.out.println("Success!" + tempPlayer.getName());
		this.printState();
		this.printState();
	}

	// checks for a player in the database by id, then adds them to the selected team
	public void addPlayer(int id, char team, String codeName, int index) throws SQLException{
		/*Connects to the database, will only work on the vm, if you try to run on your 
		local machine, it will not connect */
		SQL.connect();
		//Run addID method, returns ID added if codename is needed, otherwise source codename from existing id in db
		String potentialCodeName = SQL.addID(id);
		if (potentialCodeName != "ID added") {
			codeName = potentialCodeName;
		}
		SQL.addCodename(codeName);
		//SQL Disconnect
		SQL.disconnect();
		Player tempPlayer = new Player(id, codeName, team);
		if(team =='g')
				greenTeam[index] = tempPlayer;
			else if(team == 'r')
				redTeam[index] = tempPlayer;

		System.out.println("Success!" + tempPlayer.getID());
	}

	// deletes a player from the in-game teams, but not the database.
	public void deletePlayer(char team, int entryNumber){
		if(team == 'g')
			greenTeam[entryNumber] = null;
		else if(team == 'r')
			redTeam[entryNumber] = null;
	}

	public void printState(){ //testing code - remove 
		for (int i = 0; i<15; i++){
			System.out.println(redTeam[i].getID() + " " + redTeam[i].getName() + "\n");
		}
	}


}
