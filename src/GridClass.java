/**
 * 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class the creates Grid lines on the Center Panel of the Main window Frame
 */
public class GridClass extends JFrame implements ActionListener, ItemListener{

	private JLabel[][] cells; 
	private JPanel centerPanel;
	private int numRows;
	private int numColumns;
	/**
	 * Default Constructor
	 */
	public GridClass(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
