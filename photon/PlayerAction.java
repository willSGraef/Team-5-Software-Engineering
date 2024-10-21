package photon;

import java.util.HashMap;

public class PlayerAction {
	
	private Model model;

	PlayerAction(Model M) {
		model = M;
	}

	private int redTeamScore, greenTeamScore;

	public void updateTeamScores() {
		HashMap<Integer, Player> greenTeam = model.getGreenTeam();
		HashMap<Integer, Player> redTeam = model.getRedTeam();

		greenTeamScore = 0;
		for(Player p : greenTeam.values()) {
			greenTeamScore += p.getScore();
		}
		redTeamScore = 0;
		for(Player p : redTeam.values()) {
			redTeamScore += p.getScore();
		}
	}

	//Mutators

	public int getGreenTeamScore() {
		return this.greenTeamScore;
	}

	public int getRedTeamScore() {
		return this.redTeamScore;
	}

	
}
