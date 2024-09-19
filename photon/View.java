package photon;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JPanel {
    private Model model;
    private Game game;

    public View(Controller c, Model m, Game g) {
        model = m;
        c.setView(this);

        // Example: Create JTextField components to represent teams
        for (int i = 0; i < 20; i++) {
            JTextField tempEntry = new JTextField();
            tempEntry.setBounds(100, 20 * i + 50, 150, 20);
            g.add(tempEntry);  
        }

        for (int i = 0; i < 20; i++) {
            JTextField tempEntry = new JTextField();
            tempEntry.setBounds(300, 20 * i + 50, 150, 20);
            g.add(tempEntry);  
        }

        g.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(35, 5, 48)); // Example background color
        // Draw other UI elements as needed
    }
}
