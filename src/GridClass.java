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
	    // Extracting the model bit-by-bit
	    int modelNum = Integer.parseInt(modelText.getText()); // Get the binary model number as an integer
	    int bit7 = modelNum & 1; // Extract the least significant bit (bit 7)
	    int bit6 = (modelNum >> 1) & 1; // Extract bit 6 by shifting right 1 position
	    int bit5 = (modelNum >> 2) & 1; // Extract bit 5 by shifting right 2 positions
	    int bit4 = (modelNum >> 3) & 1; // Extract bit 4 by shifting right 3 positions
	    int bit3 = (modelNum >> 4) & 1; // Extract bit 3 by shifting right 4 positions
	    int bit2 = (modelNum >> 5) & 1; // Extract bit 2 by shifting right 5 positions
	    int bit1 = (modelNum >> 6) & 1; // Extract bit 1 by shifting right 6 positions
	    int bit0 = (modelNum >> 7) & 1; // Extract the most significant bit (bit 0)

	    JLabel Left = null; // Initialize a JLabel variable for the left neighbor (initially set to null)
	    JLabel Previous;     // Declare a JLabel variable for the previous cell
	    JLabel Right;        // Declare a JLabel variable for the right neighbor
	    JLabel Next;         // Declare a JLabel variable for the next cell below

	    for (int row = 1; row < NUM_ROWS - 1; row++) {
	        for (int col = 1; col < NUM_COLUMNS - 1; col++) {
	            // Handling different cell positions

	        	  if (row == 0 && col == NUM_COLUMNS / 2) {
	                  // Check if we are at the top row and middle column
	                  // Special case: top row, middle column
	                  Left = cells[row][col - 1];     // Get the left neighbor of the current cell
	                  Previous = cells[row][col];      // Get the current cell
	                  Right = cells[row][col + 1];    // Get the right neighbor of the current cell
	                  Next = cells[row + 1][col];     // Get the cell below the current cell
	              } else if (col == 0 || col == NUM_COLUMNS - 1) {
	                  // Check if we are at the leftmost or rightmost edge columns
	                  // Special case: left or right edge columns
	                  Previous = cells[row][col];      // Get the current cell
	                  Right = cells[row][col + 1];    // Get the right neighbor of the current cell
	                  Next = cells[row + 1][col];     // Get the cell below the current cell
	              } else {
	                  // Normal case for interior cells (not on the edges)
	                  Left = cells[row][col - 1];     // Get the left neighbor of the current cell
	                  Previous = cells[row][col];      // Get the current cell
	                  Right = cells[row][col + 1];    // Get the right neighbor of the current cell
	                  Next = cells[row + 1][col];     // Get the cell below the current cell
	              }

	        	// Rules to update cell colors based on the binary model
	        	  if (Left.getBackground().equals(Color.WHITE) &&
	        	      Previous.getBackground().equals(Color.WHITE) &&
	        	      Right.getBackground().equals(Color.WHITE)) {
	        	      // Case: 000
	        	      if (bit0 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit0 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.WHITE) &&
	        	      Previous.getBackground().equals(Color.WHITE) &&
	        	      Right.getBackground().equals(Color.BLACK)) {
	        	      // Case: 001
	        	      if (bit1 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit1 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.WHITE) &&
	        	      Previous.getBackground().equals(Color.BLACK) &&
	        	      Right.getBackground().equals(Color.WHITE)) {
	        	      // Case: 010
	        	      if (bit2 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit2 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.WHITE) &&
	        	      Previous.getBackground().equals(Color.BLACK) &&
	        	      Right.getBackground().equals(Color.BLACK)) {
	        	      // Case: 011
	        	      if (bit3 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit3 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.BLACK) &&
	        	      Previous.getBackground().equals(Color.WHITE) &&
	        	      Right.getBackground().equals(Color.WHITE)) {
	        	      // Case: 100
	        	      if (bit4 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit4 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.BLACK) &&
	        	      Previous.getBackground().equals(Color.WHITE) &&
	        	      Right.getBackground().equals(Color.BLACK)) {
	        	      // Case: 101
	        	      if (bit5 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit5 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.BLACK) &&
	        	      Previous.getBackground().equals(Color.BLACK) &&
	        	      Right.getBackground().equals(Color.WHITE)) {
	        	      // Case: 110
	        	      if (bit6 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit6 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
	        	      }
	        	  } else if (Left.getBackground().equals(Color.BLACK) &&
	        	      Previous.getBackground().equals(Color.BLACK) &&
	        	      Right.getBackground().equals(Color.BLACK)) {
	        	      // Case: 111
	        	      if (bit7 == 0) {
	        	          Next.setBackground(Color.WHITE); // Set the color of the next cell to white
	        	      } else if (bit7 == 1) {
	        	          Next.setBackground(Color.BLACK); // Set the color of the next cell to black
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
