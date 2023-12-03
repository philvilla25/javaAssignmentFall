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
        info = new JTextArea(10, 20);
        sp = new JScrollPane(info);

        // added action listener for start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            startConnection();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        return null;
                    }
                };

                worker.execute();
            }
        });

        // added action listener for end button
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        	
            String clientHostName = "Client " + clientCounter;

            out = new PrintWriter(clientSocket.getOutputStream(), true); 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            logger.log(Level.INFO, "Accepted connection from {0}", clientSocket.getInetAddress());
            info.append(clientHostName + " connected from: " + clientSocket.getInetAddress() + "\n");
            
            String request = in.readLine(); // get client request
            String configData = null;
            System.out.println("Awa request" + request);
            String[] parts = request.split(turingMachine_Config.PROTOCOL_SEPARATOR); // divide request into parts
            
            int clientId = clientCounter;
            connectedClients.add(clientId);  // Add the client ID to the list of connected clients
            
            String protocolId = parts[1]; // extract protocol id
            if (parts.length > 2) {  // Check if there is additional data
                configData = parts[2];
            }
            
            switch (protocolId) {
            case turingMachine_Config.PROTOCOL_END:
                // Handle ending execution
            	 stopClientConnection(clientId);
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
            e.printStackTrace();
        }
    
    }
       
    /**
     * Method Name: stopClientConnection(int clientId)
     * @param clientId Id of Client to stop
     */
    private void stopClientConnection(int clientId) {
    	System.out.println(clientCounter);
        connectedClients.remove(Integer.valueOf(clientId));    // Remove the client from the list of connected clients
        logger.log(Level.INFO, "Client {0} disconnected", clientId);  // Log the termination of the client connection
        int remainingClients = connectedClients.size(); // Update the number of remaining connected clients
        clientCounter-- ; // decrement number of clients
        info.append("Remaining connected clients: " + remainingClients + "\n");
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
