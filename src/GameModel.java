import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GameModel {
	private int NUM_ROWS = 50;
	private int NUM_COLUMNS = 50;
	private JLabel[][] cells;
	
	
	public GameModel(int NUM_ROWS , int NUM_COLUMNS) {
        this.NUM_ROWS = NUM_ROWS;
        this.NUM_COLUMNS = NUM_COLUMNS;
        cells = new JLabel[NUM_ROWS][NUM_COLUMNS];
        createGrid();
    }
	
	public JLabel[][] getCells() {
        return cells;
	}
	
	public void createGrid() {
	    // Create empty cells (e.g., JLabels with a white background) and add them to the grid
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLUMNS; col++) {
	            JLabel cell = new JLabel();
	            cell.setOpaque(true);
	            cell.setBackground(Color.WHITE);
	            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set black border
	            cells[row][col] = cell;
	        }
	    }
	}
	
}
