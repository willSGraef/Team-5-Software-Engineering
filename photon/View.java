package photon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.chrono.JapaneseEra;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class View extends JFrame{
	Color redFieldColor = new Color(235, 110, 110);
	Color greenFieldColor = new Color(127, 235, 110);

	private Model model;
	private JTextArea actionFeed;

	// Define score labels as instance variables
	private JLabel redTotalScore;
	private JLabel greenTotalScore;
	private JTable redTable;
	private JTable greenTable;

	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.addKeyListener(c);
		this.setFocusTraversalKeysEnabled(false); 
		this.setTitle("Photon");
        this.setSize(815, 840);
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
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
		PlayerAction pa = new PlayerAction(model);
		
		int frameWidth = 800;
		int frameHeight = 600;

		String[] columnNames = {"PLAYER: ", "SCORE: "};

		// Create a new game window
		JFrame gameFrame = new JFrame("Game");
		// Clear gameFrame layout for absolute positioning
		gameFrame.setLayout(new GridBagLayout());
		gameFrame.setSize(frameWidth, frameHeight); // Set the size of the game window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().setBackground(Color.BLACK);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.BOTH;
		
		// Init team roster panels
		JPanel redRosterPanel = new JPanel(new GridLayout(3,1));
		// Theme components
		redRosterPanel.setBackground(Color.BLACK);
		redRosterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		// Apply constraints
		constraint.weighty = 0.3;
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 0;
		// Add components
		// RED ROSTER
		// Init and add red team label
		JLabel redTeamLabel = new JLabel("RED TEAM", SwingConstants.CENTER);
		redTeamLabel.setForeground(redFieldColor);
		redRosterPanel.add(redTeamLabel);

		// Init and add red team list of players
		DefaultTableModel redData = new DefaultTableModel(columnNames, 0);
		HashMap<Integer, Player> redMap = model.getRedTeam();

		for(Player p : redMap.values()) {
			Vector<String> row = new Vector<String>();
			row.add(p.getName());
			row.add((String.valueOf(p.getScore())));
			redData.addRow(row);
		}

		redTable = new JTable(redData);
		redTable.setBackground(Color.BLACK);
		redTable.setForeground(redFieldColor);
		redRosterPanel.add(redTable);

		// Init and add red team total score label
		redTotalScore = new JLabel(String.valueOf(pa.getRedTeamScore()), SwingConstants.RIGHT);
		redTotalScore.setForeground(greenFieldColor);
		redRosterPanel.add(redTotalScore);

		gameFrame.add(redRosterPanel, constraint);

		// GREEN ROSTER

		JPanel greenRosterPanel = new JPanel(new GridLayout(3,1));
		// Theme components
		greenRosterPanel.setBackground(Color.BLACK);
		greenRosterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		// Apply constraints
		constraint.weighty = 0.3;
		constraint.gridx = 1;
		constraint.gridy = 0;

		// Add components
		// Init and add green team label
		JLabel greenTeamLabel = new JLabel("GREEN TEAM", SwingConstants.CENTER);
		greenTeamLabel.setForeground(greenFieldColor);
		greenRosterPanel.add(greenTeamLabel);

		// Init and add green team list of players and scores
		DefaultTableModel greenData = new DefaultTableModel(columnNames, 0);
		HashMap<Integer, Player> greenMap = model.getGreenTeam();

		for(Player p : greenMap.values()) {
			Vector<String> row = new Vector<String>();
			row.add(p.getName());
			row.add((String.valueOf(p.getScore())));
			greenData.addRow(row);
		}

		greenTable = new JTable(greenData);
		greenTable.setBackground(Color.BLACK);
		greenTable.setForeground(greenFieldColor);
		greenRosterPanel.add(greenTable);

		// Init and add green team total score label
		greenTotalScore = new JLabel(String.valueOf(pa.getGreenTeamScore()), SwingConstants.RIGHT);
		greenTotalScore.setForeground(greenFieldColor);
		greenRosterPanel.add(greenTotalScore);

		gameFrame.add(greenRosterPanel, constraint);


		// Init scorePanel
		JPanel scorePanel = new JPanel(new GridLayout(3, 1));
		scorePanel.setBackground(Color.DARK_GRAY);
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		constraint.weighty = 0.4;
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.gridwidth = 3;
		// Add panel title
		JLabel scorePanelTitle = new JLabel("CURRENT GAME ACTION", SwingConstants.CENTER);
		scorePanelTitle.setForeground(Color.CYAN);
		scorePanel.add(scorePanelTitle);

		gameFrame.add(scorePanel, constraint);

		// Init timerPanel
		JPanel timerPanel = new JPanel(new GridLayout());
		timerPanel.setBackground(Color.BLACK);
		timerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		constraint.weighty = 0.1;
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.gridwidth = 3;
		// Add base timer
		JLabel timerLabel = new JLabel("TIME: 0:00", SwingConstants.CENTER);
		timerLabel.setForeground(Color.CYAN);
		timerPanel.add(timerLabel);

		gameFrame.add(timerPanel, constraint);
		
		gameFrame.setVisible(true); // Show the game window
	}

	// Method to update the action feed
    public void updateActionFeed(String actionMessage) {
        actionFeed.append(actionMessage + "\n");

        // Optional: limit to the latest 10 entries
        if (actionFeed.getLineCount() > 10) {
            try {
                int end = actionFeed.getLineEndOffset(0);
                actionFeed.replaceRange("", 0, end);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

	public void updateScores() {
		// Get updated scores for each team
		int redTeamScore = model.getTeamScore('r');
		int greenTeamScore = model.getTeamScore('g');
		
		// Update the team score labels
		redTotalScore.setText("Red Team: " + redTeamScore);
		greenTotalScore.setText("Green Team: " + greenTeamScore);
		
		// Update individual scores in the red team table
		DefaultTableModel redTableModel = (DefaultTableModel) redTable.getModel();
		redTableModel.setRowCount(0); // Clear existing rows
		for (Player p : model.getRedTeam().values()) {
			Vector<String> row = new Vector<>();
			row.add(p.getName());
			row.add(String.valueOf(p.getScore()));
			redTableModel.addRow(row);
		}
		
		// Update individual scores in the green team table
		DefaultTableModel greenTableModel = (DefaultTableModel) greenTable.getModel();
		greenTableModel.setRowCount(0); // Clear existing rows
		for (Player p : model.getGreenTeam().values()) {
			Vector<String> row = new Vector<>();
			row.add(p.getName());
			row.add(String.valueOf(p.getScore()));
			greenTableModel.addRow(row);
		}
	
		//highlight the team with the highest score
		if (redTeamScore > greenTeamScore) {
			redTotalScore.setForeground(Color.YELLOW); // Highlight red team score
			greenTotalScore.setForeground(Color.WHITE);
		} else if (greenTeamScore > redTeamScore) {
			greenTotalScore.setForeground(Color.YELLOW); // Highlight green team score
			redTotalScore.setForeground(Color.WHITE);
		} else {
			redTotalScore.setForeground(Color.WHITE);
			greenTotalScore.setForeground(Color.WHITE);
		}
	}
	

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		this.setBackground(Color.black);
		this.requestFocus();
	}
}
