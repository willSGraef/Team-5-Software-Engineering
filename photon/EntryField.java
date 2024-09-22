package photon;
import javax.swing.JTextField;


public class EntryField {

    private JTextField textField = new JTextField(); 
    private int row; // 0 - 14
    private int column; // 0 - 3


    public EntryField(JTextField inputField, int row, int column){
        textField = inputField;
        this.row = row;
        this.column = column;
    }

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public JTextField getTextField(){
        return textField;
    }

}
