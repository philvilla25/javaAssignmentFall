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
	private int port;
	private String tmModel;
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
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getTmModel() {
		return tmModel;
	}

	public void setTmModel(String tm) {
		this.tmModel = tm;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	public JButton getConnectButton() {
		return connectButton;
	}
	
	public JButton getSendButton() {
		return sendButton;
	}


	public JButton getReceiveButton() {
		return receiveButton;
	}


	public JButton getRunButton() {
		return runButton;
	}
	
	public JTextArea getInfo() {
		return info;
	}

	public JTextField getTmText() {
		return tmText;
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

	public void setConfig() {
	    String portInput = portText.getText();
	    String userInput = userText.getText();
	    String serverInput = serverText.getText();

	    // Check if the input is not empty
	    if (!portInput.isEmpty()) {
	        // Parse the input as an integer if it's not empty
	        port = Integer.parseInt(portInput);
	    } else {
	        // Use the default value if the input is empty
	        port = turingMachine_Config.DEFAULT_PORT;
	    }

	    // Check if the input is not empty
	    if (!userInput.isEmpty()) {
	        user = userInput;
	    } else {
	        // Use the default value if the input is empty
	        user = turingMachine_Config.DEFAULT_USER;
	    }

	    // Check if the input is not empty
	    if (!serverInput.isEmpty()) {
	        server = serverInput;
	    } else {
	        // Use the default value if the input is empty
	        user = turingMachine_Config.DEFAULT_ADDR;
	    }
	}
	
	public boolean validateTm() {
		String tmInput = tmText.getText();
	    // Check if the input is not empty
	    if (!tmInput.isEmpty()) {
	        if (isValidModel(tmInput)) {
	        	setTmModel(tmInput);
	            return true;  // Return true if the TM model is valid
	        } else {
	            return false;  // Return false if the TM model is invalid
	        }
	    } else {
	        String message = "Enter valid TM Model";
	        JOptionPane.showMessageDialog(userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
	        return false;  // Return false if TM input is empty
	    }
	}

    public boolean isValidModel(String model) {
    	int modelCharCount = 0;
    	String message;
    	
        // Validate binary format
        if (!(model.matches("[01 ]+"))){
        	message = "Error: The Turing Machine model must be binary.";
            JOptionPane.showMessageDialog(userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate minimum two states
        String[] parts = model.split(" ");
        int numberOfParts = parts.length;
        if (numberOfParts < 2) {
            message = "Error: The Turing Machine must have at least two states.";
            JOptionPane.showMessageDialog(userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate five-unit sequence
        for (char c : model.toCharArray()) {
            if (c != ' ') {
            	modelCharCount++;
            }
        }
        
        if (!(modelCharCount % 5 == 0)) {
        	 message = "Error: The Turing Machine model must be defined with a five-unit sequence.";
            JOptionPane.showMessageDialog( userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
    public void errorWindow(String message) {
         JOptionPane.showMessageDialog(userFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

