package bataillenavale.modele;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.abstractBoat.Bateau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BatailleNavale implements Serializable {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private boolean turnPlayer;
    private boolean playerIsReady;
    private String epoque;

    private Player humain;
    private Player ia;



    public BatailleNavale(String epoque) {
        this.epoque = epoque;

        humain = new Player(initListBateau());
        ia = new Player(initListBateau());

        humain.placeBoatAlea();
        ia.placeBoatAlea();

        turnPlayer = true;
    }

    /**
     * Crée toute la liste de bateau pour le joueur courant et la renvoie
     * @return la liste de bateaux
     */
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

    /**
     * APplique un shoot à une position données au player a qui ce n'est pas le tour
     * @param pos
     */
    public void playerShoot(Point2D pos) {
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

    public boolean playerIsReady() {
        return playerIsReady;
    }

    public void setPlayerIsReady(boolean playerIsReady) {
        this.playerIsReady = playerIsReady;
    }

    /**
     * Verifie si tous les bateaux sont bien placés
     * @return true ou false selon si bien placés ou non
     */
    public boolean checkBoatsPosition() {
        Player humain = getHumain();
        List<Bateau> boatList = humain.getBoatList();
        for (Bateau boat : boatList) {
            for (Bateau verifBoat : boatList) {

                if (verifBoat != boat) {
                    if(verifBoat.collisionBoat(boat)) {
                        return false;
                    }
                }

                if (boat.isOutOfScreen()) {
                    return false;
                }
            }
        }
        return true;
    }
}
