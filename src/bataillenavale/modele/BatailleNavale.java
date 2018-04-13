package bataillenavale.modele;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.ConcreteBateauFactory;
import bataillenavale.boatFactory.abstractBoat.Bateau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BatailleNavale implements Serializable {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int XIX = 19;
    public static final int XVIII = 18;

    private boolean turnPlayer;
    private String epoque;

    private Player humain;
    private Player ia;

    public BatailleNavale(String epoque) {
        this.epoque = epoque;
        ConcreteBateauFactory factory = AbstractBateauFactory.getFactoryByEpoque(epoque);


        humain = new Player(initListBateau());
        ia = new Player(initListBateau());

        humain.placeBoatAlea();
        for (Bateau b :humain.getBoatList()) {
            System.out.println(b.getPosition() + " --> " + b.getSize());
        }
        ia.placeBoatAlea();

        turnPlayer = true;
    }

    private List<Bateau> initListBateau() {
        List<Bateau> listBateaux = new ArrayList<>();


        Bateau b = AbstractBateauFactory.getFactoryByEpoque(epoque).getBateau5Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoque).getBateau4Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoque).getBateau3Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoque).getBateau2Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        return listBateaux;
    }

    public void playerShoot(Point2D pos) {
//        System.out.println("            TIR EFFECTUE");

        if (turnPlayer) {
            boolean touche = ia.receiveShoot(pos, humain.getCurrentBoat().getDegat());

            humain.getCurrentBoat().setMunitions(humain.getCurrentBoat().getMunitions() - 1);

            if (!touche) humain.addFailedShoot(pos);


            turnPlayer = false;



        } else {//IA
            boolean touche = humain.receiveShoot(pos, ia.getCurrentBoat().getDegat());

            ia.getCurrentBoat().setMunitions(ia.getCurrentBoat().getMunitions() - 1);

            if (!touche) {
                ia.addFailedShoot(pos);
                ia.getStrategie().setLastShootTouched(null);
            } else {
                ia.getStrategie().setLastShootTouched(pos);
            }
            turnPlayer = true;
        }


    }

    public BatailleNavale loadFromFile(String filename) {
        return null;
    }

    public void saveToFile(String filename) {

    }

    public boolean isTurnPlayer() {
        return turnPlayer;
    }

    public String getEpoque() {
        return epoque;
    }

    public Player getHumain() {
        return humain;
    }

    public Player getIa() {
        return ia;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
    }
}
