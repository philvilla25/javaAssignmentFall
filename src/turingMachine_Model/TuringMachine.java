package turingMachine_Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import turingMachine_Main.turingMachine_User;

public class TuringMachine {
	//private Tape tape;
	//private State currentState;
	private turingMachine_User turingMachine_Client;
	//private turingMachine_Server turingMachine_Server;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	
	static final String PROTOCOL_SEPARATOR = "#";
	static final String PROTOCOL_END = "P0";
	static final String PROTOCOL_SENDMODEL = "P1";
	static final String PROTOCOL_RECVMODEL = "P2";
	private int clientId;
	
    public TuringMachine(turingMachine_User turingMachine_Client) {
        this.turingMachine_Client = turingMachine_Client;
    }

    public void connectToServer() { 
	    try {
	        turingMachine_Client.setConfig();
	        String ip = turingMachine_Client.getServer();
	        int port = turingMachine_Client.getPort();
	        
	        clientSocket = new Socket(ip, port);
	        
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	    } catch (UnknownHostException e) {
	        // Log or handle the UnknownHostException
	        e.printStackTrace();
	    } catch (IOException e) {
	        // Log or handle the IOException
	        e.printStackTrace();
	    }
	}

    
    public void sendConfigToServer(String configurationData) {
        // Send game configuration message to the server
        out.println( clientId + PROTOCOL_SEPARATOR + PROTOCOL_SENDMODEL + PROTOCOL_SEPARATOR + configurationData);
    }
    
    public void receiveConfigFromServer() {
        out.println( clientId + PROTOCOL_SEPARATOR + PROTOCOL_RECVMODEL + PROTOCOL_SEPARATOR);
    }
    
    public void endExecution() {
        // Send end execution message to the server
        out.println( clientId + PROTOCOL_SEPARATOR + PROTOCOL_END);
    }
    
    public void closeConnection() {
        try {
            // Close the resources
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
