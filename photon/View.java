package photon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.util.ArrayList;
//import java.util.Map;


public class View extends JFrame{

	private Model model;
	//private Game game;
	
	private JTextField[] redTeamIDFields = new JTextField[15];
	private JTextField[] redTeamCodenameFields = new JTextField[15];

	private EntryField[] allFields = new EntryField[60];
	


	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.setTitle("Photon");
        this.setSize(614, 638);
        this.setFocusable(true);
        //this.getContentPane().add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		



		// red team id entry boxes
		for (int h = 0; h < 4; h++){
			for (int i = 0; i < 15; i++){
				switch (h) {
					case 0: // creates red team id fields
						JTextField tempEntryIDRed = new JTextField();  
						tempEntryIDRed.setBounds(100, 20*i+50, 50, 20); 

						EntryField tempEntryFieldIDRed = new EntryField(tempEntryIDRed, i, h);
						allFields[i] = tempEntryFieldIDRed;
						

						this.add(tempEntryIDRed);
						break;
					case 1: // creates red team name fields
						JTextField tempEntryNameRed = new JTextField();  
						tempEntryNameRed.setBounds(150, 20*i+50, 100, 20); 

						EntryField tempEntryFieldNameRed = new EntryField(tempEntryNameRed, i, h);
						allFields[i+15] = tempEntryFieldNameRed;

						this.add(tempEntryNameRed);
						break;
					case 2: // creates green team id fields
						JTextField tempEntryIDGreen = new JTextField();  
						tempEntryIDGreen.setBounds(300, 20*i+50, 50, 20); 

						EntryField tempEntryFieldIDGreen = new EntryField(tempEntryIDGreen, i, h);
						allFields[i+30] = tempEntryFieldIDGreen;

						this.add(tempEntryIDGreen);
						break;
					case 3: // creates green team name fields
						JTextField tempEntryNameGreen = new JTextField();  
						tempEntryNameGreen.setBounds(350, 20*i+50, 100, 20);

						EntryField tempEntryFieldNameGreen = new EntryField(tempEntryNameGreen, i, h);
						allFields[i+45] = tempEntryFieldNameGreen;

						this.add(tempEntryNameGreen);
						break;
					default:
						break;
				}
				setActionListener(allFields[i]);
			}
		}


		// for (int i = 0; i < 15; i++)
		// {
		// 	JTextField tempEntryID = new JTextField();  
		// 	tempEntryID.setBounds(100, 20*i+50, 150, 20); 
		// 	redTeamIDFields[i] = tempEntryID;
			
		// 	// this block of code creates and defines a personal action listener for each textField, which listens for the Enter key. 
		// 	// for the id-boxes, it does not accept a non-integer, and SHOULD prompt the user to enter a valid number instead.
		// 	// finally, it adds a player to the model, which should also add it to the database.
		// 	tempEntryID.addActionListener(new ActionListener(){
		// 			@Override
		// 			public void actionPerformed(ActionEvent event) {
		// 				try {
							
		// 					Integer.parseInt(event.getSource().getText());
		// 				} catch (Exception e) {
		// 					System.out.println("non-number entered");
		// 					return;
		// 				}
		// 				// code isn't working here for new entries by id; the indexOf function is returning -1. solution should involve changing the arraylists to arrays.
		// 				model.addPlayer(Integer.parseInt(tempEntryID.getText()), 'r', i);
		// 				//model.addPlayer(redTeamIDFields.getInt(i), 0, i);
		// 			}
		// 	});

	    // 	this.add(tempEntryID);
			
		// }

		// // red team codeName entry boxes
		// for (int j = 0; j < 15; j++)
		// {
		// 	JTextField tempEntry = new JTextField();  
		// 	tempEntry.setBounds(300, 20*j+50, 150, 20); 
		// 	redTeamCodenameFields[j] = tempEntry;
			
		// 	tempEntry.addActionListener(new ActionListener(){
		// 			@Override
		// 			public void actionPerformed(ActionEvent event) {
		// 				//int tempIndex = redTeamCodenameFields.indexOf(tempEntry);
						
		// 				model.addPlayer(Integer.parseInt(redTeamIDFields[j].getText()), tempEntry.getText() ,'r', j);
		// 			}
		// 	});

	    // 	this.add(tempEntry);
			
		// }
		
    	//g.setSize(300,300);  
    	this.setLayout(null);  
    	//team1Entries.setVisible(true);
	}

	private void setActionListener(EntryField entryToListen){
        int column = entryToListen.getColumn();
		JTextField tempEntry = entryToListen.getEntryField();
		
        if(column == 0 || column == 2){
            tempEntry.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						
						Integer.parseInt(tempEntry.getText());
					} catch (Exception e) {
						System.out.println("non-number entered");
						return;
					}
					// code isn't working here for new entries by id; the indexOf function is returning -1. solution should involve changing the arraylists to arrays.
					if (column == 0)
						model.addPlayer(Integer.parseInt(tempEntry.getText()), 'r', entryToListen.getRow());
					else
						model.addPlayer(Integer.parseInt(tempEntry.getText()), 'g', entryToListen.getRow());

				}
			});
        }
        else if(column == 1 || column == 3){
			tempEntry.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					System.out.println("whoa! something happened!S");
					if (column == 1)
						model.addPlayer(Integer.parseInt(tempEntry.getText()), 'r', entryToListen.getRow());
					else
						model.addPlayer(Integer.parseInt(tempEntry.getText()), tempEntry.getText(), 'g', entryToListen.getRow());

				}
			});
        }

    }


	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(35, 5, 48));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
