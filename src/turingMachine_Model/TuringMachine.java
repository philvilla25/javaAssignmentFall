package turingMachine_Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import turingMachine_Main.turingMachine_Config;
import turingMachine_Main.turingMachine_User;

public class TuringMachine {
	//private Tape tape;
	//private State currentState;
	private turingMachine_User turingMachine_Client;
	//private turingMachine_Server turingMachine_Server;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int clientId;
	
    public TuringMachine(turingMachine_User turingMachine_Client) {
        this.turingMachine_Client = turingMachine_Client;
    }

    public void connectToServer() { 
	   if (turingMachine_Client.setConfig()) {
		   try {
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
	}

    
    public void sendConfigToServer(String configurationData) {
        // Send game configuration message to the server
        out.println( clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_SENDMODEL + turingMachine_Config.PROTOCOL_SEPARATOR + configurationData);
    }
    
    public void receiveConfigFromServer() {
        out.println( clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_RECVMODEL + turingMachine_Config.PROTOCOL_SEPARATOR);
    }
    
    public void endExecution() {
        // Send end execution message to the server
        out.println( clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_END);
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
