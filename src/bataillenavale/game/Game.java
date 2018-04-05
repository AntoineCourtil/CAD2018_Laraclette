package bataillenavale.game;

import bataillenavale.engine.Cmd;

import java.awt.geom.Point2D;


public class Game implements bataillenavale.engine.Game {

	private GameState gameState;

	private int selectedMenuIndex = 0;
	private final String[] menuEntries = {"Jouer", "Quitter"};

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
		if (gameState == GameState.MENU) {
		    evolveMenu(cmd);
        }
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
        if (gameState == GameState.MENU) {
            evolveMenu(cmd);
        }
    }

    private void evolveMenu(Cmd cmd) {
        if (cmd == Cmd.UP) {
            selectedMenuIndex++;
            if (selectedMenuIndex == menuEntries.length) selectedMenuIndex = 0;
        } else if (cmd == Cmd.DOWN) {
            selectedMenuIndex--;
            if(selectedMenuIndex == -1) selectedMenuIndex = menuEntries.length - 1;
        } else if (cmd == Cmd.ENTER) {
            switch (menuEntries[selectedMenuIndex]) {
                case "Quitter":
                    System.exit(0);
            }
        }
    }

    private void evolveEpochChoose(Cmd cmd) {

    }

    private void evolveResumeGame(Cmd cmd) {

    }

    private void playerChooseBoat(Point2D pos) {

    }

    private void playerShoot(Point2D pos) {

    }

    public GameState getGameState() {
        return gameState;
    }

    public int getSelectedMenuIndex() {
        return selectedMenuIndex;
    }

    public String[] getMenuEntries() {
        return menuEntries;
    }
}