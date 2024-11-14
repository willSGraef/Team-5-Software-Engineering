package photon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseEra;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;
import java.util.TimerTask;
import java.util.Vector;


public class View extends JFrame{
	Color redFieldColor = new Color(235, 110, 110);
	Color greenFieldColor = new Color(127, 235, 110);

	private Model model;
	private Timer timer;

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

		JTable redTable = new JTable(redData);
		redTable.setBackground(Color.BLACK);
		redTable.setForeground(redFieldColor);
		redRosterPanel.add(redTable);

		// Init and add red team total score label
		JLabel redTotalScore = new JLabel(String.valueOf(pa.getRedTeamScore()), SwingConstants.RIGHT);
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

		JTable greenTable = new JTable(greenData);
		greenTable.setBackground(Color.BLACK);
		greenTable.setForeground(greenFieldColor);
		greenRosterPanel.add(greenTable);

		// Init and add green team total score label
		JLabel greenTotalScore = new JLabel(String.valueOf(pa.getGreenTeamScore()), SwingConstants.RIGHT);
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
		JLabel timerLabel = new JLabel("TIME: 6:00", SwingConstants.CENTER);
		timerLabel.setForeground(Color.CYAN);
		timerPanel.add(timerLabel);

		gameFrame.add(timerPanel, constraint);

		timer = new Timer(1000, new ActionListener() {
			// Init game time in seconds
			int gameTime = 360;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameTime > 0) {
                    gameTime--;
                    timerLabel.setText("TIME: " + gameTime/60 + ":" + gameTime%60);
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
		
		gameFrame.setVisible(true); // Show the game window
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		this.setBackground(Color.black);
		this.requestFocus();
	}
}
