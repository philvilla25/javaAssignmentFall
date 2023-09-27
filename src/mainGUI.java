import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mainGUI {

	String title = "Cellular Automata";
	String logoImg = "logo.png";
	String logoBanner = "banner.png";
	String strGames[] = { "Cellular Automata", "Game Of Life", "Turing Machine" };
	String languageOption[] = {"English", "Français"};
    Color customColor = new Color(11, 171, 164);
	JButton execButton = new JButton("Execute");
	private static final int NUM_ROWS = 50;
	private static final int NUM_COLUMNS = 50;
	private JLabel[][] cells; 
	JTextField rowsTextField, colsTextField ;
	JPanel centerPanel;
	JTextField modelText;
	JPanel cards;
	JFrame frame = new JFrame();

	
	
	public void createGrid() {
	        // Create a grid layout for the panel
	        centerPanel.setLayout(new GridLayout(NUM_ROWS , NUM_COLUMNS));
	        cells = new JLabel[NUM_ROWS ][NUM_COLUMNS];
	        // Create empty cells (e.g., JLabels with a white background) and add them to the grid
	        for (int row = 0; row < NUM_ROWS ; row++) {
	            for (int col = 0; col < NUM_COLUMNS; col++) {
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
	}
	
	public void mainWindow() {
		// Settings for the frame
		try {
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
        
     
        createGrid();
        
        int modelNum = Integer.parseInt(modelText.getText());
        GridClass grid = new GridClass(centerPanel, modelNum, cells);
        
        // grid content
        frame.setVisible(true);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Please enter valid row and column numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}
}
