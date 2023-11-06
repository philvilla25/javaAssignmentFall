import gameOfLife_Controller.GameController;
import gameOfLife_Model.GameModel;
import gameOfLife_View.GameView;

/**
 * Game class to initialize all components of the MVC
 */
public class Game {

	/**
	 * Passes XML resources
	 * @param args XML resources
	 */
	public static void main(String args) {
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
	    GameController gameController = new GameController(gameModel, gameView);     
	}

}
