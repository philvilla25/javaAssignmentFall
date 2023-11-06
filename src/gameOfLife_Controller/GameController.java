package gameOfLife_Controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import gameOfLife_Model.GameModel;
import gameOfLife_View.GameView;  

/**
 * Name:Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * CST8221 A22
 * Date: 5th November, 2023.
 * Professor: Daniel Cormier
 * Compiler: Eclipse IDE for Java Developers - Version: 2023-06 (4.28.0)]
 */

/**
 * Class name: GameController
 * Methods list: callSplash(),handleHelpClick(),handleRandomManual(ActionEvent e),handleExitItem(),
 * 				handleNewItem(), handleCellClick(), handleSolutionItem(),handleRandomSet(),
 * 				handleModelTextBox(), isValidBinary18BitNumber(String binaryString),
 * 				handleStepsTextBox(), handleMultiColor(),actionPerformed(ActionEvent e),
 * 				handleStartClick(),handleStopClick(),handleChangeLang(String lang),handleColorSet(),
 * 				programNotAvailable(),updateUIComponents(),getLocalizedString(String key),
 * 				loadXMLResource(String language)
 * Constants list: currentLanguage,  currentXMLDocument
 * Purpose: This class manages user interactions, initiates the game, handles language changes, 
 * 			and controls game logic, including grid updates and color management
 */
public class GameController implements ActionListener {
	private GameModel GameModel;
    private GameView GameView;  
    private boolean useMultiColor = false;
    private Timer timer;
    private int delay = 1000; // Delay in milliseconds
    /** default language **/
	private static String currentLanguage = "en"; // Default language
	/** XML **/
	private static org.w3c.dom.Document currentXMLDocument;
	private int numSteps;
	
	/**
	 * This constructor initializes a GameController with the provided GameModel and GameView.
	 *
	 * @param GameModel The GameModel instance for the game.
	 * @param GameView The GameView instance for the game.
	 */
	public GameController(GameModel GameModel, GameView GameView) {
	    // Initialize the GameModel and GameView
	    this.GameModel = GameModel;
	    this.GameView = GameView;
	    // Add action listeners to various components in the GameView
	    this.GameView.getStartButton().addActionListener(this);
	    this.GameView.getHelpButton().addActionListener(this);
	    this.GameView.getLanguageGOL().addActionListener(this);
	    this.GameView.getManualButton().addActionListener(this);
	    this.GameView.getRandomButton().addActionListener(this);
	    this.GameView.getModelText().addActionListener(this);
	    this.GameView.getMulticolorText().addActionListener(this);
	    this.GameView.getColorInput().addActionListener(this);
	    this.GameView.getStartGOL().addActionListener(this);
	    this.GameView.getExecGOL().addActionListener(this);
	    this.GameView.getStopGOL().addActionListener(this);
	    this.GameView.getExitItem().addActionListener(this);
	    this.GameView.getNewItem().addActionListener(this);
	    this.GameView.getAboutItem().addActionListener(this);
	    this.GameView.getColorsItem().addActionListener(this);
	    this.GameView.getSolutionItem().addActionListener(null); // Action listener set to null
	    this.GameView.getEnglish().addActionListener(this);
	    this.GameView.getFrench().addActionListener(this);

	    // Call the splash screen method
	    callSplash();
	}
	
	
	/**
	 * Name: callSplash()
	 * Purpose: Displays a splash screen and schedules its disposal.
	 * Algorithm:Displays the splash screen using the GameView's SplashScreen method and schedules
	 *  a TimerTask to dispose of the splash screen after a delay. 
	 */
	public void callSplash() {
	    // Display the splash screen using the GameView's SplashScreen method
	    this.GameView.SplashScreen();

	    // Schedule a TimerTask to dispose of the splash screen after a delay
	    TimerTask disposeTask = new TimerTask() {
	        @Override
	        public void run() {
	            // Run the disposal and other methods after the splash screen is done
	            SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                    // Initialize the Game of Life and paint the grid
	                    GameView.GameOfLife(GameModel.getCells());
	                }
	            });

	            // Dispose of the splash frame
	            GameView.getSplashFrame().dispose();
	        }
	    };

	    // Create a Timer to schedule the disposeTask after a delay
	    Timer timer = new Timer();
	    // Adjust the delay (in milliseconds) as needed to ensure the splash screen is displayed long enough
	    int delay = 3000; // 3 seconds (adjust as needed)
	    timer.schedule(disposeTask, delay);
	}

	/**
	 * Name: handleHelpClick()
	 * Displays a help message when the help button is clicked.
	 */
	public void handleHelpClick() {
	    // Get the localized help message from resources
	    String helpMessage = getLocalizedString("messageHelp");
	    
	    // Display the help message in a dialog box with a title
	    JOptionPane.showMessageDialog(GameView.getSplashFrame(), helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Name: handleRandomManual(ActionEvent e)
	 * Handles the actions for the "Random" and "Manual" buttons.
	 *
	 * @param e The action event generated by button clicks.
	 */
	public void handleRandomManual(ActionEvent e) {
	    if (e.getSource() == GameView.getRandomButton()) {
	        // Handle the "Random" button action
	        // Add code here to perform the desired action for the "Random" button
	    } else if (e.getSource() == GameView.getManualButton()) {
	        // Handle the "Manual" button action by allowing user interaction
	        handleCellClick(); // This method allows the user to interact with the cells
	    }
	}
	
	/**
	 * Name: handleExitItem()
	 * Handles the action when the "Exit" menu item is selected.
	 * It disposes of the Game of Life frame, effectively closing the application.
	 */
	public void handleExitItem() {
	    // Dispose of the Game of Life frame to close the application
	    GameView.getGameOfLifeFrame().dispose();
	}

	/**
	 * Name: handleNewItem()
	 * Handles the action when the "New" menu item is selected.
	 * It clears the grid, resetting all cells to their initial state.
	 */
	public void handleNewItem() {
	    // Clear the grid by setting all cells to their initial state
	    GameModel.blankGrid();
	}
	
	/**
	 * Name:handleSolutionItem()
	 * Handles the action when the "Solution" menu item is selected.
	 * It advances the game to the next generation using the specified mode (single or multicolor).
	 */
	public void handleSolutionItem() {
	    // Advance the game to the next generation using the specified mode (single or multicolor).
	    GameModel.nextGeneration(useMultiColor);
	}
	
	/**
	 * Name: handleCellClick()
	 * Handles the action when a cell is clicked by the user.
	 * It allows the user to interact with individual cells by toggling their state.
	 */
	public void handleCellClick() {
	    // Get the 2D array of cells from the GameModel
	    JLabel[][] cells = GameModel.getCells();
	    
	    // Clear the grid (set all cells to blank)
	    GameModel.blankGrid();
	    
	    // Iterate through the cells and add mouse listeners to each cell
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	            final int currentRow = row;
	            final int currentCol = col;
	            JLabel cell = cells[currentRow][currentCol];
	            
	            // Add a mouse listener to toggle the cell's state when clicked
	            cell.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    GameModel.toggleCellState(currentRow, currentCol);
	                    cell.repaint(); // Repaint the cell to reflect the new state
	                }
	            });
	        }
	    }
	}

	
	/**
	 * Name: handleRandomSet()
	 * Handles setting the grid cells to random states.
	 * Randomly sets a percentage of cells to the main color.
	 */
	public void handleRandomSet() {
	    // Get the 2D array of cells from the GameModel
	    JLabel[][] cells = GameModel.getCells();
	    
	    // Clear the grid (set all cells to blank)
	    GameModel.blankGrid();
	    
	    // Iterate through the cells and randomly set a percentage of them to the main color
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	            boolean setCellToBlack = Math.random() < 0.15; // Adjust the probability as needed
	            if (setCellToBlack) {
	                GameModel.setCellToMainColour(row, col);
	            }
	        }
	    }
	}

	
	/**
	 * Name: handleModelTextBox() 
	 * Handles input in the model text box, validating and setting the GL rule.
	 * Gets the GL rule from the view, validates it as an 18-bit binary number,
	 * and sets it in the model if it's valid. Shows an error message if the input is invalid.
	 */
	public void handleModelTextBox() {
	    // Get the GL rule as text from the GameView
	    String GLRule = GameView.getModelText().getText();
	    
	    // Validate the input as an 18-bit binary number
	    boolean valid = isValidBinary18BitNumber(GLRule);
	    
	    // If the input is valid, set the GL rule in the GameModel
	    if (valid) {
	        GameModel.setGLRule(GLRule);
	    } else {
	        // Show an error message if the input is invalid
	        JOptionPane.showMessageDialog(GameView.getGameOfLifeFrame(), "Please enter a valid 18-bit binary number");
	    }
	}

	
	/**
	 * Name: isValidBinary18BitNumber(String binaryString)
	 * Validates a string to check if it represents a valid 18-bit binary number.
	 *
	 * @param binaryString The input string to be validated.
	 * @return True if the input is a valid 18-bit binary number, false otherwise.
	 */
	public static boolean isValidBinary18BitNumber(String binaryString) {
	    // Check if the string is exactly 18 characters long
	    if (binaryString.length() != 18) {
	        return false;
	    }
	    // Check if the string consists of only '0' and '1' characters
	    for (int i = 0; i < binaryString.length(); i++) {
	        char c = binaryString.charAt(i);
	        if (c != '0' && c != '1') {
	            return false;
	        }
	    }
	    return true;
	}

	
	/**
	 * Name: handleStepsTextBox()
	 * Handles the input from the steps text box, validates it, and updates the number of steps in the game model.
	 */
	public void handleStepsTextBox() {
	    try {
	        String stepsText = GameView.getStepText();
	        if (stepsText != null && !stepsText.isEmpty()) {
	            numSteps = Integer.parseInt(stepsText);
	            GameModel.setSteps(numSteps);
	        }
	    } catch (Exception e) {
	        // If an exception occurs, display a message to the user to enter a valid integer
	        JOptionPane.showMessageDialog(GameView.getGameOfLifeFrame(), "Please enter a valid integer");
	    }
	}

	
	/**
	 * Name:handleMultiColor()
	 * Handles the multi-color feature by updating the colors of the cells based on their state and live neighbors.
	 */
	public void handleMultiColor() {
	    JLabel[][] cells = GameModel.getCells();
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	            JLabel cell = cells[row][col];
	            if (GameModel.isCellAlive(cell)) {
	                int liveNeighbors = GameModel.calculateLiveNeighbors(row, col);
	                // Calculate the cell color based on the number of live neighbors
	                Color cellColor = GameModel.getColorForLiveNeighbors(liveNeighbors);
	                cells[row][col].setBackground(cellColor);
	            }
	        }
	    }
	}

	

	/**
	 * Name: actionPerformed(ActionEvent e)
	 * Handles action events triggered by various components.
	 * 
	 * @param e The ActionEvent object representing the action event.
	 */
	@Override
	/**
	 * Handles action events
	 */
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == GameView.getStartButton()) {
	        /**
	         * Handles the click event on the "Start" button.
	         */
	        handleStartClick();
	    } else if (e.getSource() == GameView.getHelpButton()) {
	        /**
	         * Handles the click event on the "Help" button.
	         */
	        handleHelpClick();
	    } else if (e.getSource() == GameView.getEnglish()) {
	        /**
	         * Handles the selection of the English language.
	         */
	        handleChangeLang(GameView.getEnglish().getText());
	    } else if (e.getSource() == GameView.getFrench()) {
	        /**
	         * Handles the selection of the French language.
	         */
	        handleChangeLang(GameView.getFrench().getText());
	    } else if (e.getSource() == GameView.getRandomButton()) {
	        /**
	         * Handles the click event on the "Random" button.
	         */
	        handleRandomSet();
	    } else if (e.getSource() == GameView.getManualButton()) {
	        /**
	         * Handles the click event on the "Manual" button.
	         */
	        handleRandomManual(e);
	    } else if (e.getSource() == GameView.getExitItem()) {
	        /**
	         * Handles the selection of the "Exit" menu item.
	         */
	        handleExitItem();
	    } else if (e.getSource() == GameView.getNewItem()) {
	        /**
	         * Handles the selection of the "New" menu item.
	         */
	        handleNewItem();
	    } else if (e.getSource() == GameView.getAboutItem()) {
	        /**
	         * Handles the selection of the "About" menu item.
	         */
	        GameView.aboutMenu();
	    } else if (e.getSource() == GameView.getStepText()) {
	        /**
	         * Handles changes to the step text field.
	         */
	        handleStepsTextBox();
	    } else if (e.getSource() == GameView.getMulticolorText()) {
	        /**
	         * Handles changes to the multi-color checkbox.
	         */
	        if (GameView.getMulticolorText().isSelected()) {
	            // Multi-color enabled
	            useMultiColor = true;
	            handleMultiColor();
	        } else {
	            // Multi-color disabled
	            useMultiColor = false;
	            GameModel.resetColors();
	        }
	    } else if (e.getSource() == GameView.getColorInput()) {
	        /**
	         * Handles the click event on the color input button.
	         */
	        handleColorSet();
	    } else if (e.getSource() == GameView.getColorsItem()) {
	        /**
	         * Handles the selection of the "Colors" menu item.
	         */
	        handleColorSet();
	    } else if (e.getSource() == GameView.getStartGOL()) {
	        /**
	         * Handles the click event on the "Start" button for the Game of Life.
	         */
	        handleStartClick();
	    } else if (e.getSource() == GameView.getStopGOL()) {
	        /**
	         * Handles the click event on the "Stop" button for the Game of Life.
	         */
	        handleStopClick();
	    }
	}

	/**
	 * Name: handleStartClick()
	 * Handles the click event on the "Start" button for running the Game of Life simulation.
	 */
	public void handleStartClick() {
	    try {
	        // Handle user inputs for steps and GLRule
	        handleStepsTextBox();
	        numSteps = GameModel.getSteps();
	        handleModelTextBox();

	        if (numSteps > 0) {
	            // Initialize and schedule a TimerTask for cell updates
	            timer = new Timer();
	            TimerTask cellUpdateTask = new TimerTask() {
	                int currentStep = 0;

	                @Override
	                public void run() {
	                    if (currentStep < numSteps) {
	                        // Advance the Game of Life simulation for the current step
	                        GameModel.nextGeneration(useMultiColor);

	                        // Update the GUI components
	                        GameView.getGameOfLifeFrame().revalidate();
	                        GameView.getGameOfLifeFrame().repaint();

	                        // Print a message for debugging purposes
	                        System.out.println("ni sud");

	                        currentStep++;

	                        // Update the step label in the GUI
	                        GameView.updateStepLabel(currentStep);
	                    } else {
	                        timer.cancel();  // Stop the timer when all steps are completed
	                    }
	                }
	            };

	            // Schedule the cell update task with the specified delay
	            timer.scheduleAtFixedRate(cellUpdateTask, 0, delay);
	        }
	    } catch (NullPointerException e) {
	        // Handle incomplete configurations
	        JOptionPane.showMessageDialog(
	            GameView.getGameOfLifeFrame(),
	            "Enter complete configurations",
	            "Incomplete Configurations Entered",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    }
	}

	/**
	 * Name:handleStopClick()
	 * Purpose: Handles the click event on the "Stop" button, stopping the Game of Life simulation if it's running.
	 */
	public void handleStopClick() {
	    // Check if the timer is not null (i.e., the simulation is running)
	    if (timer != null) {
	        // Cancel the timer to stop the simulation
	        timer.cancel();
	    }
	}

	
	/**
	 * Name:handleChangeLang(String lang) 
	 * Purpose: Handles a change in language selection and updates the user interface accordingly.
	 *
	 * @param lang The selected language (e.g., "English" or "Français").
	 */
	public void handleChangeLang(String lang) {
	    // Store the selected language
	    String selectedLanguage = lang;
	    System.out.println("choice" + selectedLanguage);

	    // Update the current language based on the selected language
	    switch (selectedLanguage) {
	        case "English":
	            currentLanguage = "en";
	            break;
	        case "Français":
	            currentLanguage = "fr";
	            break;
	    }

	    // Load the appropriate XML resource for the selected language
	    loadXMLResource(currentLanguage);

	    // Update the UI components to reflect the new language
	    updateUIComponents();
	}

	
	
	/**
	 * Name:handleColorSet()
	 * Purpose: Handles the color selection for the cells and updates the cell colors accordingly.
	 */
	public void handleColorSet() {
	    // Allow the user to choose a color
	    Color selectedColor = GameView.chooseColor();

	    // Set the main color for the GameModel
	    GameModel.setMainColor(selectedColor);

	    // Get the array of cells from the GameModel
	    JLabel[][] cells = GameModel.getCells();

	    // Update the background color of live cells with the selected color
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	            JLabel cell = cells[row][col];
	            if (GameModel.isCellAlive(cell)) {
	                cells[row][col].setBackground(selectedColor);
	            }
	        }
	    }

	    // Repaint the GameOfLifeFrame to apply the new cell colors
	    GameView.getGameOfLifeFrame().repaint();
	}


	/**
	 * Name:programNotAvailable()
	 * Purpose: Displays a message dialog to inform the user that the program is not available.
	 * 			This method is typically used when the program encounters unavailability or downtime.
	 */
	public void programNotAvailable() {
	    // Show a message dialog with the program unavailable message
	    JOptionPane.showMessageDialog(
	        GameView.getSplashFrame(),
	        "Program is not available right now. Please check back later.",
	        "Program Unavailable",
	        JOptionPane.INFORMATION_MESSAGE
	    );
	}

	
	/**
	 * Name:updateUIComponents() 
	 * Purpose: Updates various UI components with localized strings.
	 * 			This method is responsible for changing the text of UI components to match the selected language.
	 */
	public void updateUIComponents() {
	    // Update the 'home' label with a localized string
	    GameView.getHomeButton().setText(getLocalizedString("HOME"));
	    // Update the 'start' button with a localized string
	    GameView.getStartGOL().setText(getLocalizedString("START"));
	    // Update the 'help' button with a localized string
	    GameView.getHelpButton().setText(getLocalizedString("HELP"));
	    // Update the 'random' button with a localized string
	    GameView.getRandomButton().setText(getLocalizedString("RANDOM"));
	    // Update the 'manual' button with a localized string
	    GameView.getManualButton().setText(getLocalizedString("MANUAL"));
	    // Update the 'model' label with a localized string
	    GameView.getModel().setText(getLocalizedString("MODEL"));
	    // Update the 'color input' button with a localized string
	    GameView.getColorInput().setText(getLocalizedString("COLOR"));
	    // Update the 'steps' label with a localized string
	    GameView.getStep().setText(getLocalizedString("STEPS"));
	    // Update the 'stop' button with a localized string
	    GameView.getStopGOL().setText(getLocalizedString("STOP"));
	    // Update the 'language' menu with a localized string
	    GameView.getLanguageGOL().setText(getLocalizedString("LANGUAGE"));
	    // Update the 'help' menu with a localized string
	    GameView.getHelpGOL().setText(getLocalizedString("HELP"));
	    // Update the 'game' menu with a localized string
	    GameView.getGameGOL().setText(getLocalizedString("GAME"));
	    // You can add more update statements here for other UI components as needed.
	}


	/**
	 * Name:getLocalizedString(String key)
	 * Purpose: Retrieves a localized string from the loaded XML document based on the provided key.
	 *
	 * @param key The key used to look up the localized string in the XML document.
	 * @return The localized string corresponding to the provided key, or a default message if the key is not found.
	 */
	private static String getLocalizedString(String key) {
	    // Check if the XML document has been loaded
	    if (currentXMLDocument == null) {
	        return "XML document not loaded";
	    }
	    
	    // Get a NodeList of all 'entry' elements in the XML document
	    NodeList entryNodes = currentXMLDocument.getElementsByTagName("entry");
	   
	    // Iterate through the 'entry' elements to find a matching key
	    for (int i = 0; i < entryNodes.getLength(); i++) {
	        // Get the current 'entry' element
	        Element entryElement = (Element) entryNodes.item(i);

	        // Get the 'key' element within the 'entry' element
	        Element keyElement = (Element) entryElement.getElementsByTagName("key").item(0);
	        String keyValue = keyElement.getTextContent().trim();

	        // Compare the keyValue to the provided key
	        if (keyValue.equals(key)) {
	            // If a match is found, get the corresponding 'value' element
	            Element valueElement = (Element) entryElement.getElementsByTagName("value").item(0);

	            // Return the localized string found in the 'value' element
	            return valueElement.getTextContent().trim();
	        }
	    }

	    // If the key is not found, return a default message
	    return "Key not found";
	}
	
	/**
	 * Name: loadXMLResource(String language)
	 * Purpose: Loads an XML resource file for a specific language and parses it into an XML document.
	 *
	 * @param language The language code for which to load the XML resource.
	 */
	private static void loadXMLResource(String language) {
	    try {
	        // Get the class loader for the CSModel class
	        ClassLoader classLoader = GameController.class.getClassLoader(); // Replace CSModel with your actual class name

	        // Construct the file name for the XML resource based on the selected language
	        String xmlFileName = "strings_" + language + ".xml";

	        // Try to open the XML resource file as an input stream
	        InputStream inputStream = classLoader.getResourceAsStream(xmlFileName);

	        if (inputStream != null) {
	            // Create a DocumentBuilderFactory and DocumentBuilder for XML parsing
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();

	            // Parse the XML data into a Document object
	            currentXMLDocument = builder.parse(inputStream);

	            // Normalize the XML document's structure
	            currentXMLDocument.getDocumentElement().normalize();
	        } else {
	            // Print an error message if the XML resource file is not found
	            System.err.println("XML resource not found for language: " + language);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur during XML loading and parsing
	        e.printStackTrace();
	    }
	}
}
