package turingMachine_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private PrintWriter out;
	private BufferedReader in;
	private static final Logger logger = Logger.getLogger(turingMachine_Server.class.getName());
	

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
        	 logger.log(Level.INFO, "Server started on port {0}", port);
        	 
        	 System.out.println("Start button\n");
        	 System.out.println("Port= " + port);
        	 /*info.append("Start button\n");
        	 info.append("Port= " + port); 
        	 serverFrame.repaint();
        	 serverFrame.revalidate();*/

             while (true) {
                 Socket clientSocket = serverSocket.accept();
                 out = new PrintWriter(clientSocket.getOutputStream(), true);
                 in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 
                 logger.log(Level.INFO, "Accepted connection from {0}", clientSocket.getInetAddress());
                 info.append("Client connected from: " + clientSocket.getInetAddress() + "\n");
                 // Handle the client connection (implement your logic here)
                 String request = in.readLine();
                 if ("GET_CONFIG".equals(request)) {
                	    // Handle the request to get the configuration
                	    String configuration = getConfigurationFromServer();
                	    out.println(configuration);
                 } else if  ("SEND_CONFIG".equals(request)){
                	 String configuration = in.readLine();
                	    // Handle other types of requests
                 }else if  ("RUN_MACHINE".equals(request)) {
                	 ///handle run machine
                 }
                 // Create a new thread for each client
             }
        }
	  }catch (IOException ex) {
          logger.log(Level.SEVERE, "Error in server: {0}", ex.getMessage());
          // Handle or rethrow the exception as needed
	  }catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(serverFrame, this, "Error: " + ex.getMessage(), 0);
	  }
	}

	private String getConfigurationFromServer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String sendConfigurationToServer() {
		// TODO Auto-generated method stub
		return null;
	}
}
