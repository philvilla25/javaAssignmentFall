
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	JTextField rowsTextField, colsTextField ;
	JPanel centerPanel;
	JTextField modelText;
	JPanel cards;

	
	/**
	 * Default constructor
	 */
	public CSModel() {
		
	}


	public void mainMenu() {
		// settings for the frame
	   JFrame frame = new JFrame();
	   frame.setTitle(title);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setResizable(false);
	   frame.setSize(400,200);
	   frame.setVisible(true);
	   
	   // Setting image icon for program
	   ImageIcon gameIcon = new ImageIcon(logoImg);
	   frame.setIconImage(gameIcon.getImage());
	   frame.setBackground(new Color(217,217,217));
	 
	  
	   JPanel comboBoxPane = new JPanel(); //use Flow Layout
	   
	   // content image
	   ImageIcon contentImage = new ImageIcon(logoImg);
	   JLabel imageLabel = new JLabel(contentImage);
	   comboBoxPane.add(imageLabel);
	   
	   // settings for combo box
	   comboBoxPane.setSize(200,200);
       JComboBox<String>games = new JComboBox<String>(strGames);
       games.setEditable(false);
       games.addItemListener(this);
       comboBoxPane.add(games);
       
	   
	   //buttons
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	   JButton start = new JButton("START");
	   JButton help = new JButton("HELP");
	   buttonPanel.add(start);
       buttonPanel.add(help);
       start.setBackground(customColor);
	   help.setBackground(customColor);
	   comboBoxPane.add(buttonPanel);
	   
	   help.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Call your method when the button is clicked
		    	String helpMessage = "This program allows you to choose from three options:\nCellular Automata\nGame of Life\nTuring Machine.\n"
		    			+ "Choose the program you and then click the start button";
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
		            // Execute program 2
		            //executeProgram2();
		        	 programNotAvailable();
		        } else if ("Turing Machine".equals(selectedProgram)) {
		            // Execute program 3
		            //executeProgram3();
		        	 programNotAvailable();
		        }
		        
		   
		    }
		});
	  
	   
	   // settings for language box
       JComboBox<String>languages = new JComboBox<String>(languageOption);
       languages.setEditable(false);
       languages.addItemListener(this);
       comboBoxPane.add(languages); 
       languages.setBackground(Color.BLACK);
       languages.setForeground(customColor);
       
       //adding panel to frame
       frame.add(comboBoxPane, BorderLayout.CENTER);
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
	
}
