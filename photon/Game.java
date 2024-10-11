package photon;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramSocket;


public class Game{
    private Model model = new Model();
    private Controller controller = new Controller(model);
    private View view = new View(controller, model);
    
    public Game() {
        
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

        // Start the UDP server in a separate thread so it can run in the background
        new Thread(() -> {
            try {
                UDP_Server udpServer = new UDP_Server();
                System.out.println("Server is running and ready to receive data...");
                udpServer.UDP_ReceiveData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Launch the game
        Game g = new Game();
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

