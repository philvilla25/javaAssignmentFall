package turingMachine_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private int port;
	static int DEFAULT_PORT = 12345;

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
		
		startButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
						startConnection();
					} catch (IOException e1) {
						//JOptionPane.showMessageDialog(serverFrame, "Using default port number");
					}
	            }
	    });
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
	
	private boolean isValidPort(int port) {
	        return port >= 10000 && port <= 65535;
	 }
	
	public void startConnection() throws IOException {
	  try {
		  String portInput = portText.getText();
	        // Check if the input is not empty
	        if (!portInput.isEmpty()) {
	            // Parse the input as an integer if it's not empty
	            port = Integer.parseInt(portInput);
	        } else {
	            // Use the default value if the input is empty
	            port = DEFAULT_PORT;
	        }
	        
        if (isValidPort(port)) {
        	 ServerSocket serverSocket = new ServerSocket(port);
        	 System.out.println("Start button");
             System.out.println("Port= " + port);
             
             Socket clientSocket = serverSocket.accept();
             System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
          /* Now you can handle incoming connections and implement your server logic
             while (true) {
                 Socket clientSocket = serverSocket.accept();
                 // Handle the client connection (implement your logic here)
             }*/
        }
	  }catch (NumberFormatException | IOException ex) {
          JOptionPane.showMessageDialog(serverFrame, this, "Error: " + ex.getMessage(), 0);
	  }
	}
}
