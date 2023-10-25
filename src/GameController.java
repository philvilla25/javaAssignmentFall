import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GameController implements ActionListener {
	private GameModel GameModel;
    private GameView GameView;
    /** default langauge **/
	private static String currentLanguage = "en"; // Default language
	/** XML **/
	private static org.w3c.dom.Document currentXMLDocument;

	public GameController(GameModel GameModel, GameView GameView) {
		 this.GameModel = GameModel;
	     this.GameView = GameView;
	     this.GameView.getStartButton().addActionListener(this);
	     this.GameView.getHelpButton().addActionListener(this);
	}
	
	// this is different from uml in A21
	public void handleStartClick() {
		JComboBox<String> gamesComboBox = GameView.getGamesComboBox();
	    String selectedProgram = (String)gamesComboBox.getSelectedItem();
        if ("Cellular Automata".equals(selectedProgram)) {
            mainGUI gui = new mainGUI();
            gui.mainWindow();
            GameView.getFrame().dispose();
        } else if ("Game Of Life".equals(selectedProgram)) {
            programNotAvailable();
        } else if ("Turing Machine".equals(selectedProgram)) {
            programNotAvailable();
        }

        // Update UI components
        updateUIComponents();	
	}

	public void handleHelpClick() {
		 String helpMessage = getLocalizedString("messageHelp");
         JOptionPane.showMessageDialog(GameView.getFrame(), helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == GameView.getStartButton()) {
			handleStartClick();
		}else if (e.getSource() == GameView.getHelpButton()) {
			handleHelpClick();
		}
	}
	
	public void programNotAvailable() {
	    // Show a message dialog with program unavailable message
	    JOptionPane.showMessageDialog(
	            GameView.getFrame(),
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


}
