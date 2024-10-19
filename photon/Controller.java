package photon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements ActionListener, KeyListener{

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
			case KeyEvent.VK_INSERT:
				// Existing code...
				break;
			case KeyEvent.VK_RIGHT:
				// Existing code...
				break;
			case KeyEvent.VK_LEFT:
				// Existing code...
				break;
			case KeyEvent.VK_F5: // Check for F5 key
				view.startGame(); // Call the method to start the game
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent e){	}
	public void keyReleased(KeyEvent e){	}
	public void actionPerformed(ActionEvent e) {    }
    
}
