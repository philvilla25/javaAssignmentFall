import gameOfLife_Controller.GameController;
import gameOfLife_Model.GameModel;
import gameOfLife_View.GameView;


/**
 * Name:Akpoguma Oghenerukevwe and Philogene Villanueva
 * Student Number: 041075624 and 041063813
 * CST8221 A22
 * Date: 11 November, 2023.
 * Professor: Daniel Cormier
 * Compiler: Eclipse IDE for Java Developers - Version: 2023-06 (4.28.0)]
 */
public class Game {

	public static void main(String args) {
		// TODO Auto-generated method stub
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
	    GameController gameController = new GameController(gameModel, gameView);    
	    // Configure and display the initial GUI
	   
	}

}
