package bataillenavale.modele;

import java.awt.geom.Point2D;

public class StrategieCroix implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot() {
        return null;
    }
}
