/**
 * 
 */
package turingMachine_Main;

/**
 * 
 */
public class turingMachine_Config {
	public static final String PROTOCOL_SEPARATOR = "#";
	public static final String PROTOCOL_END = "P0";
	public static final String PROTOCOL_SENDMODEL = "P1";
	public static final String PROTOCOL_RECVMODEL = "P2";
	static String DEFAULT_USER = "User";
	static String DEFAULT_ADDR = "localhost";
	static int DEFAULT_PORT = 12345;
	
	private int tmModel;
	private int tape;
	
	
	public turingMachine_Config() {}
	
	public int getTmModel() {
		return tmModel;
	}


	public void setTmModel(int tmModel) {
		this.tmModel = tmModel;
	}


	public int getTape() {
		return tape;
	}


	public void setTape(int tape) {
		this.tape = tape;
	}

	
}
