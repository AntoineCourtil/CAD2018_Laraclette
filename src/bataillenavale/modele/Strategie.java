package bataillenavale.modele;

import java.io.Serializable;
import java.util.List;

public interface Strategie extends Serializable {

    public Point2D generateShoot(List<Point2D> tirsEchoues);

    public void setLastShootTouched(Point2D lastShootTouched);

    String toString();
}
