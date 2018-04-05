package bataillenavale.modele;



public class StrategieCroix implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot() {
        return null;
    }
}
