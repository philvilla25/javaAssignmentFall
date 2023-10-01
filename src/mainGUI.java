import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class mainGUI {

	String title = "Cellular Automata";
	String logoImg = "logo.png";
	String logoBanner = "banner.png";
	String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	String languageOption[] = {"English", "Français"};
    Color customColor = new Color(199,21,133);
	JButton execButton = new JButton("Execute");
	JTextField rowsTextField, colsTextField ;
	JPanel centerPanel;
	JTextField modelText;
	JPanel cards;
	JButton run, pause, stop;
	JFrame frame = new JFrame();
	private static final int NUM_ROWS = 50;
	private static final int NUM_COLUMNS = 50;
	private JLabel[][] cells; 
	private static org.w3c.dom.Document currentXMLDocument;  
	private static String currentLanguage = "en"; 
	

	/**
	 * Creates and initializes the grid layout for the panel with empty cells.
	 */
	public void createGrid() {
	    // Create a grid layout for the panel
	    centerPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
	    cells = new JLabel[NUM_ROWS][NUM_COLUMNS];

	    // Create empty cells (e.g., JLabels with a white background) and add them to the grid
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLUMNS; col++) {
	            JLabel cell = new JLabel();
	            cell.setOpaque(true);
	            cell.setBackground(Color.WHITE);
	            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set black border
	            centerPanel.add(cell);

	            cells[row][col] = cell;

	            // Add a mouse click listener to toggle cell colors
	            cell.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    // Toggle the cell's color when clicked
	                    if (cell.getBackground() == Color.WHITE) {
	                        cell.setBackground(Color.BLACK);
	                    } else {
	                        cell.setBackground(Color.WHITE);
	                    }
	                }
	            });
	        }
	    }

	    // Repaint the panel to display the grid
	    centerPanel.revalidate();
	    centerPanel.repaint();
	}
	
	/**
	 * Configures and displays the main window of the program Cellular Automata
	 */
	public void mainWindow() {
	    // Settings for the frame
	    frame.setTitle(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1000, 800);
	    frame.setResizable(false);

	    // Setting image icon for the program
	    ImageIcon gameIcon = new ImageIcon(logoImg);
	    frame.setIconImage(gameIcon.getImage());
	    frame.getContentPane().setBackground(new Color(128, 128, 128));

	    // Create a JPanel for the top panel
	    JPanel topPanel = new JPanel();
	    topPanel.setBackground(Color.BLACK);

	    // Place logoBanner in the top panel
	    ImageIcon banner = new ImageIcon(logoBanner);
	    JLabel bannerLabel = new JLabel(banner);
	    topPanel.add(bannerLabel);

	    // Create a JPanel for the bottom panel
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    bottomPanel.setBackground(Color.BLACK);
	    bottomPanel.setForeground(Color.white);

	    JLabel switchLanguage = new JLabel("Switch Language:");
	    bottomPanel.add(switchLanguage);

	    JComboBox<String> languages = new JComboBox<>(languageOption);
	    languages.setBackground(customColor);
	    languages.setEditable(false);
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
	    bottomPanel.add(languages);

	    JLabel model = new JLabel("Model:");
	    bottomPanel.add(model);
	    modelText = new JTextField(10);
	    bottomPanel.add(modelText);

	    // Buttons
	    run = new JButton("RUN");
	    run.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            boolean valid = isValidDecimal(modelText.getText());
	            if (valid) {
	                GridClass grid = new GridClass(modelText, cells);
	                grid.runSimulation();
	            } else {
	                JOptionPane.showMessageDialog(frame, "Please enter a valid decimal betwen 0 and 255");
	            }
	        }
	    });

	    pause = new JButton("PAUSE");
	    stop = new JButton("STOP");
	    bottomPanel.add(run);
	    bottomPanel.add(pause);
	    bottomPanel.add(stop);
	    run.setBackground(customColor);
	    pause.setBackground(customColor);
	    stop.setBackground(customColor);

	    // Create a JPanel for the center content
	    centerPanel = new JPanel();
	    centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set a black border
	    centerPanel.setBackground(Color.black);
	    // Create a JPanel for the content using BorderLayout
	    JPanel content = new JPanel(new BorderLayout());
	    content.add(topPanel, BorderLayout.NORTH);
	    content.add(centerPanel, BorderLayout.CENTER);
	    content.add(bottomPanel, BorderLayout.SOUTH);

	    // Add the content to the frame
	    frame.add(content, BorderLayout.CENTER);

	    createGrid();

	    // Display the frame
	    frame.setVisible(true);
	}
	
	/**
	 * Updates the UI components with localized strings.
	 */
	private void updateUIComponents() {
	    // Update the text of the "RUN" button
	    run.setText(getLocalizedString("run"));

	    // Update the text of the "STOP" button
	    stop.setText(getLocalizedString("stop"));

	    // Update the text of the "PAUSE" button
	    pause.setText(getLocalizedString("pause"));
	}
	
	
	/**
	 * Loads an XML resource file for the specified language.
	 *
	 * @param language The language for which to load the XML resource file.
	 */
	private static void loadXMLResource(String language) {
	    try {
	        // Get the class loader for the CSModel class
	        ClassLoader classLoader = CSModel.class.getClassLoader();

	        // Construct the resource file name based on the selected language
	        String resourceFileName = "strings_" + language + ".xml";

	        // Attempt to open the XML resource file as an input stream
	        InputStream inputStream = classLoader.getResourceAsStream(resourceFileName);

	        if (inputStream != null) {
	            // Create a new DocumentBuilderFactory
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	            // Create a new DocumentBuilder
	            DocumentBuilder builder = factory.newDocumentBuilder();

	            // Parse the XML document from the input stream
	            currentXMLDocument = builder.parse(inputStream);

	            // Normalize the XML document
	            currentXMLDocument.getDocumentElement().normalize();
	        } else {
	            // If the input stream is null, the XML resource file was not found
	            System.err.println("XML resource not found for language: " + language);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur during loading
	        e.printStackTrace();
	    }
	}
 
	/**
	 * Retrieves a localized string value for the given key from the loaded XML document.
	 *
	 * @param key The key used to look up the localized string.
	 * @return The localized string value associated with the key, or an error message if the XML document is not loaded or the key is not found.
	 */
	private static String getLocalizedString(String key) {
	    // Check if the XML document is not loaded
	    if (currentXMLDocument == null) {
	        return "XML document not loaded";
	    }

	    // Get a list of entry nodes from the XML document
	    NodeList entryNodes = currentXMLDocument.getElementsByTagName("entry");

	    // Iterate through the entry nodes to find a matching key
	    for (int i = 0; i < entryNodes.getLength(); i++) {
	        // Get the current entry element
	        Element entryElement = (Element) entryNodes.item(i);

	        // Get the key element from the entry element
	        Element keyElement = (Element) entryElement.getElementsByTagName("key").item(0);

	        // Get the text content of the key element and trim any leading/trailing whitespace
	        String keyValue = keyElement.getTextContent().trim();

	        // Check if the current key matches the provided key
	        if (keyValue.equals(key)) {
	            // Get the value element associated with the key
	            Element valueElement = (Element) entryElement.getElementsByTagName("value").item(0);

	            // Return the text content of the value element as the localized string
	            return valueElement.getTextContent().trim();
	        }
	    }

	    // If the key is not found, return an error message
	    return "Key not found";
	}
	
	/**
	 * Checks whether the provided string represents a valid 8-bit binary number.
	 *
	 * @param modelInput The input string to validate.
	 * @return True if the input is a valid 8-bit binary number, false otherwise.
	 */
	public boolean isValidDecimal(String modelInput) {
	    try {
	        int decimalValue = Integer.parseInt(modelInput);

	        // Check if the decimal value is within the range [0, 255]
	        if (decimalValue >= 0 && decimalValue <= 255) {
	            return true; // Valid decimal value
	        } else {
	            return false; // Out of range
	        }
	    } catch (NumberFormatException e) {
	        return false; // Not a valid integer
	    }
	}

}
