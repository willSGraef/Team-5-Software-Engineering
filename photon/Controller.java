package photon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class Controller implements ActionListener, KeyListener, CountDownListener {

	private View view;
	private Model model;
	
	public Controller(Model m)
	{
		model = m;
	}
	
	void setView(View v)
	{
		view = v;
	}

	public void showSplashScreen() {
        SplashScreen splash = new SplashScreen();
        splash.showSplash();

        try {
            // Simulate a loading delay (e.g., 3 seconds)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.closeSplash();
    }
	

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
			case KeyEvent.VK_F3: 
				try {
					model.addPlayer();
				} catch (Exception e1) {
					System.out.println("SQL ERROR");
				}
				break;
			case KeyEvent.VK_F2: 
				model.shiftActiveFieldForward();
				break;
			case KeyEvent.VK_F1: 
				model.shiftActiveFieldBackward();
				break;
			case KeyEvent.VK_F12:
				model.clearPlayers();
				break;
			case KeyEvent.VK_F5: // Check for F5 key
				// Start the countdown and wait for the callback to trigger startGame
				//Clear previous view
				this.view.dispose();
                Game gameInstance = new Game(model);
                GameCountDown countdown = new GameCountDown(gameInstance, this); // Pass 'this' as the listener
                countdown.showCountdown();
				model.playTrack();
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent e){	}
	public void keyReleased(KeyEvent e){	}
	public void actionPerformed(ActionEvent e) {    }

	@Override
    public void onCountdownFinished() {
        // This method will be called when the countdown is finished
        System.out.println("Countdown finished, starting the game...");

		try {
			// Create an instance of the UDP_Client and send code 202
			UDP_Server server = model.getServerOBJ();
			server.UDP_SendData("202"); // Send code 202 to the server
		} catch (IOException e) {
			e.printStackTrace();
		}
        view.startGame(); // Now you can start the game after the countdown is complete
    }

	public void addActionToFeed(String actionMessage) {
        view.updateActionFeed(actionMessage);
    }

	public void handlePlayerTag(int taggerId, int taggedId) {
		// Retrieve the players from Model
		Player tagger = model.getPlayerByEquipmentId(taggerId);
		Player tagged = model.getPlayerByEquipmentId(taggedId);
	
		// Check if both players are valid
		if (tagger != null && tagged != null) {
			// Create the action message
			String actionMessage = tagger.getName() + " tagged " + tagged.getName();
			
			// Update the action feed in the view
			addActionToFeed(actionMessage);
	
			// Adjust scores based on whether it's a team or opponent tag
			if (tagger.getTeam() == tagged.getTeam()) {
				tagger.updateScore(-10); // Penalty for tagging own team
			} else {
				tagger.updateScore(10);  // Award points for tagging opponent
			}
	
			// Refresh the score display in the view
			view.updateScores();
		}
	}

	public void handleBaseTag(int taggerId, int code) {
		// Retrieve the player from the model
		Player tagger = model.getPlayerByEquipmentId(taggerId);
	
		// Check if the player is valid
		if (tagger != null) {
			if (code == 53 && tagger.getTeam() == 'g') {
				// Red base scored, and tagger is on the Green team
				tagger.updateScore(100);
				if (!tagger.getName().startsWith("B")) {
					tagger.setName("B" + tagger.getName());
				}
				addActionToFeed(tagger.getName() + " scored on the Red base!");
			} 
			else if (code == 43 && tagger.getTeam() == 'r') {
				// Green base scored, and tagger is on the Red team
				tagger.updateScore(100);
				if (!tagger.getName().startsWith("B")) {
					tagger.setName("B" + tagger.getName());
				}
				addActionToFeed(tagger.getName() + " scored on the Green base!");
			}
			// Refresh the score display in the view
			view.updateScores();
		}
	}	
}
