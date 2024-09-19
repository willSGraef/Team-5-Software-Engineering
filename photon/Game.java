package photon;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Game extends JFrame {
    private Model model = new Model();
    private Controller controller = new Controller(model);
    private View view = new View(controller, model, this);
    
    public Game() {
        this.setTitle("Photon UI Demo");
        this.setSize(614, 638);
        this.setFocusable(true);
        this.getContentPane().add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        view.addMouseMotionListener(controller);
        view.addMouseListener(controller);
        this.addKeyListener(controller);
    }

    public static void main(String[] args) {
        // Show splash screen before launching the game
        SplashScreen splash = new SplashScreen();
        splash.showSplash();

        // Simulate loading process (e.g., 3 seconds)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the splash screen
        splash.closeSplash();

        // Launch the game
        Game g = new Game();
        g.run();
    }
    
    public void run() {
        while (true) {
            view.repaint(); // This will indirectly call View.paintComponent
            Toolkit.getDefaultToolkit().sync(); // Updates screen

            // Go to sleep for 40 milliseconds (controls game speed)
            try {
                Thread.sleep(40);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}

