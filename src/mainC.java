
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.BorderLayout;
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
/**
 * 
 */
public class mainC extends JFrame implements ActionListener{

	
	String logoImg = "logo.png";
	String strGames[] = { "Cellular Automata", "Game Of Life" };
	
	JComboBox<String> combo1 = new JComboBox<String>(strGames);
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
		

}

	public void mainMenu() {
	   combo1.setBounds(100, 100, 75, 75);
		System.out.println("Testing Commit");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
