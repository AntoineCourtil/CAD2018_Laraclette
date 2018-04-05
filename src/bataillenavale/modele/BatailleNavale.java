package bataillenavale.modele;

import java.io.Serializable;

public class BatailleNavale implements Serializable{

    public static int WIDTH;
    public static int HEIGHT;
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
