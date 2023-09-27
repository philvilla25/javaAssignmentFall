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
	private JTextField modelText;
	/**
	 * Default Constructor
	 */
	public GridClass(JTextField modelText, JLabel[][] cells) {
		this.modelText = modelText;
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
		 int modelNum = Integer.parseInt(modelText.getText());
		 int bit0 = modelNum & 1;
		 int bit1 = (modelNum >> 1) & 1;
		 int bit2 = (modelNum >> 2) & 1;
		 int bit3 = (modelNum >> 3) & 1;
		 int bit4 = (modelNum >> 4) & 1;
		 int bit5 = (modelNum >> 5) & 1;
		 int bit6 = (modelNum >> 6) & 1;
		 int bit7 = (modelNum >> 7) & 1;
		 
		  JLabel Left = null;
          JLabel Previous;  // determine previous
          JLabel Right;
          JLabel Next;
		 
		 for (int row = 1; row <  NUM_ROWS -1; row++) {
	            for (int col = 1; col< NUM_COLUMNS -1; col++) {	
	            	
	            	// this is for the middle of the first row, the one that is meant to be black
	            	if (row == 0 && col == NUM_COLUMNS / 2) {
	            		  Left = cells[row][col - 1];
				          Previous = cells[row][col]; 
				          Right = cells[row][col + 1];
				          Next = cells[row + 1][col];
				     // by the edges
	                } else if (col == 0 || col == NUM_COLUMNS - 1) {
	                	 Previous = cells[row][col]; 
				         Right = cells[row][col + 1];
				         Next = cells[row + 1][col];   
	                }else {
	                // normal ones
			            Left = cells[row][col - 1];
			            Previous = cells[row][col]; 
			            Right = cells[row][col + 1];
			            Next = cells[row + 1][col];
	                }

	            	//case 000
					if (Left.getBackground().equals(Color.WHITE) &&
					    Previous.getBackground().equals(Color.WHITE) &&
					    Right.getBackground().equals(Color.WHITE)) {
		
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
					// case 001		
					}else if(Left.getBackground().equals(Color.WHITE) &&
						    Previous.getBackground().equals(Color.WHITE)&&
							Right.getBackground().equals(Color.BLACK)){
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
					// case 010
					}else if(Left.getBackground().equals(Color.WHITE) &&
							Previous.getBackground().equals(Color.BLACK) &&
						    Right.getBackground().equals(Color.WHITE)) {
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
					// case 100
					}else if(Left.getBackground().equals(Color.BLACK) &&
						    Previous.getBackground().equals(Color.WHITE) &&
						    Right.getBackground().equals(Color.WHITE)) {
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
				   // case 101
					}else if(Left.getBackground().equals(Color.BLACK) &&
						    Previous.getBackground().equals(Color.WHITE) &&
						    Right.getBackground().equals(Color.BLACK)) {
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
					// case 110
					}else if(Left.getBackground().equals(Color.BLACK) &&
						    Previous.getBackground().equals(Color.BLACK) &&
						    Right.getBackground().equals(Color.WHITE)) {
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
					// case 111
					}else if(Left.getBackground().equals(Color.BLACK) &&
						    Previous.getBackground().equals(Color.BLACK) &&
						    Right.getBackground().equals(Color.BLACK)) {
							
							if (bit0 == 0) {
								Next.setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								Next.setBackground(Color.BLACK);
							}
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
