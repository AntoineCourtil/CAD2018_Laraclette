package bataillenavale.boatFactory.abstractBoat;

import java.awt.geom.Point2D;

/**
 * Created by simon on 05/04/18.
 */
public abstract class Bateau5Cases extends Bateau{


    public Bateau5Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions, 5);
    }

}
