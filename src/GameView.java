import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameView {
	/** Name of project **/
	private String title = "Cellular Automata";
	/** Name of logo image **/
	private String logoImg = "../resources/logo.png";
	/** Color **/
	private Color customColor = new Color(11, 171, 164);
	private Color cyan = new Color(217, 217, 217);
	/** Home Button **/
	private JLabel home;
	/** Name of games **/
	private String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	private JComboBox<String> games;
	/** Buttons **/
	private JButton start, help;
	private  JFrame frame;
	
	/** Default Constructor **/
	public GameView() {
	    // Create a new JFrame for the main menu
	    frame = new JFrame();
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
	    menu.setBackground(cyan);
	    subMenu.setBackground(cyan);
	    options.setBackground(cyan);

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
	    games = new JComboBox<String>(strGames);
	    games.setEditable(false);
	    options.add(games);
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
	    
	    //start.addActionListener(GameController);
	
	}
	
    public JFrame getFrame() {
		return frame;
	}

	public JComboBox<String> getGamesComboBox() {
	        return games;
	    }
	   
	   public JButton getStartButton() {
	        return start;
	    }

	public JButton getHelpButton() {
		return help;
	}

	public JLabel getHomeButton() {
		return home;
	}

}
