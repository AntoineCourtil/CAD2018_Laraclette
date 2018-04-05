package bataillenavale.modele;

import java.util.Random;

public class StrategieAlea implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot() {

        Random rand = new Random();

        int x = rand.nextInt(BatailleNavale.WIDTH);
        int y = rand.nextInt(BatailleNavale.HEIGHT);


        return new Point2D(x,y);
    }
}
