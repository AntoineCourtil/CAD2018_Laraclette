package boatFactory.boatXVIII;

import boatFactory.abstractBoat.Bateau;

import java.awt.geom.Point2D;

/**
 * Created by simon on 05/04/18.
 */
public class XVIIIBateau2Cases extends Bateau{

    public XVIIIBateau2Cases() {
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
