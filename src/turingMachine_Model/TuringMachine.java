package turingMachine_Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
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
	private boolean connection = false;
    public TuringMachine(turingMachine_User turingMachine_Client) {
        this.turingMachine_Client = turingMachine_Client;
    }

    public boolean connectToServer() { 
    	turingMachine_Client.setConfig();
		   try {
			   	
		        String ip = turingMachine_Client.getServer();
		        int port = turingMachine_Client.getPort();
		        
		        clientSocket = new Socket(ip, port);
		        
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
		        connection = true;
		   	} catch (ConnectException e) {
			    String message = "Connection refused. The server may not be running or unreachable.";
			    turingMachine_Client.errorWindow(message); 
		    } catch (UnknownHostException e) {
		        // Log or handle the UnknownHostException
		        e.printStackTrace();
		    } catch (IOException e) {
		        // Log or handle the IOException
		        e.printStackTrace();
		    }	
	   return connection;
	}

    
    public void sendConfigToServer(String tmModel) {
    	try {
	        // Send game configuration message to the server
	        out.println( clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_SENDMODEL + turingMachine_Config.PROTOCOL_SEPARATOR + tmModel);
	    }catch(NullPointerException e) {
	    	 String message = "Error: The Client must be connected to the sever";
	    	 turingMachine_Client.errorWindow(message); 
		}
    }
    
    public void receiveConfigFromServer() {
        try {
        	// send message to server requesting for game configuration
            out.println(clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_RECVMODEL + turingMachine_Config.PROTOCOL_SEPARATOR);
            // receive game configuration
			String configFromServer = in.readLine();
			  if (configFromServer.isEmpty()) {
		            turingMachine_Client.getInfo().append("No Turing Machine configuration in the server yet");
		        } else {
		            turingMachine_Client.getInfo().append("Received Turing Machine configuration: " + configFromServer);
		            turingMachine_Client.setTmModel(configFromServer);
		            turingMachine_Client.getTmText().setText(configFromServer);
		        }
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			String message = "Error: The Client must be connected to the sever";
			turingMachine_Client.errorWindow(message);
		}
        
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
