package boatFactory.boatXIX;

import boatFactory.abstractBoat.Bateau4Cases;

import java.awt.geom.Point2D;

/**
 * Created by simon on 05/04/18.
 */
public class XIXBateau4Cases extends Bateau4Cases {

    public XIXBateau4Cases() {
        //HP, precision, degat, portee, position, munitions
        super(10, 10, 10, 10, null, 10);
    }

    public int getHP(){
        return super.getHP();
    }
    public float getPrecision(){
        return super.getPrecision();
    }
    public int getDegat(){
        return super.getDegat();
    }

    public int getPortee(){
        return super.getPortee();
    }
}
