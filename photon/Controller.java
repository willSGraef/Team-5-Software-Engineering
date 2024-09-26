package photon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

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
	
	public void mousePressed(MouseEvent e) {	}
	public void mouseDragged(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	
	
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) {
			case KeyEvent.VK_INSERT:
				model.printState();
				break;
		
			default:
				break;
		}
	}
	public void keyReleased(KeyEvent e){	}
	
	
	public void mouseMoved(MouseEvent e) {	  }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	public void keyTyped(KeyEvent e) {	  }
	public void actionPerformed(ActionEvent e) {    }

    
}
