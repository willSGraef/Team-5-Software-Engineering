package photon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;


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
	

	public void keyPressed(KeyEvent e) 
	{
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
			case KeyEvent.VK_INSERT:
				try {
					model.addPlayer();
				} catch (Exception e1) {
					System.out.println("SQL ERROR");
				}
				break;
			case KeyEvent.VK_RIGHT:
				model.shiftActiveFieldForward();
				break;
			case KeyEvent.VK_LEFT:
				model.shiftActiveFieldBackward();
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent e){	}
	public void keyReleased(KeyEvent e){	}
	public void actionPerformed(ActionEvent e) {    }
    
}
