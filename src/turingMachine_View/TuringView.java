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
		private turingMachine_User turingMachine;
		TuringView view;
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
		

		public JTextField getTapeText() {
			return tapeText;
		}


		public JButton getRunButton() {
			return runButton;
		}

 
		
		
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
				 mainPanel.setLayout(new BorderLayout()); // Set layout manager for mainPanel
		         mainPanel.add(sp, BorderLayout.CENTER); // Add JScrollPane to mainPanel

				 // Make the frame visible
		         tmFrame.setLayout(new BoxLayout(tmFrame.getContentPane(), BoxLayout.Y_AXIS));
		         tmFrame.add(topPanel);
		         tmFrame.add(firstButtonPanel);
		         tmFrame.add(secondButtonPanel);
		         tmFrame.add(mainPanel);
		         tmFrame.setVisible(true);
		        
		         TuringMachine turMachine = new TuringMachine(this.turingMachine);
		         
		         runButton.addActionListener(new ActionListener(){
		        
		             @Override
		             public void actionPerformed(ActionEvent e) {
		                 // Handle the runButton click event
		            	
		            	 if(turMachine.validateTm()) {	
		         			
		            		 turMachine.startTuringMachine(TuringView.this);	
		         		}
		            	 else {
		            		 System.out.println("Turing Machine is NULL");
		            	 }
		            	 
		                 // Perform actions based on the button click, you can customize this part
		                
		             }

					
		         });
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


		private void addRunButtonListener(ActionListener actionListener) {
			// TODO Auto-generated method stub
			
		}
}
