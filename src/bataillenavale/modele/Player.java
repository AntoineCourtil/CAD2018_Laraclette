package bataillenavale.modele;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {

    private int currentBoatIndex;
    private boolean hasChossedBoat;
    private Strategie strategie;

    private List<Boat> boatList;
    private List<Point2D> tirsEchoues;



    public Player(){

    }

    public void shoot(Point2D pos){

    }

    public boolean receiveShoot(Point2D pos, int damage){

        return false;

    }

    public boolean chooseBoat(Point2D pos){

        return false;

    }

    public int getBoatIndexFromPos(Point2D pos){

        return 0;

    }

    public Boat getCurrentBoat(){

        return null;

    }

    public void addFailedShoot(Point2D pos){

    }

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategie s){
        strategie = s;
    }
}
