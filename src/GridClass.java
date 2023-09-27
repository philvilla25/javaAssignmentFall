/**
 * 
 */

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
	private int modelNum;
	private JPanel centerPanel;
	/**
	 * Default Constructor
	 */
	public GridClass(JPanel centerPanel,int modelNum, JLabel[][] cells) {
		this.centerPanel = centerPanel;
		this.modelNum = modelNum;
		this.cells = cells;
	}
	

	public void runSimulation() {
		// option for continuously or scrolled
		/*
		 * for the model binary number enter, perform a calculation
		 * As described in the A11, each binary is in fact in one proper position composed by the tuple
			“LPR”: the left neighbour (L), your previous state in the generation x (P) and the right
			neighbour (R),so that we can evaluate the next generation (x+1)
		 */
		// for cells to be filled, you should add 1 and 0 and change colour
		
		// 8 bits represent different position of cells, and using models you can kind 
		// determine if it is zero or 1
		
		 // extracting model bit-by-bit
		 int bit7 = (modelNum >> 7) & 1;
		 int bit6 = (modelNum >> 6) & 1;
		 int bit5 = (modelNum >> 5) & 1;
		 int bit4 = (modelNum >> 4) & 1;
		 int bit3 = (modelNum >> 3) & 1;
		 int bit2 = (modelNum >> 2) & 1;
		 int bit1 = (modelNum >> 1) & 1;
		 int bit0 = modelNum & 1;
		 
		 for (int row = 0; row <  NUM_ROWS; row++) {
	            for (int col = 0; col < NUM_COLUMNS; col++) {	
	            	// im not sure about these
		            JLabel L = cells[row][col - 1];
		            JLabel P = cells[row][col];  // determine previous
		            JLabel R = cells[row][col + 1];
		            

					if (L.getBackground().equals(Color.WHITE) &&
					    R.getBackground().equals(Color.WHITE) &&
					    P.getBackground().equals(Color.WHITE)) {
		
							if (bit0 == 0) {
								cells[row][col].setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								cells[row][col].setBackground(Color.BLACK);
							}
					}else if(L.getBackground().equals(Color.WHITE) &&
						    R.getBackground().equals(Color.WHITE) &&
						    P.getBackground().equals(Color.WHITE)) {
						
					}
		 	  }
		 }
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
