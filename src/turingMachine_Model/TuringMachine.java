/*
 * File name: TuringMachine.java
 * Name: Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * Course: CST 8221 – JAP, Lab Section: 302
 * Assignment: A32 
 * Professor: Daniel Cormier
 * Date: 3rd December, 2024.
 * Compiler: Eclipse IDE for Java Developers - Version: 2022-03 (4.23.0)
 * Purpose: Class that acts as the model for the Turing Machine
 */

package turingMachine_Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import turingMachine_Main.turingMachine_Config;
import turingMachine_Main.turingMachine_User;
import turingMachine_View.TuringView;

/**
 * Class Name: Turing Machine
 * Purpose: Model for turing machine
 */
public class TuringMachine {
	
	// instance variables 
	private turingMachine_User turingMachine_Client;
	private TuringView view;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int clientId = 0;
	private boolean connection = false;
    private TuringMachineTuple[] tuples;
	private char[] tape;
	/**
	 * Parameterized Constructor 
	 * @param turingMachine_Client Client Window
	 */
	public TuringMachine(turingMachine_User turingMachine_Client) {
        this.turingMachine_Client = turingMachine_Client;
    }

	/**
	 * Method Name: connectToServer()
	 * @return true if connected properly, false if not
	 */
	public boolean connectToServer() { 
	    // Set client configuration from the GUI
	    turingMachine_Client.setConfig();
	    
	    try {
	        // Retrieve server IP and port from the GUI
	        String ip = turingMachine_Client.getServer(); // get server from gui
	        int port = turingMachine_Client.getPort(); // get port from gui
	        
	        // Establish a client socket connection to the server
	        clientSocket = new Socket(ip, port); // start client socket
	        
	        // Initialize PrintWriter for sending data to the server
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        
	        // Initialize BufferedReader for receiving data from the server
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
	        
	        // Set connection status to true upon successful connection
	        connection = true;
	        
	        // Increment clientId for the connected client
	        clientId++;
	       
	        // Handle exceptions
	    } catch (ConnectException e) {
	        // Handle connection refusal exception
	        String message = "Connection refused. The server may not be running or unreachable.";
	        turingMachine_Client.errorWindow(message); 
	    } catch (UnknownHostException e) {
	        // Handle unknown host exception
	        String message = "Unknown host. Please check the server address.";
	        turingMachine_Client.errorWindow(message);
	    } catch (IOException e) {
	        // Handle IOException during connection
	        String message = "IOException occurred while connecting to the server.";
	        turingMachine_Client.errorWindow(message);
	    }	
	    
	    // Return the connection status
	    return connection;
	}


    /**
     * Method Name: sendConfigToServer(String tmModel)
     * @param tmModel String turing machine model
     */
    public void sendConfigToServer(String tmModel) {
    	try {
	        // Send game configuration message to the server
	        out.println(clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_SENDMODEL + turingMachine_Config.PROTOCOL_SEPARATOR + tmModel);
	    }catch(NullPointerException e) { // handle exception
	    	 String message = "Error: The Client must be connected to the sever";
	    	 turingMachine_Client.errorWindow(message); 
		}
    }
    
    /**
     * Method Name: receiveConfigFromServer()
     */
    public void receiveConfigFromServer() {
        try {
            // Send message to server requesting game configuration
            out.println(clientId + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_RECVMODEL + turingMachine_Config.PROTOCOL_SEPARATOR);

            // Use BufferedReader to read from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Check if data is ready to be read
            if (in.ready()) {
                // Read the line from the server (blocking operation)
                String configFromServer = in.readLine();

                if (configFromServer != null) {
                    if (configFromServer.isEmpty()) {
                        turingMachine_Client.getInfo().append("No Turing Machine configuration in the server yet \n");
                    } else {
                        turingMachine_Client.getInfo().append("Received Turing Machine configuration: " + configFromServer);
                        turingMachine_Client.setTmModel(configFromServer);
                        turingMachine_Client.getTmText().setText(configFromServer);
                    }
                } else {
                    // No data available, handle accordingly
                    turingMachine_Client.getInfo().append("No data available from the server \n");
                }
            } else {
                // No data ready, handle accordingly
                turingMachine_Client.getInfo().append("No data ready in the server stream \n");
            }

//            // Close the input stream and the socket to solve blocking problem
//            in.close();
//            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            String message = "Error: The Client must be connected to the server";
            turingMachine_Client.errorWindow(message);
        }
    }




    /**
     * Method Name: validateTm()
     * Purpose: Validate if the turing machine model is valid
     * @return true if its valid, false if not
     */
    public boolean validateTm() {
		JTextField tmInput = turingMachine_Client.getTmText();
	    // Check if the input is not empty
	    if (!tmInput.getText().isEmpty()) { // check if the TM has been entered 
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
    
    /**
     * Method Name: isValidModel(String model)
     * Purpose: Validate if the turing machine model is valid
     * @param model TM Model is entered
     * @return true if it value, false if not
     */
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
    
    /**
     * Method Name: endExecution() 
     */
    public void endExecution() {
        // Send end execution message to the server
        //out.println(turingMachine_Client.getUserName() + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_END);
        stopClientConnection();
    }
    
    /**
     * Method Name: stopClientConnection()
     */
    public void stopClientConnection() {
        // Send end execution message to the server
        out.println(turingMachine_Client.getUserName() + turingMachine_Config.PROTOCOL_SEPARATOR + turingMachine_Config.PROTOCOL_END);
        turingMachine_Client.getInfo().append("Disconnected from server! \n");
        // Close the resources
        try {
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Inner class to define a tuple
     */
    public static class TuringMachineTuple {
        private int startState;
        private char inputSymbol;
        private int nextState;
        private char writeSymbol;
        private int moveDirection;


        /**
         * Constructor for tuple
         * @param startState Symbol that says what state this tuple belongs t
         * @param inputSymbol Symbol for input
         * @param nextState Symbol for the next state
         * @param writeSymbol Symbol to get written on the tape
         * @param moveDirection  Symbol to directs the tape drive
         */
        public TuringMachineTuple(int startState, char inputSymbol, int nextState, char writeSymbol, int moveDirection) {
            this.startState= startState;
            this.inputSymbol = inputSymbol;
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.moveDirection = moveDirection;
        }

        /**
         * Getter for start state
         * @return  start state
         */
        public int getStartState() {
            return startState;
        }

        /**
         * Getter for input symbol
         * @return input symbol
         */
        public char getInputSymbol() {
            return inputSymbol;
        }

        /**
         * Getter for next state 
         * @return  next state 
         */
        public int getNextState() {
            return nextState;
        }

        /**
         * Getter for write symbol
         * @return write symbol
         */
        public char getWriteSymbol() {
            return writeSymbol;
        }

        /**
         * Getter for direction to move
         * @return direction to move
         */
        public int getMoveDirection() {
            return moveDirection;
        }
    }

    /**
     * Method Name:  initializeTuples(String tmModel
     * @param tmModel TM Model
     */
    public void initializeTuples(String tmModel) {
        // Split the tmModel string by spaces
        String[] tuplePairs = tmModel.split(" ");

        // Initialize the array to hold the tuples
        tuples = new TuringMachineTuple[tuplePairs.length];

        // Populate the array with tuples
        for (int i = 0; i < tuplePairs.length; i++) {
            // Split each pair into individual digits
            String pair = tuplePairs[i];
            
            // Assuming each pair has exactly 5 digits (adjust if needed)
            if (pair.length() != 5) {
                // Handle error, invalid pair format
                System.out.println("Invalid pair format at index " + i + ": " + pair);
                return;
            }

            // assigns values for the TuringMachineTuple 
            int startState = Character.getNumericValue(pair.charAt(0));
            char inputSymbol = pair.charAt(1);
            int nextState = Character.getNumericValue(pair.charAt(2));
            char writeSymbol = pair.charAt(3);
            int moveDirection = Character.getNumericValue(pair.charAt(4));

            // Create a new TuringMachineTuple and store it in the array
            tuples[i] = new TuringMachineTuple(startState, inputSymbol, nextState, writeSymbol, moveDirection);
        }
    }

	
    /**
     * Method Name: getTape(TuringView view)
     * @param view Turing Machine View
     */
	public void getTape(TuringView view) {
		if (view.getTapeText().getText()!= " ") { // check if user entered tape 
			tape = view.getTapeText().getText().toCharArray(); // get tape entered by user
		}else {
			tape = "000000000000000000000000000000000000000000000000".toCharArray(); // use default tape
		}	
	}
	
	/**
	 * Method Name: displayTape(int tapePos, TuringView view)
	 * @param tapePos Position on the tape
	 * @param view Turing Machine View
	 */
	public void displayTape(int tapePos, TuringView view) {
		 for (int i = 0; i < tape.length; i++) {
			    char symbol = tape[i];
			    if (i == tapePos) {
			    	view.getInfo().append("[" + symbol + "]");
			    } else {
			    	view.getInfo().append(String.valueOf(symbol));
			    }
		 }
	}
	
	/**
	 * Method Name: findTuple(int state, char symbol) 
	 * @param state State currently in
	 * @param symbol Symbol on the head position of the tape
	 * @return Tuple to be used to determine next transition
	 */
	private TuringMachineTuple findTuple(int state, char symbol) {
	    for (TuringMachineTuple tuple : tuples) { // iterate through tuples array
	        if (tuple.getStartState() == state && tuple.getInputSymbol() == symbol) { // check for state and symbol
	            return tuple;
	        }
	    }
	    return null;  // Return null where no matching tuple is found 
	}

	/**
	 * Method Name:startTuringMachine(TuringView view) 
	 * @param view Turing Machine View
	 */
	public void startTuringMachine(TuringView view) {
	    getTape(view); // get tape 
	    int step = 0;
	    int tapeLength = tape.length;
	    int tapePos = tapeLength / 2; // start at the middle of the tape 
	    int currentState = 0;

	    String tmModel = view.getTmText().getText(); // get TM Model
	    view.getInfo().append("Card:" + tmModel + "\n");
	    initializeTuples(tmModel); // initialize tuples

	    view.getInfo().append("Initial tape (head position between brackets)\n");
	    displayTape(tapePos, view); // write initial tape
	    view.getInfo().append("\n"); // new line;
	    view.getInfo().append("Game Started\n");

	    TuringMachineTuple currentTuple = findTuple(currentState, tape[tapePos]); // find tuple
	    
	    while (currentTuple != null) { // check if tuple is null (FINAL STATE)
	        // Apply the transition rules
	        tape[tapePos] = currentTuple.getWriteSymbol();
	        
	        // Update the tape position based on the move direction
	        if (currentTuple.getMoveDirection() == 0) {
	            tapePos--;  // Move to the left
	        } else {
	            tapePos++;  // Move to the right
	        }

	        // Update the current state
	        currentState = currentTuple.getNextState();

	        // Display the current step and tape
	        view.getInfo().append("Step: " + step + " Tapepos: " + tapePos + "\n");
	        displayTape(tapePos, view);
	        view.getInfo().append("\n" );

	        step++; // increase step

	        // Find the next tuple for the current state and symbol under the tape head
	        if (tapePos > 0 && tapePos < tape.length) {
	            currentTuple = findTuple(currentState, tape[tapePos]);
	        } else {
	            // Exit the loop if tapePos is out of bounds
	            currentTuple = null;
	        }
	    }
	    view.getInfo().append("Final Tape Config is: \n" );
	    displayTape(tapePos, view); // display final tape
	    view.getInfo().append("\n" );
	}


   
    
}


