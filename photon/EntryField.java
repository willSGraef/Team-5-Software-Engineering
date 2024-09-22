package photon;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class EntryField {

    private JTextField entryField = new JTextField(); 
    private int row; // 0 - 14
    private int column; // 0 - 3


    public EntryField(JTextField inputField, int row, int column){
        entryField = inputField;
        this.row = row;
        this.column = column;

        this.setActionListener();
    }

    private void setActionListener(){
        
        if(column == 0 || column == 2){
            
        }
        else if(column == 1 || column == 3){

        }

    }

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public JTextField getEntryField(){
        return entryField;
    }

}
