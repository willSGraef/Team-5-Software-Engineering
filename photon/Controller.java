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
                Game gameInstance = new Game();
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
			UDP_Client udpClient = new UDP_Client();
			udpClient.UDP_SendData("202"); // Send code 202 to the server
		} catch (IOException e) {
			e.printStackTrace();
		}
        view.startGame(); // Now you can start the game after the countdown is complete
    }
    
}
