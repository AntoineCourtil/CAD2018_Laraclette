package bataillenavale.modele;

import java.util.List;

public interface Strategie {

    public Point2D generateShoot(List<Point2D> tirsEchoues);

    public Point2D getLastShootTouched();

    public void setLastShootTouched(Point2D lastShootTouched);
}
