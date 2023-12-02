package turingMachine_Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
    private String tm;

	private static final Logger logger = Logger.getLogger(turingMachine_Server.class.getName());
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private volatile boolean serverRunning = false;
    private int clientCounter = 0;
    
    private PrintWriter out;
    private BufferedReader in;

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

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopConnection();
            }
        });
    }

    public String getTm() {
 		return tm;
 	}

 	public void setTm(String tm) {
 		this.tm = tm;
 	}
 	
    public void serverWindow() {
        try {
            serverFrame.setTitle("Turing Machine Server");
            serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            serverFrame.setSize(559, 370);
            serverFrame.setResizable(false);

            ImageIcon banner = new ImageIcon(bannerName);
            JLabel bannerLabel = new JLabel(banner);
            topPanel.add(bannerLabel);

            buttonPanel.add(portLabel);
            buttonPanel.add(portText);
            buttonPanel.add(startButton);
            buttonPanel.add(modelButton);
            buttonPanel.add(finalizesOption);
            buttonPanel.add(finalizesLabel);
            buttonPanel.add(endButton);

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

    private boolean isValidPort(int port) {
        return port >= 10000 && port <= 65535;
    }

    public void startConnection() throws IOException {
        String portInput = portText.getText();
        if (!portInput.isEmpty()) {
            port = Integer.parseInt(portInput);
        } else {
            port = turingMachine_Config.DEFAULT_PORT;
        }

        if (isValidPort(port)) {
            serverRunning = true;
            serverSocket = new ServerSocket(port);
            executorService = Executors.newFixedThreadPool(10);
            info.append("Server Started on port " + port+"...\n");
            logger.log(Level.INFO, "Server started on port {0}", port);

            while (serverRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                   
                    ++clientCounter;
                    executorService.submit(() -> handleClientConnection(clientSocket));
                } catch (SocketException e) {
                    // SocketException will be thrown when the server socket is closed
                    break;
                }
            }

            serverSocket.close();
            executorService.shutdown();
            logger.log(Level.INFO, "Server stopped.");
        }
    }

  

    private void handleClientConnection(Socket clientSocket) {
        try {
        	
            String clientHostName = "Client " + clientCounter;

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            logger.log(Level.INFO, "Accepted connection from {0}", clientSocket.getInetAddress());
            info.append(clientHostName + " connected from: " + clientSocket.getInetAddress() + "\n");
            
            String request = in.readLine();
            String configData = null;
            System.out.println("Awa request" + request);
            String[] parts = request.split(turingMachine_Config.PROTOCOL_SEPARATOR);
            int clientId = clientCounter;
            String protocolId = parts[1];
            // Check if there is additional data
            if (parts.length > 2) {
                configData = parts[2];
            }
            
            switch (protocolId) {
            case turingMachine_Config.PROTOCOL_END:
                // Handle ending execution
            	stopConnection();
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
            
            /*if ("GET_CONFIG".equals(request)) {
                String configuration = getConfigurationFromServer();
                out.println(configuration);
            } else if ("SEND_CONFIG".equals(request)) {
                String configuration = in.readLine();
                // Handle other types of requests
            } else if ("RUN_MACHINE".equals(request)) {
                // Handle run machine

            out.close();
            in.close();
            clientSocket.close();
            }    */  
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
       
public void stopConnection() {
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
   
private void sendConfigurationToClient() {
	String config = getTm();
    if (config != null) {
        out.println(config);
    } else {
        // Handle the case where no configuration is available
        out.println(""); // Send an empty string or a specific message
    }
}

private void receiveConfigFromClient(String configData) {
    	setTm(configData);
    	info.append("TM Model " + configData + " received \n");
        return;
    }

public static void main(String[] args) {
    	turingMachine_Server server = new turingMachine_Server();
        server.serverWindow();
    }
}
