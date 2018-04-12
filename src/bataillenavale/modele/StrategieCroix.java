package bataillenavale.modele;


import java.util.List;
import java.util.Random;

public class StrategieCroix implements Strategie{

    private Point2D lastShootTouched;

    @Override
    public Point2D generateShoot(List<Point2D> tirsEchoues) {
        Boolean isShootOk = false;
        int x = 0;
        int y = 0;

        while(!isShootOk) {
            if (lastShootTouched != null) {
                System.out.println(lastShootTouched);
                return lastShootTouched;
            } else {

                Boolean echoue = false;
                for (Point2D tEchoue : tirsEchoues) {
                    if (tEchoue.equals(new Point2D(x, y))) {
                        echoue = true;
                        break;
                    }
                }
                if (!echoue) {
                    isShootOk = true;
                } else {
                    x += 2;
                    if (x >= BatailleNavale.WIDTH) {
                        if (y % 2 == 0) x = 0;
                        else x = 1;
                        y += 1;
                    }
                }
            }
        }
        return new Point2D(x,y);
    }

    public String toString() {
        return "En croix";
    }

    public Point2D getLastShootTouched() {
        return lastShootTouched;
    }

    public void setLastShootTouched(Point2D lastShootTouched) {
        this.lastShootTouched = lastShootTouched;
    }
}
