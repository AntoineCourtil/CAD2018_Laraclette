package bataillenavale.modele;

import bataillenavale.boatFactory.abstractBoat.Bateau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private int currentBoatIndex;
    private boolean hasChoosedBoat;
    private Strategie strategie;

    private List<Bateau> boatList;
    private List<Point2D> tirsEchoues;



    public Player(ArrayList<Bateau> boatArrayList){

        boatList = boatArrayList;
        tirsEchoues = new ArrayList<>();

        currentBoatIndex = -1;
        hasChoosedBoat = false;

    }

    public void shootIA(){
        getStrategie().generateShoot(tirsEchoues);
    }

    public boolean receiveShoot(Point2D pos, int damage){

        for(Bateau bateau : boatList){

            if(bateau.getHP() > 0) {
                Point2D baseBateau = bateau.getPosition();
            }

        }

        return false;

    }

    public boolean chooseBoat(Point2D pos){

        return false;

    }

    public int getBoatIndexFromPos(Point2D pos){

        return 0;

    }

    public Bateau getCurrentBoat(){

        return boatList.get(currentBoatIndex);

    }

    public void addFailedShoot(Point2D pos){
        tirsEchoues.add(pos);
    }

    //------------ GETTERS && SETTERS

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategie s){
        strategie = s;
    }


}
