package boatFactory;

import bataillenavale.game.Game;
import boatFactory.boatXIX.XIXBateauFactory;
import boatFactory.boatXVIII.XVIIIBateauFactory;

/**
 * Created by simon on 05/04/18.
 */
public abstract class AbstractBateauFactory {
    public static AbstractBateauFactory getFactoryByEpoque(int epoque) {
        switch (epoque){
            case Game.XIX :
                return new XIXBateauFactory();
            case Game.XVIII :
                return new XVIIIBateauFactory();
        }
        return null;
    }


}
