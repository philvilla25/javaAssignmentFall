
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import layoutPackage.cardLayout;

/**
 * 
 */
public class mainC extends JFrame implements ActionListener, ItemListener{

	String title = "JAP (Fall 2021)";
	String logoImg = "logo.png";
	String strGames[] = { "Cellular Automata", "Game Of Life" };
	String cell = "Cellular automata";
	String gameOf = "Game of Life";
	JComboBox<String> combo1 = new JComboBox<String>(strGames);
	JButton execButton = new JButton("Execute");
	
	int game;
	JPanel cards;
	
	/**
	 * Top panel
	 */
	JPanel topPanel = new JPanel();
	
	/**
	 * Main panel
	 */
	JPanel mainPanel = new JPanel();
	
	
	/**
	 * Default constructor
	 */
	public mainC() {
		; // Default constructor
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainC runMenu = new mainC();
		
		runMenu.mainMenu();
		
}

	public void mainMenu() {
	   JFrame frame = new JFrame();
	   frame.setTitle("Java Game Project");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setResizable(false);
	   frame.setSize(400,200);
	   frame.setVisible(true);
	   
	   
	   ImageIcon image = new ImageIcon("logo.png");
	   frame.setIconImage(image.getImage());
	   frame.getContentPane().setBackground(new Color(128,128,128));
	   
	  //cardLayout dropDown = new cardLayout(frame);
	  // dropDown.execute();
	   JPanel comboBoxPane = new JPanel(); //use FlowLayout
	   comboBoxPane.setSize(200,200);
       String comboBoxItems[] = { "Game of Life", "Cellular Automata" };
       JComboBox<String>cb = new JComboBox<String>(comboBoxItems);
       cb.setEditable(false);
       cb.addItemListener(this);
       comboBoxPane.add(cb);
       
       frame.add(comboBoxPane, BorderLayout.CENTER);
       frame.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	 public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
     
	
}
