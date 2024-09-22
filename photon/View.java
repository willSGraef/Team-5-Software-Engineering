package photon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.BorderFactory;


public class View extends JFrame{

	private Model model;
	
	private EntryField[] redTeamIDFields = new EntryField[15];
	private EntryField[] redTeamCodenameFields = new EntryField[15];
	private EntryField[] redTeamEquipmentIDFields = new EntryField[15];

	private EntryField[] greenTeamIDFields = new EntryField[15];
	private EntryField[] greenTeamCodenameFields = new EntryField[15];
	private EntryField[] greenTeamEquipmentIDFields = new EntryField[15];
	


	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.setTitle("Photon");
        this.setSize(614, 638);
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

		
		// this massive block iterates over h to represent columns, and i to represent rows. 
		// the switch statement controls which column is being generated. 
		// the JTextFields used to input players are stored in the EntryField objects, which know their own columns and rows.
		// each EntryField is passed to the setActionListener method, and finally added to the JFrame of this view.
		for (int h = 0; h < 6; h++){
			for (int i = 0; i < 15; i++){
				switch (h) {
					case 0: // creates red team id fields
						JTextField tempEntryIDRed = new JTextField();  
						tempEntryIDRed.setBounds(100, 20*i+50, 50, 20); 

						EntryField tempEntryFieldIDRed = new EntryField(tempEntryIDRed, i, h);
						redTeamIDFields[i] = tempEntryFieldIDRed;
						
						setActionListener(tempEntryFieldIDRed);
						this.add(tempEntryIDRed);
						break;
					case 1: // creates red-team name fields
						JTextField tempEntryNameRed = new JTextField();  
						tempEntryNameRed.setBounds(150, 20*i+50, 100, 20); 

						EntryField tempEntryFieldNameRed = new EntryField(tempEntryNameRed, i, h);
						redTeamCodenameFields[i] = tempEntryFieldNameRed;

						setActionListener(tempEntryFieldNameRed);
						this.add(tempEntryNameRed);
						break;
					case 2: // creates red-team equipment ID fields
						JTextField tempEntryEquipmentIDRed = new JTextField();  
						tempEntryEquipmentIDRed.setBounds(250, 20*i+50, 50, 20); 

						EntryField tempEntryFieldEquipmentIDRed = new EntryField(tempEntryEquipmentIDRed, i, h);
						redTeamEquipmentIDFields[i] = tempEntryFieldEquipmentIDRed;

						setActionListener(tempEntryFieldEquipmentIDRed);
						this.add(tempEntryEquipmentIDRed);
						break;
					case 3: // creates green team id fields
						JTextField tempEntryIDGreen = new JTextField();  
						tempEntryIDGreen.setBounds(350, 20*i+50, 50, 20); 

						EntryField tempEntryFieldIDGreen = new EntryField(tempEntryIDGreen, i, h);
						greenTeamIDFields[i] = tempEntryFieldIDGreen;

						setActionListener(tempEntryFieldIDGreen);
						this.add(tempEntryIDGreen);
						break;
					case 4: // creates green-team name fields
						JTextField tempEntryNameGreen = new JTextField();  
						tempEntryNameGreen.setBounds(400, 20*i+50, 100, 20);

						EntryField tempEntryFieldNameGreen = new EntryField(tempEntryNameGreen, i, h);
						greenTeamCodenameFields[i] = tempEntryFieldNameGreen;

						setActionListener(tempEntryFieldNameGreen);
						this.add(tempEntryNameGreen);
						break;
					case 5: // creates Green-team equipment ID fields
						JTextField tempEntryEquipmentIDGreen = new JTextField();  
						tempEntryEquipmentIDGreen.setBounds(500, 20*i+50, 50, 20); 

						EntryField tempEntryFieldEquipmentIDGreen = new EntryField(tempEntryEquipmentIDGreen, i, h);
						greenTeamEquipmentIDFields[i] = tempEntryFieldEquipmentIDGreen;

						setActionListener(tempEntryFieldEquipmentIDGreen);
						this.add(tempEntryEquipmentIDGreen);
					default:
						break;
				}
			}
		}
		
    	this.setLayout(null);  
    	
	}

	// this sets a different action listener depending on whether we are pressing the "enter" key on an ID column or a codeName column.
	private void setActionListener(EntryField entryToListen){
        int column = entryToListen.getColumn();
		int row = entryToListen.getRow();
		JTextField tempEntry = entryToListen.getTextField();

		// for the ID columns, it checks for if the input ID is a valid number, and then adds a player to the model.
		// here we hava a choice: we could check the database for a name here, or check in the model.
        if(column == 0 || column == 2){
            tempEntry.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						Integer.parseInt(tempEntry.getText());
					} 
					catch (Exception e) {
						System.out.println("please enter a valid ID");
						return;
					}
					if (column == 0)
						model.addPlayer(Integer.parseInt(tempEntry.getText()), 'r', entryToListen.getRow(), entryToListen.getRow());
					else
						model.addPlayer(Integer.parseInt(tempEntry.getText()), 'g', entryToListen.getRow(), entryToListen.getRow());

				}
			});
        } // 
        else if(column == 1 || column == 3){
			tempEntry.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						if(column == 1)
							Integer.parseInt(redTeamIDFields[row].getTextField().getText());
						else
							Integer.parseInt(greenTeamIDFields[row].getTextField().getText());
					} 
					catch (Exception e) {
						System.out.println("please enter a valid ID");
						return;
					}
					if (column == 1)
						model.addPlayer(Integer.parseInt(redTeamIDFields[row].getTextField().getText()), tempEntry.getText(), 'r', entryToListen.getRow(), entryToListen.getRow());
					else
						model.addPlayer(Integer.parseInt(greenTeamIDFields[row].getTextField().getText()), tempEntry.getText(), 'g', entryToListen.getRow(), entryToListen.getRow());

				}
			});
        }

    }


	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(35, 5, 48));
		
	}
}
