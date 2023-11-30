package turingMachine_View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TuringView {

		private JFrame tmFrame;
		private String title = "Turing Machine";
		private JPanel topPanel, firstButtonPanel, secondButtonPanel, mainPanel;
		private String bannerName = "../resources/tm.png";
		private JButton runButton, clearButton;
		private JLabel tmLabel, tapeLabel;
		private JTextField tmText, tapeText;
		private JTextArea info;
		private JScrollPane sp;

		public TuringView() {
			tmFrame = new JFrame();
			topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			firstButtonPanel = new JPanel();
			secondButtonPanel = new JPanel();
			mainPanel = new JPanel();
			clearButton = new JButton("Clear");
			runButton = new JButton("Run");
			tmLabel = new JLabel("TM:");
			tmText = new JTextField(50);
			tapeLabel = new JLabel("Tape:");
			tapeText = new JTextField(40);
			info = new  JTextArea(18, 40);
			sp = new JScrollPane(info);
		}
		

		public JButton getRunButton() {
			return runButton;
		}

		public void tmWindow() {
			try {
				tmFrame.setTitle(title);
				tmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tmFrame.setSize(800, 500);
				tmFrame.setResizable(false);
				
			    // Top Panel
				 ImageIcon banner = new ImageIcon(bannerName);
				 JLabel bannerLabel = new JLabel(banner);
				 topPanel.add(bannerLabel);
			    
				//button Panel 
				 firstButtonPanel.add(tmLabel);
				 firstButtonPanel.add(tmText);
				 
				 secondButtonPanel.add(tapeLabel);
				 secondButtonPanel.add(tapeText);
				 secondButtonPanel.add(runButton);
				 secondButtonPanel.add(clearButton);
				 
				 //main Panel
				 mainPanel.setLayout(new BorderLayout()); // Set layout manager for mainPanel
		         mainPanel.add(sp, BorderLayout.CENTER); // Add JScrollPane to mainPanel

				 // Make the frame visible
		         tmFrame.setLayout(new BoxLayout(tmFrame.getContentPane(), BoxLayout.Y_AXIS));
		         tmFrame.add(topPanel);
		         tmFrame.add(firstButtonPanel);
		         tmFrame.add(secondButtonPanel);
		         tmFrame.add(mainPanel);
		         tmFrame.setVisible(true);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
