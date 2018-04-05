package bataillenavale.modele;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class BatailleNavale implements Serializable{

    private int width;
    private int height;
    private boolean turnPlayer;
    private int epoch;

    private Player humain;
    private Player ia;

    public BatailleNavale(){

    }

    public void playerShoot(Point2D pos){

    }

    public BatailleNavale loadFromFile(String filename){

        return new BatailleNavale();

    }

    public void saveToFile(String filename){

    }

}
