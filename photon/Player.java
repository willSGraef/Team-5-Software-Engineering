package photon;

public class Player {

	private int id;
	private String name;
	private char team;
	private int score;
	private boolean baseObjective; // Has player hit enemy base 3 times
	private int equipmentID;

	public Player(int entryID, String entryName, char entryTeam) 
	{
		this.id = entryID;
		this.name = entryName;
		this.team = entryTeam;
		this.score = 0;

		this.baseObjective = false;
		this.equipmentID = entryID; // !!! THIS IS TEMPORARY AND INCORRECT FOR FINAL DESIGN
	}

	public void updateScore(int delta) {
		this.score += delta;
	}

	//Getters & Setters----------------------------------------------
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getScore() {
		return this.score;
	}
	public char getTeam() {
		return this.team;
	}
	public boolean getBaseObjective() {
		return this.baseObjective;
	}
	public int getEquipmentID() {
		return this.equipmentID;
	}

	public void setID(int ID) {
		this.id = ID;
		// Do we need to check anything about ID?
	}
	public void setName(String n) {
		this.name = n;
	}
	public void setScore(int s) {
		this.score = s;
	}
	public void setTeam(char t) {
		this.team = t;
	}
	public void setBaseObjective(boolean base) {
		this.baseObjective = base;
	}
	public void setEquipmentID(int eID) {
		this.equipmentID = eID;
	}
	//---------------------------------------------------------------

}
