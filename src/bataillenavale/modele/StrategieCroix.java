package bataillenavale.modele;


import java.util.List;
import java.util.Random;

/**
 * Stratégie de tir en croix, tire toutes les deux cases en alternant selon que la ligne soit paire ou impaire
 */
public class StrategieCroix implements Strategie{

    //Stock le dernier tir touché afin de pouvoir retirer dessus si le bateau n'a plus de vie
    private Point2D lastShootTouched;

    @Override
    public Point2D generateShoot(List<Point2D> tirsEchoues) {
        Boolean isShootOk = false;
        int x = 0;
        int y = 0;

        //Tant que le shoot n'est pas bon, on génére à nouveau un autre shoot
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

    public void setLastShootTouched(Point2D lastShootTouched) {
        this.lastShootTouched = lastShootTouched;
    }
}
