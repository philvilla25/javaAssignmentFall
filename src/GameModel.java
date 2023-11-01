import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GameModel {
	private int rows= 60;
	private int cols = 60;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	private JLabel[][] cells;
	
	
	public GameModel() {
        cells = new JLabel[rows][cols];
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
	    for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            JLabel cell = new JLabel();
	            cell.setOpaque(true);
	            cell.setBackground(Color.WHITE);
	            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set black border
	            cells[row][col] = cell;
	        }
	    }
	}
	
	 public void toggleCellState(int row, int col) {
	        JLabel cell = getCell(row, col);
	        if (cell.getBackground() == Color.WHITE) {
	            cell.setBackground(Color.BLACK);
	        } else {
	            cell.setBackground(Color.WHITE);
	        }
	 }
}
	

