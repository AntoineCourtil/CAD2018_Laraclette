package boatFactory.abstractBoat;

/**
 * Created by simon on 05/04/18.
 */

import java.awt.geom.Point2D;

public abstract class Bateau4Cases extends Bateau{

    private int size = 4;

    public Bateau4Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions);
    }

    public int getSize(){
        return size;
    }
}
