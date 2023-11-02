import gameOfLife_Controller.GameController;
import gameOfLife_Model.GameModel;
import gameOfLife_View.GameView;

public class Game {

	public static void main(String args) {
		// TODO Auto-generated method stub
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
	    GameController gameController = new GameController(gameModel, gameView);    
	    // Configure and display the initial GUI
	    gameView.SplashScreen();
	}

}
