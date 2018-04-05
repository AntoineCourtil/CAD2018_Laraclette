package boatFactory.abstractBoat;

import bataillenavale.modele.Point2D;

/**
 * Created by simon on 05/04/18.
 */
public abstract class Bateau5Cases extends Bateau{

    private int size = 5;

    public Bateau5Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions);
    }

    public int getSize(){
        return size;
    }
}
