package gameOfLife_View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 * Name:Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * CST8221 A22
 * Date: 11 November, 2023.
 * Professor: Daniel Cormier
 * Compiler: Eclipse IDE for Java Developers - Version: 2023-06 (4.28.0)]
 */
public class GameView {
	/** Name of project **/
	private String GameOfLifeTitle = "Game Of Life";
	/** Name of logo image **/
	private String logoImg = "../resources/logo.png";
	private ImageIcon gameIcon, banner ;
	public JMenuItem getSolutionItem() {
		return solutionItem;
	}

	private String GameOfLifeBanner = "../resources/Game of Life Banner.png";
	/** Color **/
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
	private JMenuItem newItem, solutionItem, exitItem, colorsItem, aboutItem, englishLanguage, frenchLanguage;
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
	
	 /**
     * Default constructor for the GameView class. Initializes various components and settings.
     */
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

	    colorsItem = new JMenuItem("Colors", colorsIcon);
	    aboutItem = new JMenuItem("About", aboutIcon);  
	    
	    ImageIcon englishLangIcon = new ImageIcon("../resources/menuiconeng.gif");
	    englishLanguage = new JMenuItem("English", englishLangIcon);
	    ImageIcon frenchLangIcon = new ImageIcon("../resources/menuiconfra.gif");
	    frenchLanguage = new JMenuItem("Français", frenchLangIcon);
	}
	

	/**
	 * Update the label displaying the current execution number.
	 *
	 * @param currentExec The current execution number to display.
	 */
	public void updateStepLabel(int currentExec) {
	    execGOL.setText("EXEC: " + currentExec);
	}

	/**
	 * Get the "Help" menu item.
	 *
	 * @return The "Help" menu item.
	 */
	public JMenuItem getHelpGOL() {
	    return helpGOL;
	}

	/**
	 * Get the "Game" menu item.
	 *
	 * @return The "Game" menu item.
	 */
	public JMenuItem getGameGOL() {
	    return gameGOL;
	}

	/**
	 * Get the "About" menu item.
	 *
	 * @return The "About" menu item.
	 */
	public JMenuItem getAboutItem() {
	    return aboutItem;
	}

	/**
	 * Get the label for the model.
	 *
	 * @return The label for the model.
	 */
	public JLabel getModel() {
	    return model;
	}

	/**
	 * Get the label for the step.
	 *
	 * @return The label for the step.
	 */
	public JLabel getStep() {
	    return steps;
	}

	/**
	 * Get the "English" language menu item.
	 *
	 * @return The "English" language menu item.
	 */
	public JMenuItem getEnglish() {
	    return englishLanguage;
	}

	/**
	 * Get the "Français" language menu item.
	 *
	 * @return The "Français" language menu item.
	 */
	public JMenuItem getFrench() {
	    return frenchLanguage;
	}

	/**
	 * Get the main Game of Life frame.
	 *
	 * @return The main Game of Life frame.
	 */
	public JFrame getGameOfLifeFrame() {
	    return GameOfLifeFrame;
	}

	/**
	 * Get the "Exit" menu item.
	 *
	 * @return The "Exit" menu item.
	 */
	public JMenuItem getExitItem() {
	    return exitItem;
	}

	/**
	 * Get the "New" menu item.
	 *
	 * @return The "New" menu item.
	 */
	public JMenuItem getNewItem() {
	    return newItem;
	}

	/**
	 * Set the "Exit" menu item.
	 *
	 * @param exitItem The "Exit" menu item to set.
	 */
	public void setExitItem(JMenuItem exitItem) {
	    this.exitItem = exitItem;
	}

	/**
	 * Get the "Colors" menu item.
	 *
	 * @return The "Colors" menu item.
	 */
	public JMenuItem getColorsItem() {
	    return colorsItem;
	}

	/**
	 * Get the "Language" menu.
	 *
	 * @return The "Language" menu.
	 */
	public JMenu getLanguageGOL() {
	    return languageGOL;
	}

	/**
	 * Get the "Color" input button.
	 *
	 * @return The "Color" input button.
	 */
	public JButton getColorInput() {
	    return colorInput;
	}

	/**
	 * Get the model text input field.
	 *
	 * @return The model text input field.
	 */
	public JTextField getModelText() {
	    return modelText;
	}

	/**
	 * Get the multicolor text checkbox.
	 *
	 * @return The multicolor text checkbox.
	 */
	public JCheckBox getMulticolorText() {
	    return multicolorText;
	}

	/**
	 * Get the text entered in the step input field.
	 *
	 * @return The text entered in the step input field.
	 */
	public String getStepText() {
	    return stepText.getText();
	}

	/**
	 * Set the step input field.
	 *
	 * @param stepText The step input field to set.
	 */
	public void setStepText(JTextField stepText) {
	    this.stepText = stepText;
	}

	/**
	 * Get the "EXEC" button.
	 *
	 * @return The "EXEC" button.
	 */
	public JButton getExecGOL() {
	    return execGOL;
	}

	/**
	 * Get the "START" button.
	 *
	 * @return The "START" button.
	 */
	public JButton getStart() {
	    return start;
	}

	/**
	 * Get the "START" button for Game of Life.
	 *
	 * @return The "START" button for Game of Life.
	 */
	public JButton getStartGOL() {
	    return startGOL;
	}

	/**
	 * Get the "STOP" button for Game of Life.
	 *
	 * @return The "STOP" button for Game of Life.
	 */
	public JButton getStopGOL() {
	    return stopGOL;
	}

	/**
	 * Get the splash frame.
	 *
	 * @return The splash frame.
	 */
	public  JFrame getSplashFrame() {
	    return splashFrame;
	}

	/**
	 * Get the games combo box.
	 *
	 * @return The games combo box.
	 */
	public JComboBox<String> getGamesComboBox() {
	    return games;
	}

	/**
	 * Get the "START" button for the splash screen.
	 *
	 * @return The "START" button for the splash screen.
	 */
	public JButton getStartButton() {
	    return start;
	}

	/**
	 * Get the "HELP" button for the splash screen.
	 *
	 * @return The "HELP" button for the splash screen.
	 */
	public JButton getHelpButton() {
	    return help;
	}

	/**
	 * Get the "HOME" label for the splash screen.
	 *
	 * @return The "HOME" label for the splash screen.
	 */
	public JLabel getHomeButton() {
	    return home;
	}

	/**
	 * Get the languages combo box.
	 *
	 * @return The languages combo box.
	 */
	public JComboBox<String> getLanguages() {
	    return languages;
	}

	/**
	 * Get the "RANDOM" button for Game of Life.
	 *
	 * @return The "RANDOM" button for Game of Life.
	 */
	public JButton getRandomButton() {
	    return random;
	}

	/**
	 * Get the "MANUAL" button for Game of Life.
	 *
	 * @return The "MANUAL" button for Game of Life.
	 */
	public JButton getManualButton() {
	    return manual;
	}

	/**
	 * Get the 2D array of JLabels representing the grid of cells.
	 *
	 * @return The 2D array of JLabels representing the grid of cells.
	 */
	public JLabel[][] getCells() {
	    return cells;
	}

	
	  // Methods and functionality provided by the view

    /**
     * Display the splash screen for the application.
     */
	public void SplashScreen() {
		
		  SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                // Create the splash screen dialog
	                JDialog splashDialog = new JDialog();
	                splashDialog.setUndecorated(true); // Remove window decorations (title bar, border)
	                splashDialog.setSize(400, 150);
	                splashDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Dispose the dialog, not exit the application

	                // Center the dialog in the middle of the screen
	                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	                int x = (screenSize.width - splashDialog.getWidth()) / 2;
	                int y = (screenSize.height - splashDialog.getHeight()) / 2;
	                splashDialog.setLocation(x, y);

	                // Create a JPanel to hold the splash screen content
	                JPanel splashPanel = new JPanel();
	                splashPanel.setBackground(purple);
	                LineBorder lineBorder = new LineBorder(green, 10);
	                splashPanel.setBorder(lineBorder);
	                splashDialog.add(splashPanel);
	                
	                // Add a progress bar to the splash screen
	                JProgressBar progressBar = new JProgressBar(0, 100);
	                progressBar.setValue(50);
	                progressBar.setStringPainted(true);
	                progressBar.setForeground(green);
	                splashPanel.add(progressBar);

	                // Create a Timer to simulate loading progress
	                Timer timer = new Timer(19, new ActionListener() {
	                    int progress = 0;

	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        if (progress < 100) {
	                            progressBar.setValue(progress);
	                            progress++;
	                        } else {
	                            // Loading is complete
	                            ((Timer) e.getSource()).stop();
	                            splashDialog.dispose(); // Close the splash dialog
	                           
	                        }
	                    }
	                });

	                timer.start(); // Start the loading progress

	                // Show the splash dialog
	                
	                splashDialog.setVisible(true);
	            }
	        });
	    }
	    
	
	 /**
     * Display the main Game of Life user interface.
     *
     * @param cells The 2D array of JLabels representing the grid of cells.
     */
	public void GameOfLife(JLabel[][] cells) {
	    // Settings for the frame
		
		try {
		GameOfLifeFrame.setTitle(GameOfLifeTitle);
		GameOfLifeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GameOfLifeFrame.setSize(1050, 800);
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
	    languageGOL.add(englishLanguage);
	    languageGOL.add(frenchLanguage);
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
	
	/**
     * Show a color chooser dialog and return the selected color.
     *
     * @return The selected color.
     */
	public Color chooseColor() {
		Color selectedColor = JColorChooser.showDialog(GameOfLifeFrame, "Choose a Color", Color.BLACK);
		return selectedColor;
	}
	
	 /**
     * Display an "About" dialog providing information about Conway's Game of Life.
     */
	public void aboutMenu() {
		 JDialog aboutDialog = new JDialog();
         aboutDialog.setTitle("About My Application");
        // aboutDialog.setPreferredSize(new Dimension(400, 200));
         aboutDialog.setResizable(false);
         JTextArea textArea = new JTextArea("About Conway's Game of Life\r\n"
         		+ "\r\n"
         		+ "Conway's Game of Life is a cellular automaton developed by the British mathematician John Conway in 1970. "
         		+ "\r\n"
         		+ "It is a zero-player game that operates based on simple rules and can create complex and fascinating patterns.\r\n"
         		+ "\r\n"
         		+ "Game Logic:\r\n"
         		+ "Users enter an 18-bit binary number known as the \"GL rule.\" This rule defines the game's behavior,\r\n "
         		+ "and each generation (N) depends on the previous state (P) and the sum of neighbors (S).\r\n"
         		+ "\r\n"
         		+ "Rules:\r\n"
         		+ "- Value: 0 0 0 1 0 0 0 0 0 0 0 1 1 0 0 0 0 0\r\n"
         		+ "- Index positions: 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17\r\n"
         		+ "- Previous state: |-------------- DEAD (0) --------------| |-------------- ALIVE(1) -----------|\r\n"
         		+ "- Living neighbors: 0 1 2 3 4 5 6 7 8 0 1 2 3 4 5 6 7 8\r\n"
         		+ "- If the bit is on (1), the cell remains alive; if it's off (0), the cell dies.\r\n"
         		+ "- The first nine bits represent an empty cell with a count of living neighbors (00 to 08).\r\n"
         		+ "- The next nine bits represent a live cell's neighbor count.\r\n"
         		+ "\r\n"
         		+ "Enjoy exploring the world of Conway's Game of Life and watch how simple rules can lead to mesmerizing patterns and dynamics.\r\n");
         textArea.setEditable(false);
         textArea.setBackground(violet);
         aboutDialog.add(textArea);
         aboutDialog.pack();
         aboutDialog.setVisible(true);
	}
	
	
	/**
     * Create and return a JPanel for displaying the grid of cells.
     *
     * @param cells The 2D array of JLabels representing the grid of cells.
     * @return A JPanel containing the grid of cells.
     */
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
