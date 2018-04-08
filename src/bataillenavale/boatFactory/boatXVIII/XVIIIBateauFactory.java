package bataillenavale.boatFactory.boatXVIII;

import bataillenavale.boatFactory.AbstractBateauFactory;
import bataillenavale.boatFactory.ConcreteBateauFactory;
import bataillenavale.boatFactory.abstractBoat.*;

/**
 * Created by simon on 05/04/18.
 */
public class XVIIIBateauFactory implements ConcreteBateauFactory {

    public Bateau5Cases getBateau5Cases() {
        return new XVIIIBateau5Cases();
    }

    public Bateau4Cases getBateau4Cases() {
        return new XVIIIBateau4Cases();
    }

    public Bateau3Cases getBateau3Cases() {
        return new XVIIIBateau3Cases();
    }

    public Bateau2Cases getBateau2Cases() {
        return new XVIIIBateau2Cases();
    }
}
