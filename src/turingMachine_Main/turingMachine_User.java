/*
 * File name: turingMachine_User.java
 * Name: Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * Course: CST 8221 – JAP, Lab Section: 302
 * Assignment: A32 
 * Professor: Daniel Cormier
 * Date: 3rd December, 2024.
 * Compiler: Eclipse IDE for Java Developers - Version: 2022-03 (4.23.0)
 * Purpose: Class that handles Client GUI
 */

package turingMachine_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class Name:  turingMachine_User
 * Purpose: GUI for client window
 */
public class turingMachine_User {

	// attributes
	private JFrame userFrame;
	private String title = "Turing Machine User";
	private JPanel topPanel, buttonPanel, mainPanel;
	private String bannerName = "../resources/tm-client.png";
	private JButton sendButton, validateButton, receiveButton, endButton, runButton, connectButton;
	private JLabel userLabel, serverLabel, portLabel, tmLabel;
	private JTextField userText, serverText, portText, tmText;
	private JTextArea info;
	private JScrollPane sp;
	private int port;
	private String tmModel;
	private String user, server;

	/**
	 * Default constructor
	 */
	public turingMachine_User() {
		userFrame = new JFrame();
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel = new JPanel();
		mainPanel = new JPanel();
		endButton = new JButton("End");
		validateButton = new JButton("Validate");
		sendButton = new JButton("Send");
		receiveButton = new JButton("Recieve");
		runButton = new JButton("Run");
		userLabel = new JLabel("User:");
		userText = new JTextField(10);
		serverLabel = new JLabel("Server:");
		serverText = new JTextField(10);
		portLabel = new JLabel("Port:");
		portText = new JTextField(5);
		tmLabel = new JLabel("TM:");
		tmText = new JTextField(20);
		connectButton = new JButton("Connect");
		info = new  JTextArea(10, 20);
		sp = new JScrollPane(info);
	}
	
	/**
	 * Getter for End Button
	 * @return the end button
	 */
	public JButton getEndButton() {
		return endButton;
	}

	/**
	 * Getter for user frame
	 * @return user frame
	 */
	public JFrame getUserFrame() {
		return userFrame;
	}
	
	/**
	 * Setter for info text area
	 * @param inform text to put into the text area
	 */
	public void setInfo(String inform) {
		this.info.append(inform);
	}
	
	/**
	 * Getter for port number
	 * @return port number
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Setter for port number
	 * @param port Port number
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Getter for tm model
	 * @return tm model
	 */
	public String getTmModel() {
		return tmModel;
	}

	/**
	 * Setter for tm model
	 * @param tm Tm model
	 */
	public void setTmModel(String tm) {
		this.tmModel = tm;
	}

	/**
	 * Getter for user 
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Setter for user
	 * @param user user name
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Getter for server
	 * @return server name
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Setter for server
	 * @param server server name
	 */
	public void setServer(String server) {
		this.server = server;
	}
	
	/**
	 * Getter for connect button
	 * @return connect button
	 */
	public JButton getConnectButton() {
		return connectButton;
	}
	
	/**
	 * Setter for send button
	 * @return send button
	 */
	public JButton getSendButton() {
		return sendButton;
	}

	/**
	 * Getter for receive button
	 * @return receive button
	 */
	public JButton getReceiveButton() {
		return receiveButton;
	}

	/**
	 * Getter for run button
	 * @return run button
	 */
	public JButton getRunButton() {
		return runButton;
	}
	
	/**
	 * Getter for validate button
	 * @return validate button
	 */
	public JButton getValidateButton() {
		return validateButton;
	}
	
	/**
	 * Getter for info text area
	 * @return info text area
	 */
	public JTextArea getInfo() {
		return info;
	}

	/**
	 * Getter for tm text
	 * @return tm text
	 */
	public JTextField getTmText() {
		return tmText;
	}
	
	/**
	 * Getter for user name
	 * @return user name
	 */
	public String getUserName() {
		return user;
	}
	
	/**
	 * Setter for user name
	 * @param user user name
	 */
	public void setUserName(String user) {
		this.user = user;
	}
	
	/**
	 * Method Name: Client GUI Window
	 */
	public void userWindow() {
		try {
			userFrame.setTitle(title);
			userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			userFrame.setSize(579, 400);
			userFrame.setResizable(false);
			
		    // Top Panel
			 ImageIcon banner = new ImageIcon(bannerName);
			 JLabel bannerLabel = new JLabel(banner);
			 topPanel.add(bannerLabel);
		    
			//button Panel
			 buttonPanel.add(userLabel);
			 buttonPanel.add(userText);
			 buttonPanel.add(serverLabel);
			 buttonPanel.add(serverText);
			 buttonPanel.add(portLabel);
			 buttonPanel.add(portText);
			 buttonPanel.add(connectButton);
			 buttonPanel.add(sendButton);
			 buttonPanel.add(endButton);
			 buttonPanel.add(tmLabel);
			 buttonPanel.add(tmText);
			 buttonPanel.add(validateButton);
			 buttonPanel.add(sendButton);
			 buttonPanel.add(receiveButton);
			 buttonPanel.add(runButton);
			 
			 //main Panel
			 mainPanel.setLayout(new BorderLayout()); // Set layout manager for mainPanel
	         mainPanel.add(sp, BorderLayout.CENTER); // Add JScrollPane to mainPanel

		   
			// add to frame
		    userFrame.setLayout(new BorderLayout());
		    userFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
	        userFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
	        userFrame.getContentPane().add(mainPanel, BorderLayout.SOUTH);
			userFrame.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method Name:setConfig()
	 */
	public void setConfig() {
	    String portInput = portText.getText(); // get port input
	    String userInput = userText.getText(); // get user input
	    String serverInput = serverText.getText(); // get server input
	    System.out.println("Setconfig" + portInput + userInput + serverInput);
	    
	    if (!portInput.isEmpty()) {
	        // Parse the input as an integer if it's not empty
	        port = Integer.parseInt(portInput);
	    } else {
	        // Use the default value if the input is empty
	        port = turingMachine_Config.DEFAULT_PORT;
	    }

	     //Check if the input is not empty
	    if (userText.getText().isEmpty()) {
	        user = turingMachine_Config.DEFAULT_USER;
	    } else {
	        // Use the default value if the input is empty
	        user = userInput;
	    }

	    // Check if the input is not empty
	    if (!serverInput.isEmpty()) {
	        server = serverInput;
	    } else {
	        // Use the default value if the input is empty
	        server = turingMachine_Config.DEFAULT_ADDR;
	    }
	}
	
	/**
	 * Method Name:errorWindow(String message)
	 * @param message Message to be displayed on the window
	 */
    public void errorWindow(String message) {
         JOptionPane.showMessageDialog(userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

