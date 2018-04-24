package bataillenavale.boatFactory.boatXVIII;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.boatFactory.abstractBoat.Bateau2Cases;

/**
 * Created by simon on 05/04/18.
 */
public class XVIIIBateau2Cases extends Bateau2Cases{

    public XVIIIBateau2Cases() {
        //HP, precision, degat, portee, position, munitions
        super(4, 10, 1, 10, null, 10);
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
