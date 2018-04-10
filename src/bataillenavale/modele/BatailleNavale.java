package bataillenavale.modele;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.ConcreteBateauFactory;
import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.boatFactory.boatXIX.XIXBateauFactory;
import bataillenavale.game.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BatailleNavale implements Serializable {

    public static int WIDTH = 10;
    public static int HEIGHT = 10;
    private boolean turnPlayer;
    private int epoch = Game.XIX;

    private Player humain;
    private Player ia;

    public BatailleNavale(){
        ConcreteBateauFactory factory = AbstractBateauFactory.getFactoryByEpoque(epoch);

        List<Bateau> bateauxHumain = new ArrayList<>();
        List<Bateau> bateauxIA = new ArrayList<>();

        Bateau b = factory.getBateau4Cases();
        bateauxHumain.add(b);
        b.setPosition(new Point2D(5, 5));
        b.setOrientation(Bateau.Orientation.OUEST);

        b = factory.getBateau3Cases();
        bateauxIA.add(b);
        b.setPosition(new Point2D(3, 3));
        b.setOrientation(Bateau.Orientation.SUD);

        humain = new Player(bateauxHumain);
        ia = new Player(bateauxIA);

        turnPlayer = true;
    }

    public void playerShoot(Point2D pos){

    }

    public BatailleNavale loadFromFile(String filename){

        return new BatailleNavale();

    }

    public void saveToFile(String filename){

    }

    public boolean isTurnPlayer() {
        return turnPlayer;
    }

    public int getEpoch() {
        return epoch;
    }

    public Player getHumain() {
        return humain;
    }

    public Player getIa() {
        return ia;
    }
}
