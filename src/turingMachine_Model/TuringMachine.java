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
	
    public TuringMachine(turingMachine_User turingMachine_Client) {
        this.turingMachine_Client = turingMachine_Client;
    }

    public void startConnection(int operationType) { 
	    try {
	        turingMachine_Client.sendConfig();
	        String ip = turingMachine_Client.getServer();
	        int port = turingMachine_Client.getPort();
	        
	        clientSocket = new Socket(ip, port);
	        
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        
	        
	        switch (operationType) {
            case 0:
                // Send a request to get the Turing Machine configuration
                out.println("GET_CONFIG");
                break;
            case 1:
                // Send a Turing Machine configuration to the server
                out.println("SEND_CONFIG");
                // Add code to send the configuration
                break;
            case 2:
                // Send a request to run the Turing Machine
                out.println("RUN_MACHINE");
                // Add code to handle running the Turing Machine
                break;
            default:
                System.out.println("Invalid operation type");
             }
	        
            // Receive and process the response
            String response = in.readLine();
            //System.out.println("Received from server (" + operationType + "): " + response);
	        
	    } catch (UnknownHostException e) {
	        // Log or handle the UnknownHostException
	        e.printStackTrace();
	    } catch (IOException e) {
	        // Log or handle the IOException
	        e.printStackTrace();
	    }
	}

}
