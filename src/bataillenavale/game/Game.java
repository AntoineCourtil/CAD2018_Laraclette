package bataillenavale.game;

import bataillenavale.engine.Cmd;

import java.awt.geom.Point2D;


public class Game implements bataillenavale.engine.Game {

	private GameState gameState;

	public Game() {
        gameState = GameState.MENU;
	}

	/**
	 * Fait evoluer le jeu suite a une commande
	 * 
	 * @param cmd la commande
	 */
	@Override
	public void evolve(Cmd cmd) {
		System.out.println("Execute " + cmd);
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

	private void evolveRunning(Cmd cmd) {

    }

    private void evolveMenu(Cmd cmd) {

    }

    private void evolveEpochChoose(Cmd cmd) {

    }

    private void evolveResumeGame(Cmd cmd) {

    }

    private void playerChooseBoat(Point2D pos) {

    }

    private void playerShoot(Point2D pos) {

    }

}
