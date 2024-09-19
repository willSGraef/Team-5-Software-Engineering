package photon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

public class View extends JPanel{

	private Model model;
	private Game game;
    
	public View(Controller c, Model m, Game g)
	{
		model = m;
		c.setView(this);
		
		//team 1
		for (int i = 0; i < 20; i++)
		{
			JTextField tempEntry = new JTextField();  
			tempEntry.setBounds(100, 20*i+50, 150, 20); 
	    	g.add(tempEntry);  
		}
		// team 2
		for (int i = 0; i < 20; i++)
		{
			JTextField tempEntry = new JTextField();  
			tempEntry.setBounds(300, 20*i+50, 150, 20); 
	    	g.add(tempEntry);  
		}
		
    	//g.setSize(300,300);  
    	g.setLayout(null);  
    	//team1Entries.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(35, 5, 48));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
