
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import layoutPackage.cardLayout;

/**
 * 
 */
public class CSModel extends JFrame implements ActionListener, ItemListener{

	String title = "Cellular Automata";
	String logoImg = "logo.png";
	String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	String languageOption[] = {"English", "Français"};
    Color customColor = new Color(11, 171, 164);
	//String gameOf = "Game of Life";
	// JComboBox<String> combo1 = new JComboBox<String>(strGames);
	JButton execButton = new JButton("Execute");
	
	//int game;
	JPanel cards;
	/*
	 * Top panel
	JPanel topPanel = new JPanel();*/
	
	/*/**
	 * Main panel
	JPanel mainPanel = new JPanel();*/

	
	
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
		        // Call your method when the button is clicked
		    	mainWindow();
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
	
	
	public void mainWindow() {
		// settings for the frame
		   JFrame frame = new JFrame();
		   frame.setTitle(title);
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   frame.setSize(screenSize.width, screenSize.height);
		   frame.setResizable(false);
		   frame.setVisible(true);
		   
		 // Setting image icon for program
		   ImageIcon gameIcon = new ImageIcon("banner.jpg");
		   frame.setIconImage(gameIcon.getImage());
		   frame.getContentPane().setBackground(new Color(128,128,128));
		   
		   JPanel content = new JPanel();
		   content.setBackground(Color.WHITE);
		   content.setLayout(new BorderLayout());
		   
		   JLabel label = new JLabel("This is the white content pane.");
		   content.add(label, BorderLayout.CENTER);
		   
		  // buttom panel
		  JPanel buttomPanel = new JPanel();
		  
		  JLabel switchLanguage = new JLabel("Switch Language:");
		  buttomPanel.add(switchLanguage);
		  
		  JComboBox<String>languages = new JComboBox<String>(languageOption);
	      languages.setEditable(false);
	      languages.addItemListener(this);
	      buttomPanel.add(languages);
	      
	      JLabel model = new JLabel("Model:");
		  buttomPanel.add(model);
		  JTextField modelText = new JTextField(10);
		  buttomPanel.add(modelText);
	      
		  JLabel Iterations = new JLabel("Iterations:");
		  buttomPanel.add(Iterations);
		  JTextField iterationsText = new JTextField(5);
		  buttomPanel.add(iterationsText);
		  
		  JLabel scroll = new JLabel("Scroll Continuously:");
		  buttomPanel.add(scroll);
		  JCheckBox checkBox1 = new JCheckBox();
		  buttomPanel.add(checkBox1);
		  
		  //buttons
		  JButton run = new JButton("RUN");
		  JButton pause = new JButton("PAUSE");
		  JButton stop = new JButton("STOP");
		  buttomPanel.add(run);
	      buttomPanel.add(pause);
	      buttomPanel.add(stop);
	      run.setBackground(customColor);
		  pause.setBackground(customColor);
		  stop.setBackground(customColor);
			 
	      //zoom in and zoom out buttons
		  
		 frame.add(content);
	     frame.add(buttomPanel);
	     frame.setVisible(true);
	}
	
	@Override
	 public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
     
	
	/**
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
	
}
