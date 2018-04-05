package bataillenavale.modele;


import java.util.List;

public class StrategieCroix implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot(List<Point2D> tirsEchoues) {

    }
}
