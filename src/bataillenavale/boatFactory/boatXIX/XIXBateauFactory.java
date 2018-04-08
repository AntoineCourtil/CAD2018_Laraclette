package bataillenavale.boatFactory.boatXIX;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.ConcreteBateauFactory;
import bataillenavale.boatFactory.abstractBoat.*;

/**
 * Created by simon on 05/04/18.
 */
public class XIXBateauFactory implements ConcreteBateauFactory {

    public Bateau5Cases getBateau5Cases() {
        return new XIXBateau5Cases();
    }

    public Bateau4Cases getBateau4Cases() {
        return new XIXBateau4Cases();
    }

    public Bateau3Cases getBateau3Cases() {
        return new XIXBateau3Cases();
    }

    public Bateau2Cases getBateau2Cases() {
        return new XIXBateau2Cases();
    }
}
