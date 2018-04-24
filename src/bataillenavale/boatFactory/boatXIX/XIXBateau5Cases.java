package bataillenavale.boatFactory.boatXIX;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.boatFactory.abstractBoat.Bateau5Cases;

/**
 * Created by simon on 05/04/18.
 */
public class XIXBateau5Cases extends Bateau5Cases{

    public XIXBateau5Cases() {
        //HP, precision, degat, portee, position, munitions
        super(40, 10, 16, 10, null, 10);
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
