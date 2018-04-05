package bataillenavale;

import bataillenavale.game.Painter;
import bataillenavale.engine.GameEngineGraphical;
import bataillenavale.game.Controller;
import bataillenavale.game.Game;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		Game game = new Game();
		Painter painter = new Painter(game);
		Controller controller = new Controller();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();

		// AbstractBateauFactory.getFactoryByEpoque(MONEPOQUE).getBateau4Case()
	}

}
