package turingMachine_Main;

import javax.swing.*;

import turingMachine_View.TuringView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class turingMachine_Server {
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
    private static final Logger logger = Logger.getLogger(turingMachine_Server.class.getName());
    private ExecutorService executorService;
    private volatile boolean serverRunning = false;
    
   /* static final String PROTOCOL_SEPARATOR = "#";
    static final String PROTOCOL_END = "P0";
    static final String PROTOCOL_SENDMODEL = "P1";
    static final String PROTOCOL_RECVMODEL = "P2";
    static String DEFAULT_USER = "User1";
    static String DEFAULT_ADDR = "localhost";
    static int DEFAULT_PORT = 12345;*/
    
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

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use SwingWorker to perform the server startup in the background
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

                // Execute the SwingWorker
                worker.execute();
            }
        });
        
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopConnection();
            }
        });
    }

    public void serverWindow() {
        try {
            serverFrame.setTitle("Turing Machine Server");
            serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            serverFrame.setSize(559, 370);
            serverFrame.setResizable(false);

            // Top Panel
            ImageIcon banner = new ImageIcon(bannerName);
            JLabel bannerLabel = new JLabel(banner);
            topPanel.add(bannerLabel);

            // button Panel
            buttonPanel.add(portLabel);
            buttonPanel.add(portText);
            buttonPanel.add(startButton);
            buttonPanel.add(modelButton);
            buttonPanel.add(finalizesOption);
            buttonPanel.add(finalizesLabel);
            buttonPanel.add(endButton);

            // main Panel
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(sp, BorderLayout.CENTER);

            // Make the frame visible
            serverFrame.setLayout(new BorderLayout());
            serverFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
            serverFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
            serverFrame.getContentPane().add(mainPanel, BorderLayout.SOUTH);
            serverFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidPort(int port) {
        return port >= 10000 && port <= 65535;
    }

    public void startConnection() throws IOException {
        String portInput = portText.getText();
        // Check if the input is not empty
        if (!portInput.isEmpty()) {
            // Parse the input as an integer if it's not empty
            port = Integer.parseInt(portInput);
        } else {
            // Use the default value if the input is empty
            port = turingMachine_Config.DEFAULT_PORT;
        }

        if (isValidPort(port)) {
            serverRunning = true;
            ServerSocket serverSocket = new ServerSocket(port);
            executorService = Executors.newFixedThreadPool(10);

            logger.log(Level.INFO, "Server started on port {0}", port);

            while (serverRunning) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> handleClientConnection(clientSocket));
            }

            // Close resources after the server stops
            serverSocket.close();
            executorService.shutdown();
            logger.log(Level.INFO, "Server stopped.");
        }
    } 

    private void handleClientConnection(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            logger.log(Level.INFO, "Accepted connection from {0}", clientSocket.getInetAddress());
            info.append("Client connected from: " + clientSocket.getInetAddress() + "\n");

            // Handle the client connection (implement your logic here)
            String request = in.readLine();
            String[] parts = request.split(turingMachine_Config.PROTOCOL_SEPARATOR);
            String clientId = parts[0];
            String protocolId = parts[1];
            
            // Check if there is additional data
            if (parts.length > 2) {
                String additionalData = parts[2];
                // Process additional data as needed
            }
            
            switch (protocolId) {
            case turingMachine_Config.PROTOCOL_END:
                // Handle ending execution
            	stopConnection();
                break;
            case turingMachine_Config.PROTOCOL_SENDMODEL:
                // Handle sending game configuration to server
            	receiveConfigFromClient();
                break;
            case turingMachine_Config.PROTOCOL_RECVMODEL:
            	sendConfigurationToClient();
            	break;
            }

            // Close the resources
            out.close();
            in.close();
            clientSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void stopConnection() {
        try {
            if (serverRunning) {
                serverRunning = false;
                executorService.shutdownNow();
                logger.log(Level.INFO, "Server stopped.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String sendConfigurationToClient() {
        // TODO: Implement this method
        return null;
    }

    private String receiveConfigFromClient() {
        // TODO: Implement this method
        return null;
    }

    public static void main(String[] args) {
    	turingMachine_Server server = new turingMachine_Server();
        server.serverWindow();
    }
}
