package photon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class View extends JFrame{

	private Model model;

	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.addKeyListener(c);
		this.setFocusTraversalKeysEnabled(false); //this lets us change highlighted entry with the tab key
		this.setTitle("Photon");
        this.setSize(815, 840);
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		Color redFieldColor = new Color(235, 110, 110);
		Color greenFieldColor = new Color(127, 235, 110);
		this.setLayout(null);  


		// Team titles

		JPanel redTeamTitle = new JPanel();
		redTeamTitle.setBounds(100,25,200,35);
		redTeamTitle.setBackground(Color.black);
		JLabel redTeamTitleText = new JLabel("Red Team");
		redTeamTitleText.setFont(new Font("Serif", Font.BOLD, 20));
		redTeamTitleText.setForeground(Color.red);
		this.add(redTeamTitle);
		redTeamTitle.add(redTeamTitleText);

		JPanel greenTeamTitle = new JPanel();
		greenTeamTitle.setBounds(500,25,200,35);
		greenTeamTitle.setBackground(Color.black);
		JLabel greenTeamTitleText = new JLabel("Green Team");
		greenTeamTitleText.setFont(new Font("Serif", Font.BOLD, 20));
		greenTeamTitleText.setForeground(Color.green);
		this.add(greenTeamTitle);
		greenTeamTitle.add(greenTeamTitleText);

		// Guides for user inputs
		JPanel guides = new JPanel();
		guides.setLayout(null);
		guides.setBounds(0, 650, 800, 150);
		guides.setBackground(Color.BLACK);
		this.add(guides);

		Border whiteLine = BorderFactory.createLineBorder(Color.white);

		JLabel navLeftGuide = new JLabel("Navigate Left: F1 Key");
		navLeftGuide.setBounds(50, 0, 150,25);
		navLeftGuide.setBorder(whiteLine);
		navLeftGuide.setForeground(Color.green);
		guides.add(navLeftGuide);
		
		JLabel navRighttGuide = new JLabel("Navigate Right: F2 Key");
		navRighttGuide.setBounds(225, 0, 150,25);
		navRighttGuide.setBorder(whiteLine);
		navRighttGuide.setForeground(Color.green);
		guides.add(navRighttGuide);

		JLabel addPlayerGuide = new JLabel("Enter Selected Player: F3 Key");
		addPlayerGuide.setBounds(50, 50, 200,25);
		addPlayerGuide.setBorder(whiteLine);
		addPlayerGuide.setForeground(Color.green);
		guides.add(addPlayerGuide);

		JLabel startGameGuide = new JLabel("Start Game: F5 Key");
		startGameGuide.setBounds(625, 0, 125,25);
		startGameGuide.setBorder(whiteLine);
		startGameGuide.setForeground(Color.green);
		guides.add(startGameGuide);

		JLabel clearPlayersGuide = new JLabel("Clear All Players: F12 Key");
		clearPlayersGuide.setBounds(50, 100, 200,25);
		clearPlayersGuide.setBorder(whiteLine);
		clearPlayersGuide.setForeground(Color.green);
		guides.add(clearPlayersGuide);

		

		// Red team column labels

		Border redLine = BorderFactory.createLineBorder(Color.red);
		JPanel redTeamLabels = new JPanel();
		redTeamLabels.setBackground(Color.black);
		redTeamLabels.setLayout(null);
		redTeamLabels.setBounds(50, 75, 300, 25);

		JLabel redIdLabel = new JLabel("Player ID:");
		redIdLabel.setBounds(0,0,75, 25);
		redIdLabel.setForeground(Color.white);
		redIdLabel.setBorder(redLine);
		redTeamLabels.add(redIdLabel);

		JLabel redCodenameLabel = new JLabel("Codename:");
		redCodenameLabel.setBounds(75,0,150, 25);
		redCodenameLabel.setForeground(Color.white);
		redCodenameLabel.setBorder(redLine);
		redTeamLabels.add(redCodenameLabel);

		JLabel redEquipmentIdLabel = new JLabel("Kit ID:");
		redEquipmentIdLabel.setBounds(225,0,75, 25);
		redEquipmentIdLabel.setForeground(Color.white);
		redEquipmentIdLabel.setBorder(redLine);
		redTeamLabels.add(redEquipmentIdLabel);

		this.add(redTeamLabels);

		// Green team column labels

		

		Border greenLine = BorderFactory.createLineBorder(Color.green);
		JPanel greenTeamLabels = new JPanel();
		greenTeamLabels.setLayout(null);
		greenTeamLabels.setBackground(Color.black);
		greenTeamLabels.setBounds(450, 75, 300, 25);

		JLabel greenIdLabel = new JLabel("Player ID:");
		greenIdLabel.setBounds(0,0,75, 25);
		greenIdLabel.setForeground(Color.white);
		greenIdLabel.setBorder(greenLine);
		greenTeamLabels.add(greenIdLabel);

		JLabel greenCodenameLabel = new JLabel("Codename:");
		greenCodenameLabel.setBounds(75,0,150, 25);
		greenCodenameLabel.setForeground(Color.white);
		greenCodenameLabel.setBorder(greenLine);
		greenTeamLabels.add(greenCodenameLabel);

		JLabel greenEquipmentIdLabel = new JLabel("Kit ID:");
		greenEquipmentIdLabel.setBounds(225,0,75, 25);
		greenEquipmentIdLabel.setForeground(Color.white);
		greenEquipmentIdLabel.setBorder(greenLine);
		greenTeamLabels.add(greenEquipmentIdLabel);
		
		this.add(greenTeamLabels);


		/*
		    this massive block iterates over h to represent columns, and i to represent rows. 
		    the switch statement controls which column is being generated.

			The player entry screen is organized as 2 large blocks scaled 300x525 with 100
			for a buffer in-between. Each row is 25 units tall, for 15 rows.
		*/ 
		for (int h = 0; h < 6; h++){
			for (int i = 0; i < 15; i++){
				switch (h) {
					case 0: // creates red team id fields
						JTextField tempEntryIDRed = new JTextField();  
						tempEntryIDRed.setBounds(50, 35*i+100, 75, 35); 
						tempEntryIDRed.setBackground(redFieldColor);
						
						tempEntryIDRed.addKeyListener(c);
						
						model.addTextFieldRed(tempEntryIDRed, i, h);
						this.add(tempEntryIDRed);
						break;
					case 1: // creates red-team name fields
						JTextField tempEntryNameRed = new JTextField();  
						tempEntryNameRed.setBounds(125, 35*i+100, 150, 35); 
						tempEntryNameRed.setBackground(redFieldColor);

						tempEntryNameRed.addKeyListener(c);

						model.addTextFieldRed(tempEntryNameRed, i, h);
						this.add(tempEntryNameRed);
						break;
					case 2: // creates red-team equipment ID fields
						JTextField tempEntryEquipmentIDRed = new JTextField();  
						tempEntryEquipmentIDRed.setBounds(275, 35*i+100, 75, 35); 
						tempEntryEquipmentIDRed.setBackground(redFieldColor);

						tempEntryEquipmentIDRed.addKeyListener(c);

						model.addTextFieldRed(tempEntryEquipmentIDRed, i, h);
						this.add(tempEntryEquipmentIDRed);
						break;
					case 3: // creates green team id fields
						JTextField tempEntryIDGreen = new JTextField();  
						tempEntryIDGreen.setBounds(450, 35*i+100, 75, 35); 
						tempEntryIDGreen.setBackground(greenFieldColor);

						tempEntryIDGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryIDGreen, i, h-3);
						this.add(tempEntryIDGreen);
						break;
					case 4: // creates green-team name fields
						JTextField tempEntryNameGreen = new JTextField();  
						tempEntryNameGreen.setBounds(525, 35*i+100, 150, 35);
						tempEntryNameGreen.setBackground(greenFieldColor);

						tempEntryNameGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryNameGreen, i, h-3);
						this.add(tempEntryNameGreen);
						break;
					case 5: // creates Green-team equipment ID fields
						JTextField tempEntryEquipmentIDGreen = new JTextField();  
						tempEntryEquipmentIDGreen.setBounds(675, 35*i+100, 75, 35); 
						tempEntryEquipmentIDGreen.setBackground(greenFieldColor);

						tempEntryEquipmentIDGreen.addKeyListener(c);

						model.addTextFieldGreen(tempEntryEquipmentIDGreen, i, h-3);
						this.add(tempEntryEquipmentIDGreen);
					default:
						break;
				}
			}
		}
		
    	
    	
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
