
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	String logoBanner = "banner.png";
	String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	String languageOption[] = {"English", "Français"};
    Color customColor = new Color(11, 171, 164);
	//String gameOf = "Game of Life";
	// JComboBox<String> combo1 = new JComboBox<String>(strGames);
	JButton execButton = new JButton("Execute");
	private int numRows = 0;
	private int numCols = 0;
	private int modelNum = 0;
	private JLabel[][] cells; 
	JTextField rowsTextField, colsTextField ;
	JPanel centerPanel;
	JTextField modelText;
	
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
		// Settings for the frame
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(1000, 800);
        frame.setResizable(false);

        // Setting image icon for program
        ImageIcon gameIcon = new ImageIcon(logoImg);
        frame.setIconImage(gameIcon.getImage());
        frame.getContentPane().setBackground(new Color(128, 128, 128));

        // Create a JPanel for the top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);

        // Place logoBanner in the top panel
        ImageIcon banner = new ImageIcon(logoBanner);
        JLabel bannerLabel = new JLabel(banner);
        topPanel.add(bannerLabel);

        // Create a JPanel for the bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel switchLanguage = new JLabel("Switch Language:");
        bottomPanel.add(switchLanguage);

        JComboBox<String> languages = new JComboBox<>(languageOption);
        languages.setEditable(false);
        // languages.addItemListener(this); // Make sure to implement ItemListener
        bottomPanel.add(languages);

        JLabel model = new JLabel("Model:");
        bottomPanel.add(model);
         modelText = new JTextField(10);
        bottomPanel.add(modelText);

        JLabel iterations = new JLabel("Iterations:");
        bottomPanel.add(iterations);
        JTextField iterationsText = new JTextField(5);
        bottomPanel.add(iterationsText);

        JLabel scroll = new JLabel("Scroll Continuously:");
        bottomPanel.add(scroll);
        JCheckBox checkBox1 = new JCheckBox();
        bottomPanel.add(checkBox1);

        // Buttons
        JButton run = new JButton("RUN");
        JButton pause = new JButton("PAUSE");
        JButton stop = new JButton("STOP");
        bottomPanel.add(run);
        bottomPanel.add(pause);
        bottomPanel.add(stop);
        run.setBackground(customColor);
        pause.setBackground(customColor);
        stop.setBackground(customColor);

        // Create a JPanel for the center content
        centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set a black border
     
        // Create a JPanel for the content using BorderLayout
        JPanel content = new JPanel(new BorderLayout());
        content.add(topPanel, BorderLayout.NORTH);
        content.add(centerPanel, BorderLayout.CENTER);
        content.add(bottomPanel, BorderLayout.SOUTH);

        // Add the content to the frame
        frame.add(content, BorderLayout.CENTER);
        
        //Left Pannel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JButton blankGridButton =  new JButton("BLANK GRID");
        blankGridButton.setBackground(customColor);
        leftPanel.add(blankGridButton);
       
        leftPanel.add(new JLabel("ROWS: "));
        rowsTextField = new JTextField(5);
        leftPanel.add(rowsTextField);
        leftPanel.add(new JLabel("COLUMNS: "));
        colsTextField = new JTextField(5);
        leftPanel.add(colsTextField);
        
        JButton createGridButton = new JButton("CREATE GRID");
        createGridButton.setBackground(customColor);
        leftPanel.add(createGridButton);
        createGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGrid();
            }
        });
        blankGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	blankGrid();
            }
        });
        content.add(leftPanel, BorderLayout.WEST);
        
        // grid content

        frame.setVisible(true);
	}
	
	//blank grid option
	public void blankGrid() {
		for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
            	 cells[row][col].setBackground(Color.WHITE);
            }
		}
	}
	
	// create new grid
	public void createGrid() {
	    try {
	        numRows = Integer.parseInt(rowsTextField.getText());
	        numCols = Integer.parseInt(colsTextField.getText());
	        // Create a grid layout for the panel
	        centerPanel.setLayout(new GridLayout(numRows, numCols));
	        cells = new JLabel[numRows][numCols];
	        // Create empty cells (e.g., JLabels with a white background) and add them to the grid
	        for (int row = 0; row < numRows; row++) {
	            for (int col = 0; col < numCols; col++) {
	                JLabel cell = new JLabel();
	                cell.setOpaque(true);
	                cell.setBackground(Color.white);
	                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set black border
	                centerPanel.add(cell);
	                
	                cells[row][col] = cell;
	                
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
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Please enter valid row and column numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void runSimulation() {
		// option for continuously or scrolled
		/*
		 * for the model binary number enter, perform a calculation
		 * As described in the A11, each binary is in fact in one proper position composed by the tuple
			“LPR”: the left neighbour (L), your previous state in the generation x (P) and the right
			neighbour (R),so that we can evaluate the next generation (x+1)
		 */
		// for cells to be filled, you should add 1 and 0 and change colour
		
		// 8 bits represent different position of cells, and using models you can kind 
		// determine if it is zero or 1
		 modelNum = Integer.parseInt(modelText.getText());
		
		 // extracting model bit-by-bit
		 int bit7 = (modelNum >> 7) & 1;
		 int bit6 = (modelNum >> 6) & 1;
		 int bit5 = (modelNum >> 5) & 1;
		 int bit4 = (modelNum >> 4) & 1;
		 int bit3 = (modelNum >> 3) & 1;
		 int bit2 = (modelNum >> 2) & 1;
		 int bit1 = (modelNum >> 1) & 1;
		 int bit0 = modelNum & 1;
		 
		 for (int row = 0; row < numRows; row++) {
	            for (int col = 0; col < numCols; col++) {	
	            	// im not sure about these
		            JLabel L = cells[row][col - 1];
		            JLabel P = cells[row][col];  // determine previous
		            JLabel R = cells[row][col + 1];
		            

					if (L.getBackground().equals(Color.WHITE) &&
					    R.getBackground().equals(Color.WHITE) &&
					    P.getBackground().equals(Color.WHITE)) {
		
							if (bit0 == 0) {
								cells[row][col].setBackground(Color.WHITE);
							}else if(bit0 == 1 ) {
								cells[row][col].setBackground(Color.BLACK);
							}
					}else if(L.getBackground().equals(Color.WHITE) &&
						    R.getBackground().equals(Color.WHITE) &&
						    P.getBackground().equals(Color.WHITE)) {
						
					}
		 	  }
		 }
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
