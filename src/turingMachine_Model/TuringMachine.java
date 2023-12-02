package turingMachine_Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import turingMachine_Main.turingMachine_Config;
import turingMachine_Main.turingMachine_User;
import turingMachine_View.TuringView;

public class TuringMachine {
	private turingMachine_User turingMachine_Client;
	private TuringView view;
	//private turingMachine_Server turingMachine_Server;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int clientId;
	private boolean connection = false;
    private TuringMachineTuple[] tuples;
	private char[] tape;
	
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
		    	String message = "Unknown host. Please check the server address.";
		    	turingMachine_Client.errorWindow(message);
		    } catch (IOException e) {
		    	String message = "IOException occurred while connecting to the server.";
		        turingMachine_Client.errorWindow(message);
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
            // Send message to server requesting game configuration
            out.println(clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_RECVMODEL + turingMachine_Config.PROTOCOL_SEPARATOR);

            // Use BufferedReader to read from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Check if data is ready to be read
            if (in.ready()) {
                // Read the line from the server
                String configFromServer = in.readLine();

                if (configFromServer.isEmpty()) {
                    turingMachine_Client.getInfo().append("No Turing Machine configuration in the server yet");
                } else {
                    turingMachine_Client.getInfo().append("Received Turing Machine configuration: " + configFromServer);
                    turingMachine_Client.setTmModel(configFromServer);
                    turingMachine_Client.getTmText().setText(configFromServer);
                }
            } else {
                // No data available, handle accordingly
                turingMachine_Client.getInfo().append("No data available from the server");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            String message = "Error: The Client must be connected to the server";
            turingMachine_Client.errorWindow(message);
        }
    }

    
    public boolean validateTm() {
		JTextField tmInput = turingMachine_Client.getTmText();
	    // Check if the input is not empty
	    if (!tmInput.getText().isEmpty()) {
	        if (isValidModel(tmInput.getText())) {
	        	turingMachine_Client.setTmModel(tmInput.getText());
	            return true;  // Return true if the TM model is valid
	        } else {
	            return false;  // Return false if the TM model is invalid
	        }
	    } else {
	        String message = "Enter valid TM Model";
	        JOptionPane.showMessageDialog(turingMachine_Client.getUserFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	        return false;  // Return false if TM input is empty
	    }
	}
    
    public boolean isValidModel(String model) {
    	int modelCharCount = 0;
    	String message;
    	
        // Validate binary format
        if (!(model.matches("[01 ]+"))){
        	message = "Error: The Turing Machine model must be binary.";
            JOptionPane.showMessageDialog(turingMachine_Client.getUserFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate minimum two states
        String[] parts = model.split(" ");
        int numberOfParts = parts.length;
        if (numberOfParts < 2) {
            message = "Error: The Turing Machine must have at least two states.";
            JOptionPane.showMessageDialog(turingMachine_Client.getUserFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate five-unit sequence
        for (char c : model.toCharArray()) {
            if (c != ' ') {
            	modelCharCount++;
            }
        }
        
        if (!(modelCharCount % 5 == 0)) {
        	 message = "Error: The Turing Machine model must be defined with a five-unit sequence.";
            JOptionPane.showMessageDialog( turingMachine_Client.getUserFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
   
    public static class TuringMachineTuple {
        private int state;
        private char inputSymbol;
        private int nextState;
        private char writeSymbol;
        private int moveDirection;

        // Constructor
        public TuringMachineTuple(int state, char inputSymbol, int nextState, char writeSymbol, int moveDirection) {
            this.state = state;
            this.inputSymbol = inputSymbol;
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.moveDirection = moveDirection;
        }

        // Getters
        public int getState() {
            return state;
        }

        public char getInputSymbol() {
            return inputSymbol;
        }

        public int getNextState() {
            return nextState;
        }

        public char getWriteSymbol() {
            return writeSymbol;
        }

        public int getMoveDirection() {
            return moveDirection;
        }
    }

	public void initializeTuples(String tmModel) {
	    String[] tupleStrings = tmModel.split(" ");

	    // Ensure that the number of characters in the model is a multiple of 5
	    if (tupleStrings.length % 5 != 0) {
	        // Handle error, invalid model
	        return;
	    }

	    int numberOfTuples = tupleStrings.length / 5;

	    // Initialize the array to hold the tuples
	    tuples = new TuringMachineTuple[numberOfTuples];

	    // Populate the array with tuples
	    for (int i = 0; i < numberOfTuples; i++) {
	        int startIndex = i * 5;
	        int state = Integer.parseInt(tupleStrings[startIndex]);
	        char inputSymbol = tupleStrings[startIndex + 1].charAt(0);
	        int nextState = Integer.parseInt(tupleStrings[startIndex + 2]);
	        char writeSymbol = tupleStrings[startIndex + 3].charAt(0);
	        int moveDirection = Integer.parseInt(tupleStrings[startIndex + 4]);

	        // Create a new TuringMachineTuple and store it in the array
	        tuples[i] = new TuringMachineTuple(state, inputSymbol, nextState, writeSymbol, moveDirection);
	        

            // Display the extracted components
            System.out.println("Tuple " + (i + 1) + ":");
            System.out.println("State: " + state);
            System.out.println("Input Symbol: " + inputSymbol);
            System.out.println("Next State: " + nextState);
            System.out.println("Write Symbol: " + writeSymbol);
            System.out.println("Move Direction: " + moveDirection);
            System.out.println();
	    }
	}
	
	public void getTape() {
		if (view.getTapeText().getText()!= " ") {
			tape = view.getTapeText().getText().toCharArray();
		}else {
			tape = "000000000000000000000000000000000000000000000000".toCharArray();
		}	
	}
	
	public void displayTape(int tapePos) {
		 for (int i = 0; i < tape.length; i++) {
			    char symbol = tape[i];
			    if (i == tapePos) {
			        turingMachine_Client.getInfo().append("[" + symbol + "]");
			    } else {
			        turingMachine_Client.getInfo().append(String.valueOf(symbol));
			    }
		 }
	}
	
	public void startTuringMachine() {
		 getTape();
		 int step = 0;
		 int tapeLength = tape.length;
		 int tapePos = tapeLength / 2;
		 int finalState = 0;
		 
		 String tmModel = turingMachine_Client.getTmModel();
		 turingMachine_Client.getInfo().append("Card:" + tmModel + "\n");
		 initializeTuples(tmModel);
		 
		 turingMachine_Client.getInfo().append("Initial tape (head position between brackets)\n");
		 displayTape(tapePos);// write initial tape
		 turingMachine_Client.getInfo().append("\n"); //new line;
		 turingMachine_Client.getInfo().append("Game Started\n");
		 while (finalState != 0) {
			 turingMachine_Client.getInfo().append("Step: " + step + " Tapepos: " + tapePos);
			 displayTape(tapePos);
		 	
		}
	}

   
    
}


