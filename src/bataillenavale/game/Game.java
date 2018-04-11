package bataillenavale.game;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.engine.Cmd;
import bataillenavale.game.menu.EpochChoose;
import bataillenavale.game.menu.MainMenu;
import bataillenavale.game.menu.ResumeGame;
import bataillenavale.modele.BatailleNavale;
import bataillenavale.modele.Player;
import bataillenavale.modele.Point2D;

import javax.swing.*;
import java.io.File;


public class Game implements bataillenavale.engine.Game {

    private GameState gameState;
    private MainMenu mainMenu;
    private EpochChoose epochChoose;
    private ResumeGame resumeGame;

    private boolean fileChooserIsOpen = false;
    private boolean isSaved = false;

    private BatailleNavale batailleNavale;

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
                // C'est le tour du joueur
                if (batailleNavale.isTurnPlayer()) {
                    System.out.println("C'est le tour du player (humain)");
                    Player humain = batailleNavale.getHumain();
                    // Il n'a pas select de bateau (donc il doit en select un hmmm)
                    // Le clique doit etre dans la left grid
                    if (Painter.isClickOnLeftGrid(Controller.getLastClickPos())) {
                        System.out.println("Le player n'a pas de bateau select et le clique est dans sa grille");
                        // Coords de la case cliqué
                        Point2D pos = Painter.clickPosToPosForLeftGrid(Controller.getLastClickPos());
                        System.out.println("Le player veut select le bateau en " + pos);
                        humain.chooseBoat(pos);

                    } else if (Painter.isClickOnRightGrid(Controller.getLastClickPos())){
                        System.out.println("T'as clickey sur la grille de l'ia c'est ça ?");
                        System.out.println("Mais as-tu select un boat ?????????");
                        if (humain.hasChosenBoat()) {
                            System.out.println("OUII, passons à la castagne");
                            Point2D pos = Painter.clickPosToPosForRightGrid(Controller.getLastClickPos());
                            System.out.println("Je vois que tu as cliqué en " + pos);
                            System.out.println("Mais ya t'il un bateau à cette pos ?????????");

                            if(humain.getCurrentBoat().getMunitions() > 0) {

                                Player ia = batailleNavale.getIa();
                                int index = ia.getBoatIndexFromPos(pos);
                                if (index != -1) {
                                    System.out.println("YESSSS t'as flingué le boat d'index " + index);
                                } else {
                                    System.out.println("T'as raté t'es nul");
                                }
                                batailleNavale.playerShoot(pos);
                            }
                        } else {
                            System.out.println("NOOO, select un bateau avant de faire le malin");
                        }
                    }
                } else {
                    System.out.println("Clique : Detends toi c'est le tour de l'ia wesh");
                }
                break;
            case CHANGE:
                if (gameState == GameState.RUNNING) {
                    Player ia = batailleNavale.getIa();
                    ia.nextStrategie();
                }
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

    public BatailleNavale getBatailleNavale() {
        return batailleNavale;
    }

    public void launchBatailleNavale(String epoque) {
        batailleNavale = new BatailleNavale(epoque);
        setGameState(GameState.RUNNING);
    }
}
