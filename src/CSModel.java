
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
import javax.swing.JTextField;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 * 
 */
@SuppressWarnings("serial")
public class CSModel extends JFrame implements ActionListener, ItemListener{

	String title = "Cellular Automata";
	String logoImg = "logo.png";
	String logoBanner = "banner.png";
	String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	String languageOption[] = {"English", "Français"};
    Color customColor = new Color(11, 171, 164);
	JButton execButton = new JButton("Execute");; 
	JButton start;
	JButton help;
	JLabel home;
	JTextField rowsTextField, colsTextField ;
	JPanel centerPanel;
	JTextField modelText;
	JPanel cards;
	private static String currentLanguage = "en"; // Default language
	private static org.w3c.dom.Document currentXMLDocument; 
	
	/**
	 * Default constructor
	 */
	public CSModel() {
		 loadXMLResource(currentLanguage);	
	}


	public void mainMenu() {
		// settings for the frame
	   JFrame frame = new JFrame();
	   frame.setTitle(title);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setResizable(false);
	   frame.setSize(400,250);
	   frame.setVisible(true);
	   
	   // Setting image icon for program
	   ImageIcon gameIcon = new ImageIcon(logoImg);
	   frame.setIconImage(gameIcon.getImage());
	  
	   JPanel menu = new JPanel(); //use Flow Layout
	   JPanel subMenu = new JPanel(); // use flow layout
	   JPanel options = new JPanel(); // use box layout
	   options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
	   
	   //changing colors of panel
	   menu.setBackground(new Color(217,217,217));
	   subMenu.setBackground(new Color(217,217,217));
	   options.setBackground(new Color(217,217,217));
	   
	   
	   // content image
	   ImageIcon contentImage = new ImageIcon(logoImg);
	   JLabel imageLabel = new JLabel(contentImage);
	   subMenu.add(imageLabel);
	   
	    // "HOME" text
       home = new JLabel("HOME");
       home.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
       home.setHorizontalAlignment(JLabel.CENTER); //supposed to make text center aligned
       options.add(home);
	   
	   // settings for combo box
       JComboBox<String>games = new JComboBox<String>(strGames);
       games.setEditable(false);
       games.addItemListener(this);
       options.add(games);
       
	   
	   //buttons
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
       buttonPanel.setBackground(new Color(217,217,217));
	   start = new JButton("START");
	   help = new JButton("HELP");
	   buttonPanel.add(start);
       buttonPanel.add(help);
       start.setBackground(customColor);
	   help.setBackground(customColor);
	   options.add(buttonPanel);
	   
	   help.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Call your method when the button is clicked
		    	
		    	String helpMessage = getLocalizedString("messageHelp");
	           JOptionPane.showMessageDialog(frame, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
		    }
		});

	   start.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 String selectedProgram = (String) games.getSelectedItem();

		        // Call proper program when selected
		        if ("Cellular Automata".equals(selectedProgram)) {
		 			mainGUI gui = new mainGUI();
		 			gui.mainWindow();
		        } else if ("Game Of Life".equals(selectedProgram)) {
		        	 programNotAvailable();
		        } else if ("Turing Machine".equals(selectedProgram)) {
		        	 programNotAvailable();
		        }
		        
		        updateUIComponents();
		    }
		});
	  
	   
	   // settings for language box
       JComboBox<String>languages = new JComboBox<String>(languageOption);
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
       
       //adding panel to frame
       frame.add(menu, BorderLayout.CENTER);
       frame.setVisible(true);
	}
	
     
	public void programNotAvailable() {
	    JOptionPane.showMessageDialog(this, "Program is not available right now. Please check back later.", "Program Unavailable", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSModel runMenu = new CSModel();	
		runMenu.mainMenu();
		}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	 private void updateUIComponents() {
	        // Update UI components with localized strings
	        home.setText(getLocalizedString("home"));
	        start.setText(getLocalizedString("start"));
	        help.setText(getLocalizedString("help"));
	        // ... (update other UI components as needed)
	    }
	
	 private static void loadXMLResource(String language) {
		    try {
		        ClassLoader classLoader = CSModel.class.getClassLoader(); // Replace CSModel with your actual class name
		        InputStream inputStream = classLoader.getResourceAsStream("strings_" + language + ".xml");

		        if (inputStream != null) {
		            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            currentXMLDocument = builder.parse(inputStream);
		            currentXMLDocument.getDocumentElement().normalize();
		        } else {
		            System.err.println("XML resource not found for language: " + language);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
	 private static String getLocalizedString(String key) {
		    if (currentXMLDocument == null) {
		        return "XML document not loaded";
		    }

		    NodeList entryNodes = currentXMLDocument.getElementsByTagName("entry");

		    for (int i = 0; i < entryNodes.getLength(); i++) {
		        Element entryElement = (Element) entryNodes.item(i);

		        Element keyElement = (Element) entryElement.getElementsByTagName("key").item(0);
		        String keyValue = keyElement.getTextContent().trim();
		        if (keyValue.equals(key)) {
		        	
		            Element valueElement = (Element) entryElement.getElementsByTagName("value").item(0);    
		        	return valueElement.getTextContent().trim();
		        }
		    }

		    return "Key not found";
		}

	
}
