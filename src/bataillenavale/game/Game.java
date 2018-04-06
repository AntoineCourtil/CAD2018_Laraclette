package bataillenavale.game;

import bataillenavale.engine.Cmd;
import bataillenavale.game.menu.EpochChoose;
import bataillenavale.game.menu.MainMenu;
import bataillenavale.game.menu.ResumeGame;
import bataillenavale.modele.Point2D;


public class Game implements bataillenavale.engine.Game {

	private GameState gameState;
	private MainMenu mainMenu;
    private EpochChoose epochChoose;
    private ResumeGame resumeGame;

    public static final int XIX = 19;
    public static final int XVIII = 18;

	public Game() {
        gameState = GameState.MENU;
        mainMenu = new MainMenu(this);
        epochChoose = new EpochChoose(this);
        resumeGame = new ResumeGame(this);
	}

	/**
	 * Fait evoluer le jeu suite a une commande
	 * 
	 * @param cmd la commande
	 */
	@Override
	public void evolve(Cmd cmd) {
        switch (gameState) {
            case MENU:
                mainMenu.evolve(cmd);
                break;
            case EPOCH_CHOOSE:
                epochChoose.evolve(cmd);
                break;
            case RESUME_GAME:
                resumeGame.evolve(cmd);
                break;
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

    }

    private void evolveMenu(Cmd cmd) {

    }

    private void playerChooseBoat(Point2D pos) {

    }

    private void playerShoot(Point2D pos) {

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gs) {
	    this.gameState = gs;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public EpochChoose getEpochChoose() {
        return epochChoose;
    }

    public ResumeGame getResumeGame() {
        return resumeGame;
    }
}
