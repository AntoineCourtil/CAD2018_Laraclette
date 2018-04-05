package bataillenavale.modele;

import java.util.List;

public interface Strategie {

    public Point2D generateShoot(List<Point2D> tirsEchoues);

}
