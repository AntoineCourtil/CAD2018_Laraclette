package bataillenavale.boatFactory.boatXVIII;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.boatFactory.abstractBoat.Bateau3Cases;

/**
 * Created by simon on 05/04/18.
 */
public class XVIIIBateau3Cases extends Bateau3Cases{

    public XVIIIBateau3Cases() {
        //HP, precision, degat, portee, position, munitions
        super(6, 10, 2, 10, null, 10);
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
