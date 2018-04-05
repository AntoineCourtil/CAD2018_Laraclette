package boatFactory.abstractBoat;

import java.awt.geom.Point2D;

/**
 * Created by simon on 05/04/18.
 */
public abstract class Bateau2Cases extends Bateau{

    private int size=2;

    public Bateau2Cases(int HP, float precision, int degat, int portee, Point2D position, int munitions) {
        super(HP, precision, degat, portee, position, munitions);
    }

    public int getSize(){
        return size;
    }


}
