/**
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
package turingMachine_Main;

/**
 * Class Name: turingMachine_Config
 */
public class turingMachine_Config {
	/**PROTOCOL SEPERATOR**/
	public static final String PROTOCOL_SEPARATOR = "#";
	/**PROTOCOL END **/
	public static final String PROTOCOL_END = "P0";
	/**PROTOCOL SENDMODEL**/
	public static final String PROTOCOL_SENDMODEL = "P1";
	/**PROTOCOL RECVMODEL**/
	public static final String PROTOCOL_RECVMODEL = "P2";
	/**DEFAULT USER**/
	public static String DEFAULT_USER = "User";
	/**DEFAULT ADDRESS**/
	public static String DEFAULT_ADDR = "localhost";
	/**DEFAULT PORT**/
	public static int DEFAULT_PORT = 12345;
	/**DEFAULT TAPE **/
	public static String DEFAULT_TAPE = "00000000000000000000";
	
	private int tmModel;
	private int tape;
	
	/**
	 * Constructor 
	 */
	public turingMachine_Config() {}
	
	/**
	 * Getter for TM Model
	 * @return TM Model
	 */
	public int getTmModel() {
		return tmModel;
	}

	/**
	 * Setter for TM Model
	 * @param tmModel TM Model
	 */
	public void setTmModel(int tmModel) {
		this.tmModel = tmModel;
	}


	/**
	 * Getter for tape
	 * @return tape
	 */
	public int getTape() {
		return tape;
	}

	/**
	 * Setter for tape
	 * @param tape tape
	 */
	public void setTape(int tape) {
		this.tape = tape;
	}

	
}
