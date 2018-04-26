package bataillenavale.modele;

import java.util.List;
import java.util.Random;

/**
 * Stratégie de tir aléatoire, mais ne mémorise pas si un tir à touché ou non, vraiment du full aléatoire
 * Ne retire pas au même endroit qu'un tir échoué
 */
public class StrategieAlea implements Strategie{

    private Point2D lastShootTouched;

    @Override
    public Point2D generateShoot(List<Point2D> tirsEchoues) {


        Random rand = new Random();
        Boolean isShootOk = false;
        int x = 0;
        int y = 0;

        while(!isShootOk) {
            x = rand.nextInt(BatailleNavale.WIDTH-1);
            y = rand.nextInt(BatailleNavale.HEIGHT-1);

            Boolean echoue = false;
            for(Point2D tEchoue : tirsEchoues){
                if(tEchoue.equals(new Point2D(x, y))){
                    echoue = true;
                    break;
                }
            }
            if(!echoue){
                isShootOk = true;
            }
        }

        return new Point2D(x,y);
    }

    @Override
    public void setLastShootTouched(Point2D lastShootTouched) {

    }

    public String toString() {
        return "Aléatoire";
    }
}
