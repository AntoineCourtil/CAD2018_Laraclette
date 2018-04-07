package bataillenavale;

import bataillenavale.game.ImageFactory;
import bataillenavale.game.Painter;
import bataillenavale.engine.GameEngineGraphical;
import bataillenavale.game.Controller;
import bataillenavale.game.Game;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

        // On charge les images
        ImageFactory.getInstance();
		Game game = new Game();
		Painter painter = new Painter(game);
		Controller controller = new Controller();

        System.out.println("HEIGHT : " + Painter.HEIGHT);
        System.out.println("WIDTH : " + Painter.WIDTH);

        // classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();

		// AbstractBateauFactory.getFactoryByEpoque(MONEPOQUE).getBateau4Case()
	}

}
