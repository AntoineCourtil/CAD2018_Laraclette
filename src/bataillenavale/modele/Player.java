package bataillenavale.modele;

import bataillenavale.boatFactory.abstractBoat.Bateau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable {

    private int currentBoatIndex;
    private Strategie strategie;


    private List<Bateau> boatList;
    private List<Point2D> tirsEchoues;


    public Player(List<Bateau> boatArrayList) {

        boatList = boatArrayList;
        tirsEchoues = new ArrayList<>();

        currentBoatIndex = -1;
    }

    /**
     * Place tous les boats du joueur aléatoirement, dans une position valide
     */
    public void placeBoatAlea() {
        Random rand = new Random();
        //TODO A TESTER
        int x = 0;
        int y = 0;
        int orientationAlea = 0;
        int index = boatList.size();
        Bateau.Orientation[] orientations = {Bateau.Orientation.EST, Bateau.Orientation.NORD, Bateau.Orientation.SUD, Bateau.Orientation.OUEST};
        for (Bateau bateau : boatList) {
            boolean isValide = false;
            while (!isValide) {
                x = rand.nextInt(BatailleNavale.WIDTH - 1);
                y = rand.nextInt(BatailleNavale.HEIGHT - 1);
                orientationAlea = rand.nextInt(3);

                bateau.setOrientation(orientations[orientationAlea]);
                bateau.setPosition(new Point2D(x, y));

                isValide = true;

                if (index == 1) {
                    isValide = true;
                } else {
                    for (Bateau verifBoat : boatList) {

                        if (verifBoat != bateau) {

                            if(isValide) {
                                //DetectBoat dit s'il trouve un boat ou non, s'il trouve un boat c'est pas valide.
                                isValide = !verifBoat.collisionBoat(bateau);

                                System.out.println(isValide + " " + x + " " + y);
                            }
                        }

                        if (bateau.isOutOfScreen()) {
                            isValide = false;
                        }

                    }
                }
            }
//            index++;
        }

    }

    public void shootIA() {
        getStrategie().generateShoot(tirsEchoues);
    }

    public boolean receiveShoot(Point2D pos, int damage) {

        boolean trouve = false;
        for (Bateau bateau : boatList) {
            if (bateau.getHP() > 0) {
                trouve = bateau.detectBoat(pos);
                if (trouve) {
                    bateau.setHP(bateau.getHP() - damage);
                }
            }

        }
        return trouve;

    }

    public boolean chooseBoat(Point2D pos) {

        boolean trouve = false;
        int index = 0;
        for (Bateau bateau : boatList) {
            if (bateau.getHP() > 0) {
                trouve = bateau.detectBoat(pos);
                if (trouve) {
                    System.out.println("Choosing boat " + index);
                    currentBoatIndex = index;
                }
            }
            index++;

        }
        return trouve;

    }

    /**
     * @param pos la position qu'on veut check s'il y a un bateau ou non
     * @return -1 si pas de bateau sinon l'index du bateau dans la list listboat
     */
    public int getBoatIndexFromPos(Point2D pos) {
        for (int i = 0; i < boatList.size(); i++) {
            System.out.println("Calling detect boat " + pos);
            if (boatList.get(i).detectBoat(pos)) return i;
        }
        return -1;
    }



    public Bateau getCurrentBoat() {
        return boatList.get(currentBoatIndex);
    }
    public int getCurrentBoatIndex() {
        return currentBoatIndex;
    }

    public void addFailedShoot(Point2D pos) {
        tirsEchoues.add(pos);
    }

    //------------ GETTERS && SETTERS

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategie s) {
        strategie = s;
    }

    public List<Bateau> getBoatList() {
        return boatList;
    }

    public boolean hasChosenBoat() {
        return currentBoatIndex != -1;
    }

}
