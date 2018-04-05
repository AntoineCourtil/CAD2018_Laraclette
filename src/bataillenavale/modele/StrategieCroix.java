package bataillenavale.modele;


import java.util.List;
import java.util.Random;

public class StrategieCroix implements Strategie{


    public boolean lastShootHasTouched(){

        return false;

    }

    @Override
    public Point2D generateShoot(List<Point2D> tirsEchoues) {
        Boolean isShootOk = false;
        int x = 0;
        int y = 0;

        while(!isShootOk) {

            Boolean echoue = false;
            for(Point2D tEchoue : tirsEchoues){
                if(tEchoue.equals(new Point2D(x, y))){
                    echoue = true;
                    break;
                }
            }
            if(!echoue){
                isShootOk = true;
            }else{
                x += 2;
                if(x >= BatailleNavale.WIDTH){
                    if(y%2 == 0) x = 0;
                    else x = 1;
                    y += 1;
                }
            }
        }
        return new Point2D(x,y);
    }
}
