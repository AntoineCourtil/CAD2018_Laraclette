package bataillenavale.modele;

import java.awt.geom.Point2D;

public class StrategieAlea implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot() {
        return null;
    }
}
