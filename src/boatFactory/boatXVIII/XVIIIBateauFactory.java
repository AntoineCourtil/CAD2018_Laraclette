package boatFactory.boatXVIII;

import boatFactory.abstractBoat.Bateau;

/**
 * Created by simon on 05/04/18.
 */
public class XVIIIBateauFactory {

    public Bateau getBateau5Cases() {
        return new XVIIIBateau5Cases();
    }

    public Bateau getBateau4Cases() {
        return new XVIIIBateau4Cases();
    }

    public Bateau getBateau3Cases() {
        return new XVIIIBateau3Cases();
    }

    public Bateau getBateau2Cases() {
        return new XVIIIBateau2Cases();
    }
}
