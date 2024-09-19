package photon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class SplashScreen {
    private JWindow window;

    public SplashScreen() {
        window = new JWindow();
        ImageIcon splashImage = new ImageIcon("logo.jpg"); // Add your splash image path
        JLabel splashLabel = new JLabel(splashImage);
        window.getContentPane().add(splashLabel, BorderLayout.CENTER);
        window.setSize(splashImage.getIconWidth(), splashImage.getIconHeight());

        // Center the splash screen on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getSize().width) / 2;
        int y = (screenSize.height - window.getSize().height) / 2;
        window.setLocation(x, y);
    }

    public void showSplash() {
        window.setVisible(true);
    }

    public void closeSplash() {
        window.setVisible(false);
        window.dispose();
    }

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        splash.showSplash();

        // Simulate loading (3 seconds)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.closeSplash();

        // Proceed to main application (Replace with actual game initialization)
        Model model = new Model();
        Controller controller = new Controller(model);
        Game game = new Game(); // Assuming a Game class exists for the main app
        View view = new View(controller, model, game);
        
        // Launch the main game window
        JFrame frame = new JFrame("Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
