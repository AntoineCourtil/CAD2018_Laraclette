package bataillenavale.boatFactory.abstractBoat;

/**
 * Created by simon on 05/04/18.
 */

import java.awt.geom.Point2D;

public abstract class Bateau4Cases extends Bateau{


    public Bateau4Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions, 2);
    }

}
