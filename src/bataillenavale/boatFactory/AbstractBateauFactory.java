package bataillenavale.boatFactory;

import bataillenavale.boatFactory.boatXIX.XIXBateauFactory;
import bataillenavale.boatFactory.boatXVIII.XVIIIBateauFactory;

/**
 * Created by simon on 05/04/18.
 */
public abstract class AbstractBateauFactory {
    public static ConcreteBateauFactory getFactoryByEpoque(String epoque) {
        switch (epoque){
            case "XIX":
                return new XIXBateauFactory();
            case "XVIII":
                return new XVIIIBateauFactory();
        }
        return null;
    }


}
