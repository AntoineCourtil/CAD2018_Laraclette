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



        humain = new Player(initListBateau());
        ia = new Player(initListBateau());

        // TODO placeBoatAlea : boucle infinie ou jsaispasquoi ptinnnnnnnnnn
        humain.placeBoatAlea();
        ia.placeBoatAlea();

//        ia.setStrategie(new StrategieAlea());
        ia.setStrategie(new StrategieCroix());

        turnPlayer = true;
    }

    private List<Bateau> initListBateau(){
        List<Bateau> listBateaux = new ArrayList<>();


        Bateau b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau5Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau4Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau3Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        b = AbstractBateauFactory.getFactoryByEpoque(epoch).getBateau2Cases();
        listBateaux.add(b);
        b.setPosition(new Point2D(0, 0));
        b.setOrientation(Bateau.Orientation.EST);

        return listBateaux;
    }

    public void playerShoot(Point2D pos){
//        System.out.println("            TIR EFFECTUE");

        if(turnPlayer){
            boolean touche = ia.receiveShoot(pos, humain.getCurrentBoat().getDegat());

            humain.getCurrentBoat().setMunitions(humain.getCurrentBoat().getMunitions() - 1);

            if(!touche) humain.addFailedShoot(pos);

            turnPlayer = false;

            Point2D tirIA = ia.shootIA();
            System.out.println("tir IA : " + tirIA.getX() + " " + tirIA.getY());
            playerShoot(tirIA);

        } else{
            boolean touche = humain.receiveShoot(pos, ia.getCurrentBoat().getDegat());

            ia.getCurrentBoat().setMunitions(ia.getCurrentBoat().getMunitions() - 1);

            if(!touche) ia.addFailedShoot(pos);

            turnPlayer = true;
        }



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
