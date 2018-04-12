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
                    Player humain = batailleNavale.getHumain();
                    // Il n'a pas select de bateau (donc il doit en select un hmmm)
                    // Le clique doit etre dans la left grid
                    if (Painter.isClickOnLeftGrid(Controller.getLastClickPos())) {
                        // Coords de la case cliqué
                        Point2D pos = Painter.clickPosToPosForLeftGrid(Controller.getLastClickPos());
                        humain.chooseBoat(pos);

                    } else if (Painter.isClickOnRightGrid(Controller.getLastClickPos())){
                        if (humain.hasChosenBoat()) {
                            Point2D pos = Painter.clickPosToPosForRightGrid(Controller.getLastClickPos());

                            if(humain.getCurrentBoat().getMunitions() > 0) {

                                Player ia = batailleNavale.getIa();
                                int index = ia.getBoatIndexFromPos(pos);
                                batailleNavale.playerShoot(pos);
                                //On regarde si la game n'est pas finie
                                boolean finished = checkFinishedGame();
                                //Tour de l'ia
                                if(!finished) {
                                    Point2D tirIA = ia.shootIA();
                                    System.out.println("tir IA : " + tirIA.getX() + " " + tirIA.getY());
                                    batailleNavale.playerShoot(tirIA);
                                }
                            }
                        } else {
                            // Pas de bateau select !
                        }
                    }
                } else {
                    // C'est le tour de l'IA
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

    private boolean checkFinishedGame(){
        Player humain = batailleNavale.getHumain();
        Player ia = batailleNavale.getIa();
        boolean iaLoose = true;
        for(Bateau boat : ia.getBoatList()){
            if(boat.getHP() > 0 && boat.getMunitions() > 0){
                System.out.println(boat.getPosition());
                iaLoose = false;
                break;
            }
        }
        boolean humainLoose = true;
        for(Bateau boat : humain.getBoatList()){
            if(boat.getHP() > 0 && boat.getMunitions() > 0){
                humainLoose= false;
                break;
            }
        }

        if(iaLoose){
            ia.setLosed(true);
            this.gameState = GameState.LOOSE;
        }
        if(humainLoose){
            humain.setLosed(true);
            this.gameState = GameState.LOOSE;
        }

        System.out.println(" finish ? "+ (humainLoose || iaLoose));
        return humainLoose || iaLoose;


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
