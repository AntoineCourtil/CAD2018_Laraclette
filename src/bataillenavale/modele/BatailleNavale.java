package bataillenavale.modele;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.abstractBoat.Bateau;
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
        List<Bateau> bateauxHumain = new ArrayList<>();

        Bateau b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau4Cases();
        bateauxHumain.add(b);
        b.setPosition(new Point2D(5, 5));
        b.setOrientation(Bateau.Orientation.OUEST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau4Cases();
        bateauxHumain.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        humain = new Player(bateauxHumain);

        // TODO placeBoatAlea : boucle infinie ou jsaispasquoi ptinnnnnnnnnn
        humain.placeBoatAlea();

        ia = new Player(new ArrayList<>());
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
