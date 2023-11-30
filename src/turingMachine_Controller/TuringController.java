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
	   // turingMachine.startConnection(2); // 2  represents connection
		turingMachine.connectToServer();
	}
	
	public void handleSendButtonClick() {
		//turingMachine.startConnection(1); // 1 represents the "SEND_CONFIG" operation
	}

	public void handleReceiveButtonClick() {
	   // turingMachine.startConnection(0); // 0 represents the "GET_CONFIG" operation
	}

	public void handleRunButtonClick() {
		TuringView tm = new TuringView();
    	tm.tmWindow();
	}
}
