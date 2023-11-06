package gameOfLife_Model;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


/**
 * Name:Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * CST8221 A22
 * Date: 5th November, 2023.
 * Professor: Daniel Cormier
 * Compiler: Eclipse IDE for Java Developers - Version: 2023-06 (4.28.0)]
 */

/**
 * Class Name: GameModel
 * Method List:getSteps(),setSteps(int steps),getGLRule(),setGLRule(String GLRule),getRows() ,
 * 			setRows(int rows),getCols(),setCols(int cols), getMainColor(),setMainColor(Color mainColor),
 * 			getCells(),getCell(int rowIndex, int colIndex),createGrid(),blankGrid(),toggleCellState(int row, int col),
 * 			setCellToMainColour(int row, int col),isCellAlive(JLabel cell),resetColors(),
 * 			calculateLiveNeighbors(int row, int col),getColorForLiveNeighbors(int liveNeighbors),
 * 			nextGeneration(boolean useMultiColor).
 * Purpose:
 */
public class GameModel {
    private int rows = 60;            // Number of rows in the grid
    private int cols = 60;            // Number of columns in the grid
    private String GLRule;            // Rule for the Game of Life
    private int steps;                // Number of steps
    private Color mainColor = Color.BLACK;  // Main color for live cells
    private Color cellColor = null;  // Color of individual cells
    private JLabel[][] cells;        // 2D array to represent the grid of cells

    /**
     * Get the number of steps.
     *
     * @return The number of steps.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Set the number of steps.
     *
     * @param steps The number of steps to set.
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Get the Game of Life rule.
     *
     * @return The Game of Life rule as a string.
     */
    public String getGLRule() {
        return GLRule;
    }

    /**
     * Set the Game of Life rule.
     *
     * @param GLRule The Game of Life rule to set as a string.
     */
    public void setGLRule(String GLRule) {
        this.GLRule = GLRule;
    }

    /**
     * Get the number of rows in the grid.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Set the number of rows in the grid.
     *
     * @param rows The number of rows to set.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Get the number of columns in the grid.
     *
     * @return The number of columns.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Set the number of columns in the grid.
     *
     * @param cols The number of columns to set.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Get the main color used for live cells.
     *
     * @return The main color.
     */
    public Color getMainColor() {
        return mainColor;
    }
    /**
     * Set the main color used for live cells.
     *
     * @param mainColor The main color to set.
     */
    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    /**
     * Constructor for GameModel. Initializes the grid of cells and creates an empty grid.
     */
    public GameModel() {
        cells = new JLabel[rows][cols];  // Initialize the grid of cells
        createGrid();  // Create an empty grid
    }
	
    /**
     * Get the 2D array representing the grid of cells.
     *
     * @return The grid of cells.
     */
    public JLabel[][] getCells() {
        return cells;
    }

    /**
     * Get a specific cell at the given row and column.
     *
     * @param rowIndex The row index.
     * @param colIndex The column index.
     * @return The JLabel representing the cell.
     */
    public JLabel getCell(int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < cells.length && colIndex >= 0 && colIndex < cells[0].length) {
            return cells[rowIndex][colIndex];
        } else {
            return null;
        }
    }
	
    
    /**
     * Create an empty grid by setting all cells to white.
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
     * Set all cells in the grid to white, making the grid blank.
     */
	public void blankGrid() {
		for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            cells[row][col].setBackground(Color.WHITE);
	        }
		}
	}
	
	
	/**
     * Toggle the state of a cell at the given row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
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
	   * Set the color of a cell at the given row and column to the main color.
	   *
	   * @param row The row index of the cell.
	   * @param col The column index of the cell.
	   */
	 public void setCellToMainColour(int row, int col) {
			 JLabel cell = getCell(row, col);
			 cell.setBackground(mainColor);
		 }
	 
	 /**
	   * Check if a cell is alive (colored).
	   *
	   * @param cell The cell to check.
	   * @return `true` if the cell is alive, `false` if it's not.
	   */
	 public boolean isCellAlive(JLabel cell) {
		 Color backgroundColor = cell.getBackground();
		 return !Color.WHITE.equals(backgroundColor);
	 }
	 
	 /**
	   * Reset the colors of the grid by changing the colored cells back to the main color.
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
     * Calculate the number of live neighbors for a given cell at the specified row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of live neighbors.
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
	   * Get the color to be used for a cell based on the number of live neighbors.
	   *
	   * @param liveNeighbors The number of live neighbors.
	   * @return The color to be used for the cell.
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
	   * Calculate the next generation of the grid based on the Game of Life rules.
	   *
	   * @param useMultiColor Whether to use multi-color for cells.
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
	

