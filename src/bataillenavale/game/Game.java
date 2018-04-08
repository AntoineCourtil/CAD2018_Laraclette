package bataillenavale.game;

import bataillenavale.engine.Cmd;
import bataillenavale.game.menu.EpochChoose;
import bataillenavale.game.menu.MainMenu;
import bataillenavale.game.menu.ResumeGame;
import bataillenavale.modele.BatailleNavale;
import bataillenavale.modele.Point2D;

import javax.swing.*;
import java.io.File;


public class Game implements bataillenavale.engine.Game {

    private GameState gameState;
    private MainMenu mainMenu;
    private EpochChoose epochChoose;
    private ResumeGame resumeGame;

    public static final int XIX = 19;
    public static final int XVIII = 18;

    private String currentEpoch = "";
    private boolean fileChooserIsOpen = false;
    private boolean isSaved = false;

    private BatailleNavale batailleNavale;

    public Game() {
        gameState = GameState.MENU;
        mainMenu = new MainMenu(this);
        epochChoose = new EpochChoose(this);
        resumeGame = new ResumeGame(this);
        batailleNavale = new BatailleNavale();
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
            case RUNNING:
                evolveRunning(cmd);
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
        switch (cmd) {
            case CLICK:
                /*System.out.println("Click position : " + Controller.getLastClickPos());
                System.out.println("Is on left grid : " + Painter.isClickOnLeftGrid(Controller.getLastClickPos()));
                System.out.println("Is on right grid : " + Painter.isClickOnRightGrid(Controller.getLastClickPos()));*/
                break;
            case QUIT:
                gameState = GameState.MENU;
                break;
            case SAVE:
                if (fileChooserIsOpen || isSaved) break;
                Runnable r = () -> {
                    fileChooserIsOpen = true;
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File selected = jfc.getSelectedFile();
                        System.out.println(selected);
                    }
                    fileChooserIsOpen = false;
                    isSaved = true;
                };
                SwingUtilities.invokeLater(r);
                break;
        }
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

    public String getCurrentEpoch() {
        return currentEpoch;
    }

    public void setCurrentEpoch(String currentEpoch) {
        this.currentEpoch = currentEpoch;
    }

    public BatailleNavale getBatailleNavale() {
        return batailleNavale;
    }
}
