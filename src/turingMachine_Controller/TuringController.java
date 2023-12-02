package turingMachine_Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import turingMachine_Main.turingMachine_User;
import turingMachine_Model.TuringMachine;
import turingMachine_View.TuringView;

public class TuringController {
	private TuringMachine turingMachine;
	private turingMachine_User turingMachine_Client;
	private TuringView view ;
	
	public TuringController(TuringMachine turingMachine, turingMachine_User turingMachine_Client,TuringView view) {
        this.turingMachine = turingMachine;
        this.turingMachine_Client = turingMachine_Client;
        this.view = view;
        addListener();
    }
	
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
	    
	    // Add listener to run button
	    turingMachine_Client.getValidateButton().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	handleValidateButtonClick();
	        }
	    });
	}
	
	public void handleConnectButtonClick() {
		if(turingMachine.connectToServer()) {		
				turingMachine_Client.getConnectButton().setEnabled(false);	
				turingMachine_Client.setInfo("Connected to server" + "\n" + turingMachine_Client.getUserName());
				turingMachine_Client.getUser();
		}else {
			turingMachine_Client.setInfo("Failed to connect to server" + "\n");
		}
	}
	
	public void handleSendButtonClick() {
		turingMachine.validateTm();
		turingMachine.sendConfigToServer(turingMachine_Client.getTmModel());
	}

	public void handleReceiveButtonClick() {
		turingMachine.receiveConfigFromServer();
	}

	public void handleValidateButtonClick() {
		if(turingMachine.validateTm()) {
			turingMachine_Client.setInfo("TM validated");
		}
		else {
			turingMachine_Client.setInfo("TM incorrect");
		}
	}
	public void handleRunButtonClick() {
		TuringView machine= new TuringView();
		if(turingMachine.validateTm()) {	
			
	    	machine.tmWindow(turingMachine_Client.getTmModel());
		}
		
	}
}
