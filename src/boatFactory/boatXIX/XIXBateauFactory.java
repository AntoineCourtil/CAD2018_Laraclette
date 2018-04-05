package boatFactory.boatXIX;

import boatFactory.AbstractBateauFactory;
import boatFactory.abstractBoat.Bateau;

/**
 * Created by simon on 05/04/18.
 */
public class XIXBateauFactory extends AbstractBateauFactory{

    public Bateau getBateau5Cases() {
        return new XIXBateau5Cases();
    }

    public Bateau getBateau4Cases() {
        return new XIXBateau4Cases();
    }

    public Bateau getBateau3Cases() {
        return new XIXBateau3Cases();
    }

    public Bateau getBateau2Cases() {
        return new XIXBateau2Cases();
    }
}
