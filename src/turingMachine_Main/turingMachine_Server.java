package turingMachine_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class turingMachine_Server {
	private JFrame serverFrame;
	private String title = "Turing Machine Server";
	private JPanel topPanel, buttonPanel, mainPanel;
	private String bannerName = "../resources/tm-server.png";
	private JButton startButton, modelButton, endButton;
	private JLabel portLabel, finalizesLabel;
	private JTextField portText;
	private JCheckBox  finalizesOption;
	private JTextArea info;
	private JScrollPane sp;

	public turingMachine_Server() {
		serverFrame = new JFrame();
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel = new JPanel();
		mainPanel = new JPanel();
		startButton = new JButton("Start");
		modelButton = new JButton("Model");
		endButton = new JButton("End");
		portLabel = new JLabel("Port:");
		portText = new JTextField(10);
		finalizesLabel = new JLabel("Finalizes");
		finalizesOption = new JCheckBox();
		info = new  JTextArea(10, 20);
		sp = new JScrollPane(info);
	}
	
	public void serverWindow() {
		try {
			serverFrame.setTitle(title);
			serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			serverFrame.setSize(559, 370);
			serverFrame.setResizable(false);
			
		    // Top Panel
			 ImageIcon banner = new ImageIcon(bannerName);
			 JLabel bannerLabel = new JLabel(banner);
			 topPanel.add(bannerLabel);
		    
			//button Panel
			 buttonPanel.add(portLabel);
			 buttonPanel.add(portText);
			 buttonPanel.add(startButton);
			 buttonPanel.add(modelButton);
			 buttonPanel.add( finalizesOption);
			 buttonPanel.add(finalizesLabel);
			 buttonPanel.add(endButton);
			 
			 //main Panel
			 mainPanel.setLayout(new BorderLayout()); // Set layout manager for mainPanel
	         mainPanel.add(sp, BorderLayout.CENTER); // Add JScrollPane to mainPanel

		   
			// Make the frame visible
		    serverFrame.setLayout(new BorderLayout());
		    serverFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
	        serverFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
	        serverFrame.getContentPane().add(mainPanel, BorderLayout.SOUTH);
			serverFrame.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
