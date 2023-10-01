
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class the creates Grid lines on the Center Panel of the Main window Frame
 */
public class GridClass extends JFrame implements ActionListener, ItemListener{

	private JLabel[][] cells;
	private static final int NUM_ROWS = 50;
	private static final int NUM_COLUMNS = 50;
	private JTextField modelText;
	/**
	 * Default Constructor
	 */
	public GridClass(JTextField modelText, JLabel[][] cells) {
		this.modelText = modelText;
		this.cells = cells;
	}

	
    /**
     * Description: this is the method that generates cellular automata on a 30 by 30 matrice
     * @return void
     */
	public void runSimulation() {
	    // Set the middle cell in the first row to 1
	    cells[0][NUM_ROWS / 2].setBackground(Color.BLACK);

	    // Get the model number as decimal
	    int modelNum = Integer.parseInt(modelText.getText()); 
	    String binaryRep = String.format("%8s", Integer.toBinaryString(modelNum)).replace(' ', '0'); //convert to binary strng
	    
	    boolean left,current,right ;
        
	    for (int row = 0;  row < NUM_ROWS - 1;  row ++) { 
	    	for (int col = 0; col < NUM_COLUMNS; col++) { // iterate through grid
	    		
	    		if (col == 0) { // checks if we are are the first column
	    			left = false; // if yes, the cells to the left are false/white
	    		} else {
	    			left = cells[row][col-1].getBackground() == Color.BLACK; // if no, check if the cell to the left is black?
	    		}

	    		if (col == NUM_COLUMNS - 1) { // checks if we are are the last column
	    			right = false; // if yes, the cells to the right are false/white
	    		} else {
	    			right = cells[row][col+1].getBackground() == Color.BLACK;  // if no, check if the cell to the right is black?
	    		}

	    		// check if cell is black/true?
	           current = cells[row][col].getBackground() == Color.BLACK;
	       

	        // Convert binary to boolean, '1' to true (black), and '0' to false (white)
	            boolean[] rule = new boolean[8];
	            for (int i = 0; i < 8; i++) {
	                rule[i] = binaryRep.charAt(i) == '1';
	            }
	            // Calculate an index based on the neighborhood cell states (left, current, right)
	            int index = (left ? 1 : 0) * 4 + (current ? 1 : 0) * 2 + (right ? 1 : 0);
	            // maps the index to the correct rule in the array 
	            boolean nextStatus = rule[7 - index];

	            if (nextStatus) {
	                cells[row + 1][col].setBackground(Color.BLACK);
	            } else {
	                cells[row + 1][col].setBackground(Color.WHITE);
	            }
	        }
	    }

	    // Assuming you have a JPanel (e.g., centerPanel) to hold the JLabels
	    for (int generation = 0; generation < NUM_ROWS; generation++) {
	        for (int cell = 0; cell < NUM_COLUMNS; cell++) {
	            if (cells[generation][cell].getBackground() == Color.BLACK) {
	                System.out.print("*");
	            } else {
	                System.out.print(" ");
	            }
	        }
	        System.out.println();
	    }
	}

	/**
	 * Description: A method that listens to item's state change
	 * @param ItemEvent
	 * @return void
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Description: A method that listens to action events
	 * @param ActionEvent
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
