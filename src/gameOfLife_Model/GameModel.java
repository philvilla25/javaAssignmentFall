package gameOfLife_Model;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Model class
 */
public class GameModel {
	private int rows= 60;
	private int cols = 60;
	private String GLRule;
	private int steps;
	private Color mainColor = Color.BLACK;
	private Color cellColor = null;
	private JLabel[][] cells;
	
	/**
	 * Getter for steps
	 * @return steps
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * Set steps
	 * @param steps int to be set as steps
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * Getter for GLRule
	 * @return  GLRule
	 */
	public String getGLRule() {
		return GLRule;
	}

	/**
	 * Set GLRule
	 * @param gLRule String to be set as GLRule
	 */
	public void setGLRule(String gLRule) {
		GLRule = gLRule;
	}
	
	/**
	 * Getter for rows
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Setter for rows
	 * @param rows  int to be set as rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Getter for columns
	 * @return cols int to be set as cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Setter for columns
	 * @param cols column for grid
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	/**
	 * Getter for main color
	 * @return color
	 */
	public Color getMainColor() {
		return mainColor;
	}

	/**
	 * Setter for main color
	 * @param mainColor main color
	 */
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}

	/**
	 * Getter for cells
	 * @return 2d array of cells
	 */
	public JLabel[][] getCells() {
        return cells;
	}
	
	/**
	 * Game Model Default Constructor
	 */
	public GameModel() {
        cells = new JLabel[rows][cols];
        createGrid();
    }
	
	/**
	 * Getter for specific cells
	 * @param rowIndex specified row
	 * @param colIndex  specified column
	 * @return cell at particular row, column
	 */
	public JLabel getCell(int rowIndex, int colIndex) {
	    if (rowIndex >= 0 && rowIndex < cells.length && colIndex >= 0 && colIndex < cells[0].length) {
	        return cells[rowIndex][colIndex];
	    } else {
	        return null;
	    }
	}
	
	/**
	 * Create 2d grid
	 */
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
	
	/**
	 * Makes grid white
	 */
	public void blankGrid() {
		for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            cells[row][col].setBackground(Color.WHITE);
	        }
		}
	}
	
	/**
	 * Toggles cell state
	 * @param row Specified Row
	 * @param col Specified Column
	 */
	 public void toggleCellState(int row, int col) {
	        JLabel cell = getCell(row, col);
	        if (cell.getBackground() == Color.WHITE) {
	            cell.setBackground(mainColor);
	        } else {
	            cell.setBackground(Color.WHITE);
	        }
	 }
	
	 /**
	  * Set specified cell to main color
	  * @param row Specified Row
	  * @param col Specified Column
	  */
	 public void setCellToMainColour(int row, int col) {
			 JLabel cell = getCell(row, col);
			 cell.setBackground(mainColor);
		 }
	 
	 /**
	  * Checks if cell is alive
	  * @param cell Cell to check if its alive
	  * @return true if cell is alive, false if it is dead
	  */
	 public boolean isCellAlive(JLabel cell) {
		 Color backgroundColor = cell.getBackground();
		 return !Color.WHITE.equals(backgroundColor);
	 }
	 
	 /**
	  * reset colors (from mutlicolor to single color option)
	  */
    public void resetColors() {
		 for (int row = 0; row < rows; row++) {
		    for (int col = 0; col < cols; col++) {
		        JLabel cell = cells[row][col];
		        if (isCellAlive(cell)) {
		            cell.setBackground(mainColor);  // Change the colored cell back to the main color
		         }
		    }
		 }
     } 
    
    /**
     * Calculate live neighbors
     * @param row Specified Row
     * @param col Specified Column
     * @return the live neighbor count
     */
	 public int calculateLiveNeighbors(int row, int col) {
		int neighborsCount = 0;
	    // Check the top neighbor
	    JLabel top = getCell(row - 1, col);
	    if (top != null && isCellAlive(top)) {
	        neighborsCount++;
	    }
	    // Check the top-right neighbor
	    JLabel topRight = getCell(row - 1, col + 1);
	    if (topRight != null && isCellAlive(topRight)) {
	        neighborsCount++;
	    }
	    // Check the right neighbor
	    JLabel right = getCell(row, col + 1);
	    if (right != null && isCellAlive(right)) {
	        neighborsCount++;
	    }
	    // Check the bottom-right neighbor
	    JLabel bottomRight = getCell(row + 1, col + 1);
	    if (bottomRight != null && isCellAlive(bottomRight)) {
	        neighborsCount++;
	    }
	    // Check the bottom neighbor
	    JLabel bottom = getCell(row + 1, col);
	    if (bottom != null && isCellAlive(bottom)) {
	        neighborsCount++;
	    }
	    // Check the bottom-left neighbor
	    JLabel bottomLeft = getCell(row + 1, col - 1);
	    if (bottomLeft != null && isCellAlive(bottomLeft)) {
	        neighborsCount++;
	    }
	    // Check the left neighbor
	    JLabel left = getCell(row, col - 1);
	    if (left != null && isCellAlive(left)) {
	        neighborsCount++;
	    }
	    // Check the top-left neighbor
	    JLabel topLeft = getCell(row - 1, col - 1);
	    if (topLeft != null && isCellAlive(topLeft)) {
	        neighborsCount++;
	    }
	    return  neighborsCount;
	 }
	 
	 /**
	  * Determines the color the cell should be based on live neighbor
	  * @param liveNeighbors the number of live neighbors a cell has
	  * @return the color the cell should be
	  */
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
	 
	 /**
	  * Generates next generation of the cells
	  * @param useMultiColor Checks if multicolor option has been selected
	  */
	 public void nextGeneration(boolean useMultiColor) {
		// Split the 18-bit rule into two parts
		 String deadCellRule = GLRule.substring(0, 9); // First nine bits
		 String liveNeighborRule = GLRule.substring(9, 18); // Next nine bits
		 
		    for (int row = 0; row < rows; row++) {
		        for (int col = 0; col < cols; col++) {
		        	JLabel cell = cells[row][col];
		        	int liveNeighbours = calculateLiveNeighbors(row, col);
		        	if(isCellAlive(cell)) { // cell is alive
		        		if (liveNeighborRule.charAt(liveNeighbours) == '1') { // check the value at the neighbor count
		        			 // check color for cells
		        			if (useMultiColor) {
		        			        Color cellColor = getColorForLiveNeighbors(liveNeighbours);  // multicolor option
		        			        cell.setBackground(cellColor);
		        			} else {
		        			        cell.setBackground(mainColor);// use main color
		        			}	
		        		}else {
		        		  	cell.setBackground(Color.WHITE);// The cell becomes dead.
		        		}
		        		
		        	}else { // cell is dead
		        		if (deadCellRule.charAt(liveNeighbours) == '1') { // check the value at the neighbor count
		        			// color cells
			        		if (useMultiColor) {
		                        Color cellColor = getColorForLiveNeighbors(liveNeighbours);
		                        cell.setBackground(cellColor); // multicolor option
			                } else {
			                	cell.setBackground(mainColor);// use main color
			                }
		        		}else {
		        			cell.setBackground(Color.WHITE);// The cell remains dead.
		        		}
		        	}
		        }
		    }
	 }

	 
	 
}
	

