package bataillenavale.boatFactory.abstractBoat;

import bataillenavale.modele.Point2D;

/**
 * Created by simon on 05/04/18.
 */


public abstract class Bateau4Cases extends Bateau{


    public Bateau4Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions, 4);
    }

}
