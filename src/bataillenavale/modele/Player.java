package bataillenavale.modele;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable {

    private int currentBoatIndex;

    private List<Bateau> boatList;
    private List<Point2D> tirsEchoues;

    private Strategie[] strategies = {new StrategieCroix(), new StrategieAlea()};
    private int currentStrategie = 0;
    private boolean losed = false;


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
        int x;
        int y;
        int orientationAlea;
        int index = boatList.size();
        Bateau.Orientation[] orientations = {Bateau.Orientation.EST, Bateau.Orientation.NORD, Bateau.Orientation.SUD, Bateau.Orientation.OUEST};

        for (Bateau bateau : boatList) {
            boolean isValide = false;
            while (!isValide) {
                x = rand.nextInt(BatailleNavale.WIDTH - 1);
                y = rand.nextInt(BatailleNavale.HEIGHT - 1);
                orientationAlea = rand.nextInt(4);
                bateau.setOrientation(orientations[orientationAlea]);
                bateau.setPosition(new Point2D(x, y));

                isValide = true;

                if (index == 1) {
                    isValide = true;
                } else {
                    for (Bateau verifBoat : boatList) {

                        if (verifBoat != bateau) {
                            if(isValide) isValide = !verifBoat.collisionBoat(bateau);
                        }

                        if (bateau.isOutOfScreen()) {
                            isValide = false;
                        }

                    }
                }
            }
        }

    }

    /**
     * Génère un shoot en fonction de la stratégie
     * @return la position du shoot
     */
    public Point2D shootIA() {
        do {
            Random rand = new Random();
            currentBoatIndex = rand.nextInt(boatList.size());
        } while(!(boatList.get(currentBoatIndex).getHP() > 0 && boatList.get(currentBoatIndex).getMunitions() > 0));

        return getStrategie().generateShoot(tirsEchoues);
    }

    /**
     * Reception d'un tir de l'adversaire
     * @param pos la position du tir
     * @param damage les domages du tir
     * @return si un bateau a été touché
     */
    public boolean receiveShoot(Point2D pos, int damage) {

        boolean touche = false;
        for (Bateau bateau : boatList) {
            if (bateau.getHP() > 0) {
                touche = bateau.detectBoat(pos);
                if (touche) {
                    bateau.setHP(bateau.getHP() - damage);
                    bateau.addPointTouche(pos);
                    if(bateau.getHP() <= 0) {
                        currentBoatIndex = -1;
                    }

                }
            }

        }
        return touche;

    }

    public void chooseBoat(Point2D pos) {
        int index = 0;
        for (Bateau bateau : boatList) {
            if (bateau.getHP() > 0) {
                if (bateau.detectBoat(pos)) {
                    currentBoatIndex = index;
                    return;
                }
            }
            index++;
        }
        currentBoatIndex = -1;
    }

    /**
     * @param pos la position qu'on veut check s'il y a un bateau ou non
     * @return -1 si pas de bateau sinon l'index du bateau dans la list listboat
     */
    public int getBoatIndexFromPos(Point2D pos) {
        for (int i = 0; i < boatList.size(); i++) {
            if (boatList.get(i).detectBoat(pos) && boatList.get(i).getHP() > 0) return i;
        }
        return -1;
    }



    public Bateau getCurrentBoat() {
        if (currentBoatIndex == -1) return null;
        return boatList.get(currentBoatIndex);
    }
    public int getCurrentBoatIndex() {
        return currentBoatIndex;
    }

    public void addFailedShoot(Point2D pos) {
        tirsEchoues.add(pos);
    }

    public Strategie getStrategie() {
        return strategies[currentStrategie];
    }

    public void nextStrategie() {
        currentStrategie++;
        if (currentStrategie == strategies.length) currentStrategie = 0;
    }

    public List<Bateau> getBoatList() {
        return boatList;
    }

    public boolean hasChosenBoat() {
        return currentBoatIndex != -1;
    }

    public List<Point2D> getTirsEchoues() {
        return tirsEchoues;
    }

    public void setLosed(boolean losed) {
        this.losed = losed;
    }
}
