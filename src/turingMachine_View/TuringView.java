/*
 * File name: TuringView.java
 * Name: Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * Course: CST 8221 – JAP, Lab Section: 302
 * Assignment: A32 
 * Professor: Daniel Cormier
 * Date: 3rd December, 2024.
 * Compiler: Eclipse IDE for Java Developers - Version: 2022-03 (4.23.0)
 * Purpose: Class that acts as the view for the Turing Machine
 */
package turingMachine_View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import turingMachine_Main.turingMachine_Config;
import turingMachine_Main.turingMachine_User;
import turingMachine_Model.TuringMachine;

/**
 * Class Name: TuringView
 * Purpose: Turing Machine View
 */
public class TuringView {

		// instance variables
		private JFrame tmFrame;
		private String title = "Turing Machine";
		private JPanel topPanel, firstButtonPanel, secondButtonPanel, mainPanel;
		private String bannerName = "../resources/tm.png";
		private JButton runButton, clearButton;
		private JLabel tmLabel, tapeLabel;
		private JTextField tmText, tapeText;
		private JTextArea info;
		private JScrollPane sp;
		private turingMachine_User turingMachine;
		private TuringView view;
		
		/**
		 * Constructor 
		 * @param turingMachine Turing Machine Client View
		 */
		public TuringView(turingMachine_User turingMachine) {
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
			this.turingMachine = turingMachine;
		}
		

		/**
		 * Getter for Info text area
		 * @return Info text area
		 */
		public JTextArea getInfo() {
			return info;
		}

		/**
		 * Setter for Info text area
		 * @param info text area
		 */
		public void setInfo(JTextArea info) {
			this.info = info;
		}

		/**
		 * Getter for tape text
		 * @return tape text
		 */
		public JTextField getTapeText() {
			return tapeText;
		}

		/**
		 * Getter for run button
		 * @return run button
		 */
		public JButton getRunButton() {
			return runButton;
		}

		/**
		 * Method Name: tmWindow(String tmModel)
		 * @param tmModel String TM Model
		 */
		public void tmWindow(String tmModel) {
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
				 tmText.setText(tmModel);
				 
				 secondButtonPanel.add(tapeLabel);
				 secondButtonPanel.add(tapeText);
				 tapeText.setText(turingMachine_Config.DEFAULT_TAPE);
				 secondButtonPanel.add(runButton);
				 secondButtonPanel.add(clearButton);
				 
				 //main Panel
				 mainPanel.setLayout(new BorderLayout()); 
		         mainPanel.add(sp, BorderLayout.CENTER); 

				 // Make the frame visible
		         tmFrame.setLayout(new BoxLayout(tmFrame.getContentPane(), BoxLayout.Y_AXIS));
		         tmFrame.add(topPanel);
		         tmFrame.add(firstButtonPanel);
		         tmFrame.add(secondButtonPanel);
		         tmFrame.add(mainPanel);
		         tmFrame.setVisible(true);
		        
		         TuringMachine turMachine = new TuringMachine(this.turingMachine);
		         
		         //add action listener
		         runButton.addActionListener(new ActionListener(){
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 // Handle the runButton click event
		            	 if(turMachine.validateTm()) {	
		            		 turMachine.startTuringMachine(TuringView.this);	
		         		}else {
		            		 System.out.println("Turing Machine is NULL");
		            	 }
		             }

					
		         });	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


}
