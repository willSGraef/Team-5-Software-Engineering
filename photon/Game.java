package photon;

import java.awt.Toolkit;
import java.io.IOException;


public class Game extends Thread{
    private Model model;
    private Controller controller;
    private View view;
    private UDP_Server server;

    public Game(Model m) {
        this.model = m;
        this.controller = new Controller(model);
        this.view = new View(this.controller, this.model);
    }
    public void setServerOBJ(UDP_Server s) {
        this.server = s;
        model.setServerOBJ(this.server);
    }
    public Controller getController() {
        return this.controller;
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

        Model model = new Model(); // Create Model instance
        Game g = new Game(model); // Create game instance

        try {
            UDP_Server server = new UDP_Server(g.getController()); // Create and initialize UDP_Server with model
    
            // Start the UDP_Server in its own thread
            Thread serverThread = new Thread(server);
            serverThread.start();

            // Give server to game
            g.setServerOBJ(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Launch the game
        g.run();
    }
    
    
    public void run() {
        while (true) {
            view.repaint(); // This will indirectly call View.paintComponent
            Toolkit.getDefaultToolkit().sync(); // Updates screen
            model.updateActiveField();
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

