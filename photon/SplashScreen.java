package photon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class SplashScreen {
    private JWindow window;

    public SplashScreen() {
        window = new JWindow();
        ImageIcon splashImage = new ImageIcon("logo.jpg"); // Add your splash image path
        Image img = splashImage.getImage();
        Image resizedImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH); // Resize image
        JLabel splashLabel = new JLabel(new ImageIcon(resizedImg));
        window.getContentPane().add(splashLabel, BorderLayout.CENTER);
        window.setSize(400, 300); // Set the window size to match the resized image

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
}

