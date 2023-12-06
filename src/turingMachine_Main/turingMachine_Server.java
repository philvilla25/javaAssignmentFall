/**
 * File name: turingMachine_Server.java
 * Name: Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * Course: CST 8221 – JAP, Lab Section: 302
 * Assignment: A32 
 * Professor: Daniel Cormier
 * Date: 3rd December, 2024.
 * Compiler: Eclipse IDE for Java Developers - Version: 2022-03 (4.23.0)
 * Purpose: Class that handles Server implementation
 */
package turingMachine_Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class name: turingMachine_Server 
 * Purpose: Class that handles Server implementation
 */
public class turingMachine_Server {
	// instance variables
    private JFrame serverFrame;
    private JPanel topPanel, buttonPanel, mainPanel;
    private String bannerName = "../resources/tm-server.png";
    private JButton startButton, modelButton, endButton;
    private JLabel portLabel, finalizesLabel;
    private JTextField portText;
    private JCheckBox finalizesOption;
    private JTextArea info;
    private JScrollPane sp;
    private int port;
    private String tm;

	private static final Logger logger = Logger.getLogger(turingMachine_Server.class.getName());
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private volatile boolean serverRunning = false;
    private int clientCounter = 0;
    private ArrayList<Integer> connectedClients = new ArrayList<Integer>();
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Default constructor for initializing attributes
     */
 // Constructor for the public TuringMachine_Server class
    public turingMachine_Server() {
        // Initializing the serverFrame
        serverFrame = new JFrame();
        
        // Creating the topPanel with FlowLayout
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Initializing buttonPanel
        buttonPanel = new JPanel();
        
        // Initializing mainPanel
        mainPanel = new JPanel();
        
        // Creating and initializing Start button
        startButton = new JButton("Start");
        
        // Creating and initializing Model button
        modelButton = new JButton("Model");
        
        // Creating and initializing End button
        endButton = new JButton("End");
        
        // Creating and initializing Port label
        portLabel = new JLabel("Port:");
        
        // Creating and initializing Port text field
        portText = new JTextField(10);
        
        // Creating and initializing Finalizes label
        finalizesLabel = new JLabel("Finalizes");
        
        // Creating and initializing Finalizes checkbox
        finalizesOption = new JCheckBox();
        
        // Creating and initializing JTextArea for info
        info = new JTextArea(10, 20);
        
        // Creating JScrollPane for the info JTextArea
        sp = new JScrollPane(info);

        // Adding action listener for the Start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creating a SwingWorker to perform background tasks
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            // Calling the startConnection method
                            startConnection();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        return null;
                    }
                };

                // Executing the SwingWorker
                worker.execute();
            }
        });

        // Adding action listener for the End button
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calling the stopConnection method
                stopConnection();
            }
        });
    }


    /**
     * Getter for Turing Machine Model
     * @return Turing Machine Model
     */
    public String getTm() {
 		return tm;
 	}

    /**
     * Setter for Turing Machine Model
     * @param tm  Turing Machine Model
     */
 	public void setTm(String tm) {
 		this.tm = tm;
 	}
 	
 	/**
 	 * Method Name:serverWindow()
 	 * Purpose: GUI for server window
 	 */
    public void serverWindow() {
        try {
            serverFrame.setTitle("Turing Machine Server");
            serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            serverFrame.setSize(559, 370);
            serverFrame.setResizable(false);
            serverFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            ImageIcon banner = new ImageIcon(bannerName);
            JLabel bannerLabel = new JLabel(banner);
            topPanel.add(bannerLabel);

            // button panel
            buttonPanel.add(portLabel);
            buttonPanel.add(portText);
            buttonPanel.add(startButton);
            buttonPanel.add(modelButton);
            buttonPanel.add(finalizesOption);
            buttonPanel.add(finalizesLabel);
            buttonPanel.add(endButton);

            // main panel
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(sp, BorderLayout.CENTER);

            serverFrame.setLayout(new BorderLayout());
            serverFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
            serverFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
            serverFrame.getContentPane().add(mainPanel, BorderLayout.SOUTH);
            serverFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method Name: isValidPort(int port) 
     * @param port Port number for server
     * @return true if the port number is valid, and false if not valid
     */
    private boolean isValidPort(int port) {
        return port >= 10000 && port <= 65535; //range of values for ports
    }

    /**
     * Method Name: startConnection() 
     * @throws IOException if port is already connected
     */
    private void startConnection() throws IOException {
        String portInput = portText.getText(); // get port from text box
        
        if (!portInput.isEmpty()) { // checks if port is empty
            port = Integer.parseInt(portInput);
        } else {
            port = turingMachine_Config.DEFAULT_PORT; // use default port instead
        }

        if (isValidPort(port)) {  //checks if port is valid
            serverRunning = true;
            serverSocket = new ServerSocket(port); //start server socket
            executorService = Executors.newFixedThreadPool(10);
            info.append("Server Started on port " + port+"...\n");
            logger.log(Level.INFO, "Server started on port {0}", port);

            while (serverRunning) { // check if server is running 
                try {
                    Socket clientSocket = serverSocket.accept();
                    ++clientCounter; // increment client counter
                    info.append("Number of Clients " + clientCounter + "\n");
                    executorService.submit(() -> handleClientConnection(clientSocket)); //handle client connection
                } catch (SocketException e) {
                    // SocketException will be thrown when the server socket is closed
                    break;
                }
            }

            serverSocket.close(); // close server socket
            executorService.shutdown();
            logger.log(Level.INFO, "Server stopped.");
        }
    }

    /**
     * Method Name: handleClientConnection(Socket clientSocket)
     * @param clientSocket Socket for client
     */
    private void handleClientConnection(Socket clientSocket) {
        try {
            // Generate a unique client host name
            String clientHostName = "Client " + clientCounter;

            // Initialize PrintWriter for sending data to the client
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // Initialize BufferedReader for receiving data from the client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Log accepted connection information
            logger.log(Level.INFO, "Accepted connection from {0}", clientSocket.getInetAddress());
            
            // Update UI information about the connected client
            info.append(clientHostName + " connected from: " + clientSocket.getInetAddress() + "\n");

            // Read the client's request
            String request = in.readLine();
            System.out.println("Received request: " + request);
            
            // Split the request into parts using the protocol separator
            String[] parts = request.split(turingMachine_Config.PROTOCOL_SEPARATOR);

            // Extract client ID and protocol ID from the request
          
            connectedClients.add(clientCounter);  // Add the client ID to the list of connected clients
            String protocolId = parts[1];

            // Check if there is additional data in the request
            String configData = (parts.length > 2) ? parts[2] : null;
            
            // Process the request based on the protocol ID
            switch (protocolId) {
                case turingMachine_Config.PROTOCOL_END:
                    // Handle ending execution
                	String clientUser = parts[0];
                    stopClientConnection(clientUser);
                    break;
                case turingMachine_Config.PROTOCOL_SENDMODEL:
                    // Handle receiving game configuration from client
                    receiveConfigFromClient(configData);
                    break;
                case turingMachine_Config.PROTOCOL_RECVMODEL:
                    // Handle sending game configuration to server
                    sendConfigurationToClient();
                    break;
            }
        } catch (IOException e) {
            // Handle IOException by printing the stack trace
            e.printStackTrace();
        }
    }
       
    /**
     * Method Name: stopClientConnection(int clientId)
     * @param clientId Id of Client to stop
     */
    private void stopClientConnection(String clientUserName) {
    	info.append("Client " + clientUserName + " disconnected \n");
    	clientCounter--;
        out.println("Remaining Clients " + clientCounter + "\n");
        info.append("Remaining connected clients: " + clientCounter + "\n");
    }

    /**
     * Method Name: stopConnection()
     * Purpose: To Stop Server
     */
	private void stopConnection() {
	     try {
	        if (serverRunning) {
	            serverRunning = false;
	            serverSocket.close();
	            executorService.shutdownNow();
	            logger.log(Level.INFO, "Server stopped.");
	            serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            info.append("Server stopped.\n");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
   
	/**
	 * Method Name: sendConfigurationToClient()
	 */
	private void sendConfigurationToClient() {
		String config = getTm(); // get turing machine model
	    if (config != null) {
	        out.println(config); //send to client
	    } else {
	        // Handle the case where no configuration is available
	        out.println(""); // Send an empty string or a specific message
	    }
	}
	
	/**
	 * Method Name: receiveConfigFromClient(String configData)
	 * @param configData Configuration received from client
	 */
	private void receiveConfigFromClient(String configData) {
	    	setTm(configData);
	    	info.append("TM Model " + configData + " received \n"); 
	        return;
	    }
	
	/**
	 * Main class
	 * @param args String arguments
	 */
	public static void main(String[] args) {
	    	turingMachine_Server server = new turingMachine_Server();
	        server.serverWindow();
	 }
}
