
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import turingMachine_Main.turingMachine_Server;
import turingMachine_Main.turingMachine_User;
/**
 * Name:Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * CST8221 A22
 * Date: 5th November, 2023.
 */
@SuppressWarnings("serial")
public class CSModel extends JFrame implements ActionListener, ItemListener {

	/** Name of project **/
	private String title = "Cellular Automata";
	/** Name of logo image **/
	private String logoImg = "../resources/logo.png";
	/** Name of games **/
	private String strGames[] = { "Cellular Automata", "Game Of Life", "TM - Server", "TM - User" };
	/** Language options **/
	private String languageOption[] = { "English", "Français" };
	/** Colour **/
	private Color customColor = new Color(11, 171, 164);
	/** Buttons **/
	private JButton start, help;
	/** home button **/
	private JLabel home;
	/** default langauge **/
	private static String currentLanguage = "en"; // Default language
	/** XML **/
	private static org.w3c.dom.Document currentXMLDocument;

	/**
	 * Default constructor
	 */
	public CSModel() {
		loadXMLResource(currentLanguage);
	}

	/**
	 * Description: method that generates
	 * the main menu pop up where user can choose between applications
	 * and start the application chosen
	 */
	public void mainMenu() {
	    // Create a new JFrame for the main menu
	    JFrame frame = new JFrame();
	    frame.setTitle(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.setSize(400, 250);
	    frame.setVisible(true);

	    // Set the image icon for the program
	    ImageIcon gameIcon = new ImageIcon(logoImg);
	    frame.setIconImage(gameIcon.getImage());

	    // Create panels for the main menu
	    JPanel menu = new JPanel(); // Use Flow Layout
	    JPanel subMenu = new JPanel(); // Use flow layout
	    JPanel options = new JPanel(); // Use box layout
	    options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

	    // Set background colors for panels
	    menu.setBackground(new Color(217, 217, 217));
	    subMenu.setBackground(new Color(217, 217, 217));
	    options.setBackground(new Color(217, 217, 217));

	    // Create a content image label
	    ImageIcon contentImage = new ImageIcon(logoImg);
	    JLabel imageLabel = new JLabel(contentImage);
	    subMenu.add(imageLabel);

	    // Create a label for "HOME" text
	    home = new JLabel("HOME");
	    home.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
	    home.setHorizontalAlignment(JLabel.CENTER); // Center align text
	    options.add(home);

	    // Create a combo box for selecting games
	    JComboBox<String> games = new JComboBox<String>(strGames);
	    games.setEditable(false);
	    games.addItemListener(this);
	    options.add(games);

	    // Create buttons panel
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    buttonPanel.setBackground(new Color(217, 217, 217));
	    start = new JButton("START");
	    help = new JButton("HELP");
	    buttonPanel.add(start);
	    buttonPanel.add(help);
	    start.setBackground(customColor);
	    help.setBackground(customColor);
	    options.add(buttonPanel);

	    // Add action listener for the "HELP" button
	    help.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Call your method when the button is clicked

	            String helpMessage = getLocalizedString("messageHelp");
	            JOptionPane.showMessageDialog(frame, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });

	    // Add action listener for the "START" button
	    start.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String selectedProgram = (String) games.getSelectedItem();

	            // Call the proper program when selected
	            if ("Cellular Automata".equals(selectedProgram)) {
	                mainGUI gui = new mainGUI();
	                gui.mainWindow();
	                frame.dispose();
	            } else if ("Game Of Life".equals(selectedProgram)) {
	               Game.main(currentLanguage);
	            } else if ("TM - Server".equals(selectedProgram)) { 
	            	turingMachine_Server server = new turingMachine_Server();
	            	server.serverWindow();
	            } else if ("TM - User".equals(selectedProgram)) {
	            	turingMachine_User user = new turingMachine_User();
	            	user.userWindow();
	            }
	            // Update UI components
	            updateUIComponents();
	        }
	    });

	    // Create a combo box for selecting languages
	    JComboBox<String> languages = new JComboBox<String>(languageOption);
	    languages.setPreferredSize(new Dimension(150, 30));
	    languages.setEditable(false);
	    languages.addItemListener(this);
	    languages.setBackground(Color.BLACK);
	    languages.setForeground(customColor);
	    languages.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String selectedLanguage = (String) languages.getSelectedItem();
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
	    });
	    subMenu.add(options);
	    menu.add(subMenu);
	    menu.add(languages);

	    // Add the menu panel to the frame
	    frame.add(menu, BorderLayout.CENTER);
	    frame.setVisible(true);
	}

	/**
	 * Displays a message dialog indicating that the program is not available.
	 */
	public void programNotAvailable() {
	    // Show a message dialog with program unavailable message
	    JOptionPane.showMessageDialog(
	            this,
	            "Program is not available right now. Please check back later.",
	            "Program Unavailable",
	            JOptionPane.INFORMATION_MESSAGE
	    );
	}

	/**
	 * Implementation of the actionPerformed method from the ActionListener interface.
	 * This method is called when an action event occurs.
	 *
	 * @param e The action event that occurred.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	    // TODO Auto-generated method stub
	    // You can add code here to handle action events.
	}

	/**
	 * Implementation of the itemStateChanged method from the ItemListener interface.
	 * This method is called when the state of an item changes in an item-selectable component.
	 *
	 * @param e The item event that occurred.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
	    // TODO Auto-generated method stub
	    // You can add code here to handle item state changes.
	}

	/**
	 * Updates various UI components with localized strings based on the selected language.
	 */
	private void updateUIComponents() {
	    // Update the 'home' label with a localized string
	    home.setText(getLocalizedString("home"));

	    // Update the 'start' button with a localized string
	    start.setText(getLocalizedString("start"));

	    // Update the 'help' button with a localized string
	    help.setText(getLocalizedString("help"));

	    // You can add more update statements here for other UI components as needed.
	}

	/**
	 * Loads an XML resource file for a specific language and parses it into an XML document.
	 *
	 * @param language The language code for which to load the XML resource.
	 */
	private static void loadXMLResource(String language) {
	    try {
	        // Get the class loader for the CSModel class
	        ClassLoader classLoader = CSModel.class.getClassLoader(); // Replace CSModel with your actual class name

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
	 * Description: main method where this program starts
	 * 
	 * @param args String arguement
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSModel runMenu = new CSModel();
		runMenu.mainMenu();
	}

}
