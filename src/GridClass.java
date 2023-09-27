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
	private JPanel centerPanel;
	private int numRows;
	private int numColumns;
	/**
	 * Default Constructor
	 */
	public GridClass(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}
	
	
	
	public void createGrid(int numRows, int numColumns) {
		try {
			this.numRows = numRows;
			this.numColumns = numColumns;
	        // Create a grid layout for the panel
	        centerPanel.setLayout(new GridLayout(numRows, numColumns));
	        cells = new JLabel[numRows][numColumns];
	        // Create empty cells (e.g., JLabels with a white background) and add them to the grid
	        for (int row = 0; row < numRows; row++) {
	            for (int col = 0; col < numColumns; col++) {
	                JLabel cell = new JLabel();
	                cell.setOpaque(true);
	                cell.setBackground(Color.white);
	                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set black border
	                centerPanel.add(cell);
	                
	                cells[row][col] = cell;
	                
	                cell.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        // Toggle the cell's color when clicked
	                        if (cell.getBackground() == Color.WHITE) {
	                            cell.setBackground(Color.BLACK);
	                        } else {
	                            cell.setBackground(Color.WHITE);
	                        }
	                    }
	                });
	            }
	        }
	        // Repaint the panel to display the grid
	        centerPanel.revalidate();
	        centerPanel.repaint();
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Please enter valid row and column numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	public void blankGrid() {

		for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
            	 cells[row][col].setBackground(Color.WHITE);
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
