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
	}
	
	public void handleConnectButtonClick() {
		if(turingMachine.connectToServer()) {		
				turingMachine_Client.getConnectButton().setEnabled(false);		
		}
	}
	
	public void handleSendButtonClick() {
		turingMachine_Client.validateTm();
		turingMachine.sendConfigToServer(turingMachine_Client.getTmModel());
	}

	public void handleReceiveButtonClick() {
		turingMachine.receiveConfigFromServer();
	}

	public void handleRunButtonClick() {
		turingMachine_Client.validateTm();
		TuringView machine= new TuringView();
    	machine.tmWindow(turingMachine_Client.getTmModel());
	}
}
