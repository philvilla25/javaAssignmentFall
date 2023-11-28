package turingMachine_Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import turingMachine_Main.turingMachine_User;

public class TuringMachine {
	//private Tape tape;
	//private State currentState;
	private turingMachine_User turingMachine_Client;
	//private turingMachine_Server turingMachine_Server;
	private Socket clientSocket;

	 public void startConnection() {
		 turingMachine_Client.sendConfig();
		 String  ip =  turingMachine_Client.getServer();
	     int port = turingMachine_Client.getPort();
	     try {
			clientSocket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
