package photon;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View extends JFrame{

	private Model model;

	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.addKeyListener(c);
		this.setFocusTraversalKeysEnabled(false); //this lets us change highlighted entry with the tab key
		this.setTitle("Photon");
        this.setSize(614, 638);
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		Color redFieldColor = new Color(235, 110, 110);
		Color greenFieldColor = new Color(127, 235, 110);
		

		// this massive block iterates over h to represent columns, and i to represent rows. 
		// the switch statement controls which column is being generated. 
		for (int h = 0; h < 6; h++){
			for (int i = 0; i < 15; i++){
				switch (h) {
					case 0: // creates red team id fields
						JTextField tempEntryIDRed = new JTextField();  
						tempEntryIDRed.setBounds(100, 20*i+50, 50, 20); 
						tempEntryIDRed.setBackground(redFieldColor);
						
						tempEntryIDRed.addKeyListener(c);
						
						model.addTextFieldRed(tempEntryIDRed, i, h);
						this.add(tempEntryIDRed);
						break;
					case 1: // creates red-team name fields
						JTextField tempEntryNameRed = new JTextField();  
						tempEntryNameRed.setBounds(150, 20*i+50, 100, 20); 
						tempEntryNameRed.setBackground(redFieldColor);

						tempEntryNameRed.addKeyListener(c);

						model.addTextFieldRed(tempEntryNameRed, i, h);
						this.add(tempEntryNameRed);
						break;
					case 2: // creates red-team equipment ID fields
						JTextField tempEntryEquipmentIDRed = new JTextField();  
						tempEntryEquipmentIDRed.setBounds(250, 20*i+50, 50, 20); 
						tempEntryEquipmentIDRed.setBackground(redFieldColor);

						tempEntryEquipmentIDRed.addKeyListener(c);

						model.addTextFieldRed(tempEntryEquipmentIDRed, i, h);
						this.add(tempEntryEquipmentIDRed);
						break;
					case 3: // creates green team id fields
						JTextField tempEntryIDGreen = new JTextField();  
						tempEntryIDGreen.setBounds(350, 20*i+50, 50, 20); 
						tempEntryIDGreen.setBackground(greenFieldColor);

						tempEntryIDGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryIDGreen, i, h-3);
						this.add(tempEntryIDGreen);
						break;
					case 4: // creates green-team name fields
						JTextField tempEntryNameGreen = new JTextField();  
						tempEntryNameGreen.setBounds(400, 20*i+50, 100, 20);
						tempEntryNameGreen.setBackground(greenFieldColor);

						tempEntryNameGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryNameGreen, i, h-3);
						this.add(tempEntryNameGreen);
						break;
					case 5: // creates Green-team equipment ID fields
						JTextField tempEntryEquipmentIDGreen = new JTextField();  
						tempEntryEquipmentIDGreen.setBounds(500, 20*i+50, 50, 20); 
						tempEntryEquipmentIDGreen.setBackground(greenFieldColor);

						tempEntryEquipmentIDGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryEquipmentIDGreen, i, h-3);
						this.add(tempEntryEquipmentIDGreen);
					default:
						break;
				}
			}
		}
		
    	this.setLayout(null);  
    	
	}

	public void startGame() {
		// Hide the current window
		this.setVisible(false);
		
		// Create a new game window
		JFrame gameFrame = new JFrame("Game");
		gameFrame.setSize(800, 600); // Set the size of the game window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().setBackground(Color.BLACK);
		
		// Add your game components here, e.g., a game canvas or panel
		JPanel gamePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Add your game drawing code here
			}
		};
		
		gameFrame.add(gamePanel);
		gameFrame.setVisible(true); // Show the game window
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		this.setBackground(Color.black);
		this.requestFocus();
	}
}
