package photon;
import java.util.ArrayList;

public class Model {
	private ArrayList<Player> database;
	
	private Player[] redTeam = new Player[15];
	private Player[] greenTeam = new Player[15];

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
	public void addPlayer(int id, char team, int index){
		// Necessary functionality: this needs to check for a player by id in the database, and return the player's codename if it exists
		if (false){ // will check if in the database, and add as appropriate

		}
		else{ //if the player does not exist in the database yet

		}
		
		Player tempPlayer = new Player(id, "TEMPORARY", team);
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
