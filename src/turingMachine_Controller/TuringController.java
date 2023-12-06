/*
 * File name: TuringController.java
 * Name: Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * Course: CST 8221 – JAP, Lab Section: 302
 * Assignment: A32 
 * Professor: Daniel Cormier
 * Date: 3rd December, 2024.
 * Compiler: Eclipse IDE for Java Developers - Version: 2022-03 (4.23.0)
 * Purpose: Class that acts as the controller and handles user action for the Turing Machine
 */

package turingMachine_Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import turingMachine_Main.turingMachine_User;
import turingMachine_Model.TuringMachine;
import turingMachine_View.TuringView;

/**
 * Class Name: TuringController 
 * Purpose: Class that acts as the controller and handles user action for the Turing Machine
 */
public class TuringController {
	// instance variables
	private TuringMachine turingMachine;
	private turingMachine_User turingMachine_Client;
	private TuringView view ;
	
	/**
	 * Constructor 
	 * @param turingMachine Turing Machine Model
	 * @param turingMachine_Client Turing Machine Client View
	 * @param view Turing Machine View
	 */
	public TuringController(TuringMachine turingMachine, turingMachine_User turingMachine_Client, TuringView view) {
        this.turingMachine = turingMachine;
        this.turingMachine_Client = turingMachine_Client;
        this.view = view;
        addListener();
    }
	
	/**
	 * Method Name: addListener()
	 */
	public void addListener() {
		// Add listener to connect button
		turingMachine_Client.getConnectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				turingMachine_Client.setInfo("Connecting to server...." + "\n");
				handleConnectButtonClick();			
			}
        });
		
		 // Add listener to send button
	    turingMachine_Client.getSendButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            handleSendButtonClick();
	        }
	    });

	    // Add listener to receive button
	    turingMachine_Client.getReceiveButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            handleReceiveButtonClick();
	        }
	    });

	    // Add listener to run button
	    turingMachine_Client.getRunButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            handleRunButtonClick();
	        }
	    });
	    
	    // Add listener to validate button
	    turingMachine_Client.getValidateButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	handleValidateButtonClick();
	        }
	    });
	    
	    // Add listener to run button
	    view.getRunButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//handleRunTuringMachine();
	        }
	    });
	    
	    // Add listener to end button
	    turingMachine_Client.getEndButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            handleEndButtonClick();
	        }
	    });
	}
	
	/**
	 * Method Name: handleEndButtonClick()
	 */
    public void handleEndButtonClick() {
    	turingMachine.endExecution();	
	}

//	public void handleRunTuringMachine() {
//		turingMachine.startTuringMachine();	
//	}

    /**
	 * Method Name:  handleConnectButtonClick()
	 */
	public void handleConnectButtonClick() {
		if(turingMachine.connectToServer()) { // check if it connects to sever successfully		
				turingMachine_Client.getConnectButton().setEnabled(false);	
				turingMachine_Client.setInfo("Connected to server \n" + "Client" + turingMachine_Client.getUserName() + "\n");
				turingMachine_Client.getUser();
		}else { // if not, output message
			turingMachine_Client.setInfo("Failed to connect to server" + "\n");
		}
	}
	
	 /**
	  * Method Name: handleSendButtonClick()
	  */
	public void handleSendButtonClick() {
		turingMachine.validateTm(); // validate TM Model
		turingMachine.sendConfigToServer(turingMachine_Client.getTmModel()); // send config to server
	}

	/**
	 * Method Name: handleReceiveButtonClick()
	 */
	public void handleReceiveButtonClick() {
		turingMachine.receiveConfigFromServer();
	}

	/**
	 * Method Name: handleValidateButtonClick()
	 */
	public void handleValidateButtonClick() {
		if(turingMachine.validateTm()) { // validate TM model
			turingMachine_Client.setInfo("TM validated");
		}
		else {
			turingMachine_Client.setInfo("TM incorrect");
		}
	}
	
	/**
	 * Method Name: handleRunButtonClick()
	 */
	public void handleRunButtonClick() {
		TuringView machine= new TuringView(turingMachine_Client);
		if(turingMachine.validateTm()) {	
	    	machine.tmWindow(turingMachine_Client.getTmModel()); // run if the TM Model is valid
		}	
	}
}
