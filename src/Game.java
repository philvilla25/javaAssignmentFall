
public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
	    gameView.SplashScreen();
	    GameController gameController = new GameController(gameModel, gameView);    

	}

}
