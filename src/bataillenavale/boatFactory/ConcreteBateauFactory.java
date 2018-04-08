package bataillenavale.boatFactory;

import bataillenavale.boatFactory.abstractBoat.*;

public interface ConcreteBateauFactory {
    Bateau5Cases getBateau5Cases();
    Bateau4Cases getBateau4Cases();
    Bateau3Cases getBateau3Cases();
    Bateau2Cases getBateau2Cases();
}
