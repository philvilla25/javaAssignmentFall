import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GameModel {
	private int NUM_ROWS = 60;
	private int NUM_COLUMNS = 60;
	private JLabel[][] cells;
	
	
	public GameModel() {
        cells = new JLabel[NUM_ROWS][NUM_COLUMNS];
        createGrid();
    }
	
	public JLabel[][] getCells() {
        return cells;
	}
	
	public JLabel getCell(int rowIndex, int colIndex) {
	    if (rowIndex >= 0 && rowIndex < cells.length && colIndex >= 0 && colIndex < cells[0].length) {
	        return cells[rowIndex][colIndex];
	    } else {
	        return null;
	    }
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
	
	 public void toggleCellState(int row, int col) {
	        JLabel cell = cells[row][col];
	        if (cell.getBackground() == Color.WHITE) {
	            cell.setBackground(Color.BLACK);
	        } else {
	            cell.setBackground(Color.WHITE);
	        }
	 }
}
	

