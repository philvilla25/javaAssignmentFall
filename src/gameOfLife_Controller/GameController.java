package gameOfLife_Controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import gameOfLife_Model.GameModel;
import gameOfLife_View.GameView;  

public class GameController implements ActionListener {
	private GameModel GameModel;
    private GameView GameView;
    /** default language **/
	private static String currentLanguage = "en"; // Default language
	/** XML **/
	private static org.w3c.dom.Document currentXMLDocument;

	public GameController(GameModel GameModel, GameView GameView) {
		 this.GameModel = GameModel;
	     this.GameView = GameView;
	     this.GameView.getStartButton().addActionListener(this);
	     this.GameView.getHelpButton().addActionListener(this);
	     this.GameView.getLanguages().addActionListener(this);
	     this.GameView.getManualButton().addActionListener(this);
	     this.GameView.getRandomButton().addActionListener(this);
	     this.GameView.getModelText().addActionListener(this);
	     this.GameView.getMulticolorText().addActionListener(this);
	     this.GameView.getColorInput().addActionListener(this);
	     this.GameView.getStartGOL().addActionListener(this);
	     this.GameView.getStepText().addActionListener(this);
	     this.GameView.getExecGOL().addActionListener(this);
	     this.GameView.getStopGOL().addActionListener(this);
	     this.GameView.getExitItem().addActionListener(this);
	     this.GameView.getNewItem().addActionListener(this);
	     callSplash();
	}
	
	public void callSplash() {
		  this.GameView.SplashScreen();

		    // Schedule a TimerTask to dispose of the splash screen after a delay
		    TimerTask disposeTask = new TimerTask() {
		        @Override
		        public void run() {
		            SwingUtilities.invokeLater(new Runnable() {
		                @Override
		                public void run() {
		                    // Run the other methods after the splash screen is done
		                    GameView.GameOfLife(GameModel.getCells());
		                }
		            });
		            GameView.getSplashFrame().dispose();
		        }
		    };

		    // Create a Timer to schedule the disposeTask after a delay
		    Timer timer = new Timer();
		    // Adjust the delay (in milliseconds) as needed to ensure the splash screen is displayed long enough
		    int delay = 3000; // 3 seconds (adjust as needed)
		    timer.schedule(disposeTask, delay);
	}
	// this is different from uml in A21
	public void handleStartClick() {
		JComboBox<String> gamesComboBox = GameView.getGamesComboBox();
	    String selectedProgram = (String)gamesComboBox.getSelectedItem();
        if ("Cellular Automata".equals(selectedProgram)) {
            //mainGUI gui = new mainGUI();
            //gui.mainWindow();
            //GameView.getSplashFrame().dispose();
        } else if ("Game Of Life".equals(selectedProgram)) {
            GameView.GameOfLife(GameModel.getCells());
            GameView.getSplashFrame().dispose();
        } else if ("Turing Machine".equals(selectedProgram)) {
            programNotAvailable();
        }

        // Update UI components
        updateUIComponents();	
	}

	// help message does not show what its meant to?
	public void handleHelpClick() {
		 String helpMessage = getLocalizedString("messageHelp");
         JOptionPane.showMessageDialog(GameView.getSplashFrame(), helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public void handleChangeLang() {
		 String selectedLanguage = (String) GameView.getLanguages().getSelectedItem();
         switch (selectedLanguage) {
             case "English":
                 currentLanguage = "en";
                 break;
             case "Français":
                 currentLanguage = "fr";
                 break;
         }
         loadXMLResource(currentLanguage);
         // Update the UI components
         updateUIComponents();	
	}
	
	public void handleRandomManual(ActionEvent e) {
		if (e.getSource() == GameView.getRandomButton()) {
			// handle random
		}else if (e.getSource() == GameView.getManualButton()) {
			 handleCellClick(); //allow user click
		}
	}
	
	public void handleExitItem() {
		GameView.SplashScreen();
	}

	public void handleNewItem() {
		GameModel.blankGrid();
	}
	
	public void handleCellClick() {
	    JLabel[][] cells = GameModel.getCells();
	    GameModel.blankGrid();
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	            final int currentRow = row;
	            final int currentCol = col;
	            JLabel cell = cells[currentRow][currentCol];
	            cell.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    GameModel.toggleCellState(currentRow, currentCol);
	                    cell.repaint(); 
	                }
	            });
	        }
	    }
	}
	
	public void handleRandomSet(){
		JLabel[][] cells = GameModel.getCells();
		GameModel.blankGrid();
	    for (int row = 0; row < cells.length; row++) {
	        for (int col = 0; col < cells[0].length; col++) {
	        	 boolean setCellToBlack = Math.random() < 0.15;
	             if (setCellToBlack) {
	            	 GameModel.setCellToBlack(row, col);
	             }
	        }
	    }
	}
	
	public void handleModelTextBox() {
		String GLRule = GameView.getModelText().getText();
		boolean valid = isValidBinary18BitNumber(GLRule);
		  if (valid) {
			  GameModel.setGLRule(GLRule); // set rule
          } else {
              JOptionPane.showMessageDialog(GameView.getGameOfLifeFrame(), "Please enter a valid 18-bit binary number");
          } 
	}
	
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
	
	public void handleStepsTextBox() {
		String steps = GameView.getStepText().getText();
		GameModel.setSteps(steps);
	}
	
	public void handleMultiColor() {
		JLabel[][] cells = GameModel.getCells();
		for (int row = 0; row < cells.length; row++) {
		    for (int col = 0; col < cells[0].length; col++) {
		    	JLabel cell = cells[row][col];
		    	if (GameModel.isCellDead(cell)) {
		    		int liveNeighbors = GameModel.calculateLiveNeighbors(row, col);
			        Color cellColor = GameModel.getColorForLiveNeighbors(liveNeighbors);
			        cells[row][col].setBackground(cellColor);
		    	}
		    }
		}
	}
	
	// fill in the blanks for these and create the appropriate methods
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == GameView.getStartButton()) {
			handleStartClick();
		}else if (e.getSource() == GameView.getHelpButton()) {
			handleHelpClick();
		}else if (e.getSource() == GameView.getLanguages()) {
			handleChangeLang();
		}else if (e.getSource() == GameView.getRandomButton()) {
			handleRandomSet();
		}else if(e.getSource() == GameView.getManualButton() ) {
			handleRandomManual(e);
		}else if (e.getSource() == GameView.getExitItem()) {
			handleExitItem();
		}else if (e.getSource() == GameView.getNewItem()) {
			handleNewItem();
		}else if (e.getSource() == GameView.getModelText()) {
			handleModelTextBox();
		}else if (e.getSource() == GameView.getStepText()) {
			handleStepsTextBox();
		}else if (e.getSource() == GameView.getMulticolorText()) {
			 if (GameView.getMulticolorText().isSelected()) {
				 	handleMultiColor();
		        } else {
		            GameModel.resetColors();
		        }
		}else if (e.getSource() == GameView.getColorInput()) {
			handleColorSet();
		}else if (e.getSource() == GameView.getStartGOL()) {
			
		}else if (e.getSource() == GameView.getStepText()) {
				
		}else if (e.getSource() == GameView.getExecGOL()) {
			
		}else if (e.getSource() == GameView.getStopGOL()){
			
		}
	}
	
	
	public void handleColorSet() {
		Color selectedColor = GameView.chooseColor();	
		GameModel.setMainColor(selectedColor);
		JLabel[][] cells = GameModel.getCells();
		for (int row = 0; row < cells.length; row++) {
		    for (int col = 0; col < cells[0].length; col++) {
		    	JLabel cell = cells[row][col];
		    	if (GameModel.isCellDead(cell)) {
		    		cells[row][col].setBackground(selectedColor);
		    	}
		    }
		}
		 GameView.getGameOfLifeFrame().repaint();
	}
	

	public void programNotAvailable() {
	    // Show a message dialog with program unavailable message
	    JOptionPane.showMessageDialog(
	            GameView.getSplashFrame(),
	            "Program is not available right now. Please check back later.",
	            "Program Unavailable",
	            JOptionPane.INFORMATION_MESSAGE
	    );
	}
	
	public void updateUIComponents() {
	    // Update the 'home' label with a localized string
	    GameView.getHomeButton().setText(getLocalizedString("home"));
	    // Update the 'start' button with a localized string
	    GameView.getStartButton().setText(getLocalizedString("start"));
	    // Update the 'help' button with a localized string
	    GameView.getHelpButton().setText(getLocalizedString("help"));
	    // You can add more update statements here for other UI components as needed.
	}

	/**
	 * Retrieves a localized string from the loaded XML document based on the provided key.
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
	 * Loads an XML resource file for a specific language and parses it into an XML document.
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
