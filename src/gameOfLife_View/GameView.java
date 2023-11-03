package gameOfLife_View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gameOfLife_Model.GameModel;

public class GameView {
	/** Name of project **/
	private String title = "Cellular Automata";
	private String GameOfLifeTitle = "Game Of Life";
	/** Name of logo image **/
	private String logoImg = "../resources/logo.png";
	private ImageIcon gameIcon, banner ;
	private String GameOfLifeBanner = "../resources/Game of Life Banner.png";
	/** Color **/
	private Color customColor = new Color(11, 171, 164);
	private Color cyan = new Color(217, 217, 217);
	private Color green = new Color(12, 106, 71);
	private Color violet = new Color(238, 229, 240);
	private Color purple = new Color(240, 193, 250);
	/** Home Button **/
	private JLabel home;
	/** Name of games **/
	private String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	private JComboBox<String> games;
	/** Buttons **/
	private JButton start, help, random, manual, colorInput, startGOL, stopGOL, execGOL;
	private JMenu gameGOL, helpGOL, languageGOL;
	private JMenuItem newItem, solutionItem, exitItem, colorsItem, aboutItem;
	private JFrame splashFrame;
	private JFrame GameOfLifeFrame;
	private JTextField stepText,  modelText;
	private JCheckBox multicolorText ;
	private JPanel centerPanel;
	private JLabel[][] cells;
	private JLabel model, steps;
	private JComboBox<String> languages;
	/** Language options **/
	private String languageOption[] = { "English", "Français" };
	private String numberOfExecs;
	
	
	public GameView() {
		//splash screen buttons
		games = new JComboBox<String>(strGames);
		home = new JLabel("HOME");
		start = new JButton("START");
		help = new JButton("HELP");
		 
		// game of life buttons
		languages = new JComboBox<String>(languageOption);
		start = new JButton("START");
	    help = new JButton("HELP");
	    random = new JButton("RANDOM");
	    manual = new JButton("MANUAL");
	    colorInput = new JButton("COLOR");
	    startGOL = new JButton("START");
	    stopGOL = new JButton("STOP");
	    execGOL = new JButton("EXEC: " + numberOfExecs);
	    gameGOL = new JMenu("Game");
	    helpGOL = new JMenu("Help");
	    languageGOL = new JMenu("Language"); 
	    model= new JLabel("MODEL:");
	    modelText = new JTextField(10);
	    multicolorText = new JCheckBox();
	    steps = new JLabel("STEPS:");
	    stepText = new JTextField(10);
	    
	    //frames
	    splashFrame = new JFrame();
		GameOfLifeFrame = new JFrame();
	    gameIcon = new ImageIcon(logoImg);
	    banner = new ImageIcon(GameOfLifeBanner);
	    
	    // menu items
	    ImageIcon newIcon = new ImageIcon("../resources/menuiconnew.gif"); 
	    ImageIcon solutionIcon = new ImageIcon("../resources/menuiconsol.gif"); 
	    ImageIcon exitIcon = new ImageIcon("../resources/menuiconext.gif"); 
	    newItem = new JMenuItem("New", newIcon);
	    solutionItem = new JMenuItem("Solution", solutionIcon);
	    exitItem = new JMenuItem("Exit",exitIcon);
	    
	    ImageIcon colorsIcon = new ImageIcon("../resources/menuiconcol.gif"); 
	    ImageIcon aboutIcon = new ImageIcon("../resources/menuiconabt.gif"); 
	    colorsItem = new JMenuItem("New", colorsIcon);
	    aboutItem = new JMenuItem("Solution", aboutIcon);  
	}
	
	
	public JFrame getGameOfLifeFrame() {
		return GameOfLifeFrame;
	}


	public JMenuItem getExitItem() {
		return exitItem;
	}


	public JMenuItem getNewItem() {
		return newItem;
	}


	public void setExitItem(JMenuItem exitItem) {
		this.exitItem = exitItem;
	}


	public JMenu getLanguageGOL() {
		return languageGOL;
	}

	public JButton getColorInput() {
		return colorInput;
	}

	public JTextField getModelText() {
		return modelText;
	}

	public JCheckBox getMulticolorText() {
		return multicolorText;
	}

	public JTextField getStepText() {
		return stepText;
	}

	public void setStepText(JTextField stepText) {
		this.stepText = stepText;
	}

	public JButton getExecGOL() {
		return execGOL;
	}

	public JButton getStart() {
		return start;
	}

	public JButton getStartGOL() {
		return startGOL;
	}

	public JButton getStopGOL() {
		return stopGOL;
	}

	public  JFrame getSplashFrame() {
		return splashFrame;
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

	public JComboBox<String> getLanguages() {
		return languages;
	}

	public JButton getRandomButton() {
		return random;
	}

	public JButton getManualButton() {
		return manual;
	}

	public JLabel[][] getCells() {
		return cells;
	}
	
	/** Default Constructor **/
	public void SplashScreen() {
	    // Create a new JFrame for the main menu
//		splashFrame.setTitle(title);
//		splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		splashFrame.setResizable(false);
//		splashFrame.setSize(400, 250);
//		splashFrame.setVisible(true);
//	    
//	    // Set the image icon for the program
//	    splashFrame.setIconImage(gameIcon.getImage());
//
//	    // Create panels for the main menu
//	    JPanel menu = new JPanel(); // Use Flow Layout
//	    JPanel subMenu = new JPanel(); // Use flow layout
//	    JPanel options = new JPanel(); // Use box layout
//	    options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
//	    
//	    // Set background colors for panels
//	    menu.setBackground(cyan);
//	    subMenu.setBackground(cyan);
//	    options.setBackground(cyan);
//
//	    // Create a content image label
//	    ImageIcon contentImage = new ImageIcon(logoImg);
//	    JLabel imageLabel = new JLabel(contentImage);
//	    subMenu.add(imageLabel);
//	    
//	    // Create a label for "HOME" text
//	    home.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
//	    home.setHorizontalAlignment(JLabel.CENTER); // Center align text
//	    options.add(home);
//
//	    // Create a combo box for selecting games
//	    games.setEditable(false);
//	    options.add(games);
//	    options.add(games);
//
//	    // Create buttons panel
//	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//	    buttonPanel.setBackground(new Color(217, 217, 217));
//	    buttonPanel.add(start);
//	    buttonPanel.add(help);
//	    start.setBackground(customColor);
//	    help.setBackground(customColor);
//	    options.add(buttonPanel);
//	    
//	    // Create a combo box for selecting languages
//	    languages.setPreferredSize(new Dimension(150, 30));
//	    languages.setEditable(false);
//	    languages.setBackground(Color.BLACK);
//	    languages.setForeground(customColor);
//	    subMenu.add(options);
//	    menu.add(subMenu);
//	    menu.add(languages);
//
//	    // Add the menu panel to the frame
//	    splashFrame.add(menu, BorderLayout.CENTER);
//	    splashFrame.setVisible(true);
	
           //GameModel gameModel = new GameModel();
//			GameOfLife(gameModel.getCells());
//			getSplashFrame().dispose();
	}
	
	public void GameOfLife(JLabel[][] cells) {
	    // Settings for the frame
		
		try {
		GameOfLifeFrame.setTitle(GameOfLifeTitle);
		GameOfLifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameOfLifeFrame.setSize(1000, 800);
		GameOfLifeFrame.setResizable(false);

	    // Setting image icon for the program
	    GameOfLifeFrame.getContentPane().setBackground(green);
	    
	    // Create a JPanel for the top panel
	    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    topPanel.setBackground(green);

	    // Place logoBanner in the top panel
	    JLabel bannerLabel = new JLabel(banner);
	    topPanel.add(bannerLabel);
	    
	    // Create a JPanel for the bottom panel
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    bottomPanel.setBackground(green);

	    // random and manual buttons
	    random.setBackground(violet);
	    manual.setBackground(violet);
	    bottomPanel.add(random);
	    bottomPanel.add(manual);
	     
	    // model and multicolor inputs
	    bottomPanel.add(model);
	    bottomPanel.add(modelText);
	   
	    JLabel multicolor = new JLabel("MULTICOLOR:");
	    bottomPanel.add(multicolor);
	    multicolorText.setBackground(green);
	    bottomPanel.add(multicolorText);
	    
	    // Color input
	    colorInput.setBackground(violet);
	    bottomPanel.add(colorInput);
	    
	    // START input
	    startGOL.setBackground(purple);
	    bottomPanel.add(startGOL);
	    
	    // step inputs
	    bottomPanel.add(steps);
	    bottomPanel.add(stepText);
	    
	    // EXEC button
	    execGOL.setBackground(purple);
	    bottomPanel.add(execGOL);
	    
	    // STOP input
	    stopGOL.setBackground(purple);
	    bottomPanel.add(stopGOL);
	   
	    //Menu Panel
	    JMenuBar menuPanel = new  JMenuBar();
	    menuPanel.setBackground(violet);
	    menuPanel.add(gameGOL); // game menu 
	    gameGOL.add(newItem);
	    gameGOL.add(solutionItem);
	    gameGOL.add(exitItem);
	      
	    menuPanel.add(helpGOL); // help menu 
	    helpGOL.add(colorsItem);
	    helpGOL.add(aboutItem );
	    menuPanel.add(languageGOL); // language menu 
	    languageGOL.add("English");
	    languageGOL.add("French");
	    //Center Panel
	    centerPanel = paintGrid(cells);
	    centerPanel.setBackground(green);

	    
	    // Add topPanel and bottomPanel to the frame's content pane
	    
	    GameOfLifeFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
	    GameOfLifeFrame.setJMenuBar(menuPanel);
	    GameOfLifeFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
	    //GameOfLifeFrame.getContentPane().add(leftPanel, BorderLayout.WEST);
	    GameOfLifeFrame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

	    // Make the frame visible
	    GameOfLifeFrame.setVisible(true);
	    
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Color chooseColor() {
		Color selectedColor = JColorChooser.showDialog(GameOfLifeFrame, "Choose a Color", Color.BLACK);
		return selectedColor;
	}
	
	public JPanel paintGrid(JLabel[][] cells) {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(cells.length, cells[0].length));
		
	        for (JLabel[] row : cells) {
	            for (JLabel label : row) {
	                centerPanel.add(label);
	                         
	            }
	        }
	        	     
	    return centerPanel;  
	}      
	  

	
}
