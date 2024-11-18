package photon;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JTextField;





public class Model {

	private JTextField[] redFields = new JTextField[45];
	private JTextField[] greenFields = new JTextField[45];

	private HashMap<Integer, Player> redTeam = new HashMap<Integer, Player>();
	private HashMap<Integer, Player> greenTeam = new HashMap<Integer, Player>();

	private int activeField = 0; // 0-29, 

	private UDP_Server server;

	private AudioPlayer audio;
	
	private postgreSQL SQL = new postgreSQL();

	public Model(){
		
	}


	// checks for a player in the database by id, then adds them to the selected team
	// if a player is entered with no codename attached, and they do not exist in the database, the functionality is unclear
	public void addPlayer() throws SQLException{
		
		int currentRowHead;
		int id;
		String codeName;
		char team;
		int equipmentID;

		if(activeField%2 == 0) // red case
		{
			team = 'r';
			currentRowHead = (int)(activeField * 1.5);
			try {
				id = Integer.parseInt(redFields[currentRowHead].getText());
			} catch (Exception e) {
				System.out.println("insert a valid Player ID");
				id = 0;
			}
			codeName = redFields[currentRowHead+1].getText();
			try {
				equipmentID = Integer.parseInt(redFields[currentRowHead+2].getText());
			} catch (Exception e) {
				System.out.println("insert a valid Equipment ID");
				equipmentID = 0;
			}
		}
		else //green case
		{
			team = 'g';
			currentRowHead = (int)((activeField-1) * 1.5);
			try {
				id = Integer.parseInt(greenFields[currentRowHead].getText());
			} catch (Exception e) {
				System.out.println("insert a valid ID");
				id = 0;
			}
			codeName = greenFields[currentRowHead+1].getText();
			try {
				equipmentID = Integer.parseInt(greenFields[currentRowHead+2].getText());
			} catch (Exception e) {
				System.out.println("insert a valid Equipment ID");
				equipmentID = 0;
			}
		}

		try {
			/*Connects to the database, will only work on the vm, if you try to run on your 
			local machine, it will not connect */
			SQL.connect();
			System.out.println("Original codename: " + codeName);
			//Run addID method, returns ID added if codename is needed, otherwise source codename from existing id in db
			String potentialCodeName = SQL.addID(id);
			if (potentialCodeName != "ID added" && potentialCodeName != "PLACEHOLDER found") {
				SQL.addCodename(potentialCodeName);
				codeName = potentialCodeName;
			}
			else {
				SQL.addCodename(codeName);
			}
			if(team =='g'){
				greenFields[currentRowHead+1].setText(codeName);
			}
			else if(team == 'r'){
				redFields[currentRowHead+1].setText(codeName);
			}
			//SQL Disconnect
			SQL.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Player tempPlayer = new Player(id, codeName, team, equipmentID);
		if(team =='g'){
			greenTeam.put(equipmentID, tempPlayer);
			System.out.println("Added to green team");
		}
		else if(team == 'r'){
			try {
				redTeam.put(equipmentID, tempPlayer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Added to red team");
		}

		System.out.println("Success!" + tempPlayer.getID());

		// Transmit the equipment ID to the server
    	sendEquipmentID(equipmentID);
	}

	//handle UDP transmission
	public void sendEquipmentID(int equipmentID) {
		try {
			// Send the equipment ID to the server
			UDP_Client client = new UDP_Client();
			client.UDP_SendData(String.valueOf(equipmentID));
			// Client closes automatically
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player getPlayerByEquipmentId(int equipmentID) {
		// Look in both teams
		if (redTeam.containsKey(equipmentID)) {
			return redTeam.get(equipmentID);
		} else if (greenTeam.containsKey(equipmentID)) {
			return greenTeam.get(equipmentID);
		}
		return null; // Return null if player not found
	}

	public char getPlayerTeamById(int equipmentID) {
		if (redTeam.containsKey(equipmentID)) {
			return 'r';
		} else if (greenTeam.containsKey(equipmentID)) {
			return 'g';
		} 
		return 'x';
	}

	public int getTeamScore(char team) {
		int totalScore = 0;
		HashMap<Integer, Player> teamMap = (team == 'r') ? redTeam : greenTeam;
		
		for (Player p : teamMap.values()) {
			totalScore += p.getScore();
		}
		return totalScore;
	}
	
	public void setServerOBJ(UDP_Server s) {
		this.server = s;
	}
	public UDP_Server getServerOBJ() {
		return this.server;
	}

	// deletes a player from the in-game teams, but not the database.
	public void deletePlayer(char team, int eID){
		if(team == 'g')
			greenTeam.remove(eID);
		else if(team == 'r')
			redTeam.remove(eID);
	}

	public void clearPlayers()
	{
		if(redTeam != null)
		{
			redTeam.clear();
		}
		
		if(greenTeam != null)
		{
			greenTeam.clear();
		}
		for (int i = 0; i < 45; i++)
		{
			redFields[i].setText("");
			greenFields[i].setText("");
		}
		activeField = 0;
		updateActiveField();
	}

	// add EntryField Method
	public void addTextFieldRed(JTextField field, int row, int column)
	{
		redFields[(row*3)+column] = field;
	}

	public void addTextFieldGreen(JTextField field, int row, int column)
	{
		greenFields[(row*3)+column] = field;
	}

	// update active box visually by updating the background color to be more intense
	public void updateActiveField()
	{
		Color activeRedColor = new Color(235, 69, 69);
		Color activeGreenColor = new Color(69, 237, 69);
		Color normalRedColor = new Color(235, 110, 110);
		Color normalGreenColor = new Color(127, 235, 110);

		for (int i = 0; i < 45; i++){
			redFields[i].setBackground(normalRedColor);
			greenFields[i].setBackground(normalGreenColor);
		}

		
		if(activeField%2 == 0) // redfields case
		{
			int currentRowHead = (int)(activeField * 1.5);
			redFields[currentRowHead].setBackground(activeRedColor);
			redFields[currentRowHead+1].setBackground(activeRedColor);
			redFields[currentRowHead+2].setBackground(activeRedColor);


		}
		else //green case
		{
			int currentRowHead = (int)((activeField-1) * 1.5);
			greenFields[currentRowHead].setBackground(activeGreenColor);
			greenFields[currentRowHead+1].setBackground(activeGreenColor);
			greenFields[currentRowHead+2].setBackground(activeGreenColor);

		}
	}

	public void shiftActiveFieldForward()
	{
		if (activeField < 29)
		{
			activeField++;
		}
	}
	public void shiftActiveFieldBackward()
	{
		if (activeField > 0)
		{
			activeField--;
		}
	}
	public HashMap<Integer, Player> getGreenTeam() {
		return greenTeam;
	}

	public HashMap<Integer, Player> getRedTeam() {
		return redTeam;
	}
		// Method to award points to a team and add "B" symbol
	public void awardPointsToTeam(char team, int points, String symbol) {
		HashMap<Integer, Player> teamMap = (team == 'r') ? redTeam : greenTeam;
	
		for (Player player : teamMap.values()) {
			player.updateScore(points); // Update player score
	
			// Add "B" to the beginning of the codename if not present
			if (!player.getName().startsWith(symbol)) {
				player.setName(symbol + player.getName());
			}
		}
	}


	public void playTrack(){
		this.audio = new AudioPlayer();
		audio.run();
	}

	public void stopTrack() {
		this.audio.stop();
	}
}
