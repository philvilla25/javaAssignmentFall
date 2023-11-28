package turingMachine_Main;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class turingMachine_User {

	private JFrame userFrame;
	private String title = "Turing Machine User";
	private JPanel topPanel, buttonPanel, mainPanel;
	private String bannerName = "../resources/tm-client.png";
	private JButton sendButton, validateButton, receiveButton, endButton, runButton, connectButton;
	private JLabel userLabel, serverLabel, portLabel, tmLabel;
	private JTextField userText, serverText, portText, tmText;
	private JTextArea info;
	private JScrollPane sp;
	static String DEFAULT_USER = "User1";
	static String DEFAULT_ADDR = "localhost";
	static int DEFAULT_PORT = 12345;
	private int port, tm;
	private String user, server;

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
	
	public void userWindow() {
		try {
			userFrame.setTitle(title);
			userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			userFrame.setSize(570, 400);
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

		   
			// Make the frame visible
		    userFrame.setLayout(new BorderLayout());
		    userFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
	        userFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
	        userFrame.getContentPane().add(mainPanel, BorderLayout.SOUTH);
			userFrame.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendConfig() {
		String portInput = portText.getText();
        // Check if the input is not empty
        if (!portInput.isEmpty()) {
            // Parse the input as an integer if it's not empty
            port = Integer.parseInt(portInput);
            System.out.println("Entered port: " + port);
        } else {
            // Use the default value if the input is empty
            port = DEFAULT_PORT;
            System.out.println("Using default port: " + port);
        }
        
        String userInput = userText.getText();
        // Check if the input is not empty
        if (!userInput.isEmpty()) {
        	user = userInput;
        }else {
            // Use the default value if the input is empty
        	user = DEFAULT_USER;
        }
        
        String serverInput = serverText.getText();
        // Check if the input is not empty
        if (!serverInput.isEmpty()) {
        	server = serverInput;
        }else {
            // Use the default value if the input is empty
        	user = DEFAULT_ADDR;
        }
        
        String tmInput = tmText.getText();
        // Check if the input is not empty
        if (!tmInput.isEmpty()) {
        	//tm = tmInput;
        }else {
            // Use the default value if the input is empty
        	user = DEFAULT_ADDR;
        }
	}
	
}

