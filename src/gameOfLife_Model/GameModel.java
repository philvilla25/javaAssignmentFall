package gameOfLife_Model;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GameModel {
	private int rows= 60;
	private int cols = 60;
	private String GLRule;
	private String steps;
	private Color mainColor = Color.BLACK;
	private Color cellColor = null;
	
	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getGLRule() {
		return GLRule;
	}

	public void setGLRule(String gLRule) {
		GLRule = gLRule;
	}
	
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
	
	public void blankGrid() {
		for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            cells[row][col].setBackground(Color.WHITE);
	        }
		}
	}
	
	 public void toggleCellState(int row, int col) {
	        JLabel cell = getCell(row, col);
	        if (cell.getBackground() == Color.WHITE) {
	            cell.setBackground(mainColor);
	        } else {
	            cell.setBackground(Color.WHITE);
	        }
	 }
	
	 public void setCellToMainColour(int row, int col) {
			 JLabel cell = getCell(row, col);
			 cell.setBackground(mainColor);
		 }
	 
	 public void calculateNextGeneration(){
		  for (int row = 0; row < rows; row++) {
		        for (int col = 0; col < cols; col++) {
		        	
		        }
		  }
	 }
	 
	 public boolean isCellDead(JLabel cell) {
		 Color backgroundColor = cell.getBackground();
		 return !Color.WHITE.equals(backgroundColor);
	}
	 
    public void resetColors() {
		 for (int row = 0; row < rows; row++) {
		    for (int col = 0; col < cols; col++) {
		        JLabel cell = cells[row][col];
		        if (isCellDead(cell)) {
		            cell.setBackground(mainColor);  // Change the colored cell back to the main color
		         }
		    }
		 }
     } 
    
	 public int calculateLiveNeighbors(int row, int col) {
		int neighborsCount = 0;
	    // Check the top neighbor
	    JLabel top = getCell(row - 1, col);
	    if (top != null && isCellDead(top)) {
	        neighborsCount++;
	    }
	    // Check the top-right neighbor
	    JLabel topRight = getCell(row - 1, col + 1);
	    if (topRight != null && isCellDead(topRight)) {
	        neighborsCount++;
	    }
	    // Check the right neighbor
	    JLabel right = getCell(row, col + 1);
	    if (right != null && isCellDead(right)) {
	        neighborsCount++;
	    }
	    // Check the bottom-right neighbor
	    JLabel bottomRight = getCell(row + 1, col + 1);
	    if (bottomRight != null && isCellDead(bottomRight)) {
	        neighborsCount++;
	    }
	    // Check the bottom neighbor
	    JLabel bottom = getCell(row + 1, col);
	    if (bottom != null && isCellDead(bottom)) {
	        neighborsCount++;
	    }
	    // Check the bottom-left neighbor
	    JLabel bottomLeft = getCell(row + 1, col - 1);
	    if (bottomLeft != null && isCellDead(bottomLeft)) {
	        neighborsCount++;
	    }
	    // Check the left neighbor
	    JLabel left = getCell(row, col - 1);
	    if (left != null && isCellDead(left)) {
	        neighborsCount++;
	    }
	    // Check the top-left neighbor
	    JLabel topLeft = getCell(row - 1, col - 1);
	    if (topLeft != null && isCellDead(topLeft)) {
	        neighborsCount++;
	    }
	    return  neighborsCount;
	 }
	 
	 public Color getColorForLiveNeighbors(int liveNeighbors) {
		    switch (liveNeighbors) {
		        case 0:
		            cellColor = Color.RED;
		            break;
		        case 1:
		            cellColor = Color.GREEN; 
		            break;
		        case 2:
		            cellColor = Color.BLUE; 
		            break;
		        case 3:
		            cellColor = Color.YELLOW; 
		            break;
		        case 4:
		            cellColor = Color.MAGENTA; 
		            break;
		        case 5:
		            cellColor = Color.CYAN; 
		            break;
		        case 6:
		            cellColor = new Color (97, 156, 250); // blue
		            break;
		        case 7:
		            cellColor = new Color (250, 219, 97); //peach
		            break;
		        case 8:
		            cellColor = new Color (234, 110, 250); //purple
		            break;
		       default:
		        	cellColor = Color.BLACK; 
		            break;
		    }
		    return cellColor;
		}

	public Color getMainColor() {
		return mainColor;
	}

	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	 
	 
}
	

