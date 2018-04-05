package boatFactory.boatXIX;

import boatFactory.abstractBoat.Bateau3Cases;

/**
 * Created by simon on 05/04/18.
 */
public class XIXBateau3Cases extends Bateau3Cases{

    public XIXBateau3Cases() {
        //HP, precision, degat, portee, position, munitions
        super(10, 10, 10, 10, null, 10);
    }

    public int getHP(){
        return super.getHP();
    }
    public float getPrecision(){
        return super.getPortee();
    }
    public int getDegat(){
        return super.getDegat();
    }

    public int getPortee(){
        return super.getPortee();
    }
}
