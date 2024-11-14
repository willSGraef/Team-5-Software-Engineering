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
			// Initialize the UDP client
			UDP_Client udpClient = new UDP_Client();
			
			// Send the equipment ID to the server
			udpClient.UDP_SendData(String.valueOf(equipmentID)); // Convert the equipment ID to a string
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
