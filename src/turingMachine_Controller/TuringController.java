package turingMachine_Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import turingMachine_Main.turingMachine_User;
import turingMachine_Model.TuringMachine;
import turingMachine_View.StateView;
import turingMachine_View.TapeView;

public class TuringController {
	private TuringMachine turingMachine;
	private turingMachine_User turingMachine_Client;
	private TapeView tapeView;
	private StateView stateView;
	
	public TuringController(TuringMachine turingMachine, turingMachine_User turingMachine_Client, TapeView tapeView, StateView stateView) {
        this.turingMachine = turingMachine;
        this.turingMachine_Client = turingMachine_Client;
        this.tapeView = tapeView;
        this.stateView = stateView;
        addListener();
    }
	
	public void addListener() {
		// Add listener to connect button
		turingMachine_Client.getConnectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	
	public void handleConnectButtonClick() {
		turingMachine.startConnection();
	}
}
