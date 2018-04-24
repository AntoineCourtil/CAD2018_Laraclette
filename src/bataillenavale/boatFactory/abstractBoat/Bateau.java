package bataillenavale.boatFactory.abstractBoat;

import bataillenavale.modele.BatailleNavale;
import bataillenavale.modele.Point2D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Bateau implements Serializable {


    public enum Orientation{NORD, SUD, EST, OUEST}
    private int HP;
    private float precision;
    private int degat;
    private int portee;
    private Point2D position;
    private int munitions;
    private List<Point2D> pointsTouches;
    private Orientation orientation;
    private int size;

    public Bateau(int HP, float precision, int degat, int portee, Point2D position, int munitions, int size){
        setHP(HP);
        setPrecision(precision);
        setDegat(degat);
        setPortee(portee);
        setPosition(position);
        setMunitions(munitions);
        setSize(size);

        pointsTouches = new ArrayList<>();
    }

    public void addPointTouche(Point2D pos){
        pointsTouches.add(pos);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getPortee() {
        return portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getMunitions() {
        return munitions;
    }

    public void setMunitions(int munitions) {
        this.munitions = munitions;
    }

    public List<Point2D> getPointsTouches() {
        return pointsTouches;
    }

    public void setPointsTouches(List<Point2D> pointsTouches) {
        this.pointsTouches = pointsTouches;
    }

    public void nextOrientation() {
        if (orientation == Orientation.SUD) orientation = Orientation.OUEST;
        else if (orientation == Orientation.OUEST) orientation = Orientation.NORD;
        else if (orientation == Orientation.NORD) orientation = Orientation.EST;
        else if (orientation == Orientation.EST) orientation = Orientation.SUD;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point2D getFinBateau(){
        switch (orientation){
            case NORD:
                return new Point2D(position.getX(), position.getY() - (size-1));
            case SUD:
                return new Point2D(position.getX(), position.getY() + (size-1));
            case EST:
                return new Point2D(position.getX() + (size-1), position.getY());
            default:
                return new Point2D(position.getX() - (size-1), position.getY());
        }
    }

    public boolean isOutOfScreen(){

        if(position.getX() < 0 || getFinBateau().getX() < 0) return true;
        if(position.getY() < 0 || getFinBateau().getY() < 0) return true;

        if(position.getX() >= BatailleNavale.WIDTH || getFinBateau().getX() >= BatailleNavale.WIDTH) return true;
        if(position.getY() >= BatailleNavale.HEIGHT || getFinBateau().getY() >= BatailleNavale.HEIGHT) return true;

        return false;
    }

    public boolean detectBoat(Point2D pos) {
        boolean trouve = false;
        Orientation orientation = this.getOrientation();
        int size = this.getSize();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        for (int i = 0; i < size; i++) {
            if (x >= BatailleNavale.WIDTH || y >= BatailleNavale.HEIGHT || x < 0 || y < 0) {
                //On sort du plateau donc on simule un bateau trouve afin de dire que la case est non valide
                trouve = true;
                break;
            }
            switch (orientation) {
                case NORD:
                    if (pos.equals(new Point2D(x, y))) trouve = true;
                    else y--;
                    break;
                case SUD:
                    if (pos.equals(new Point2D(x, y))) trouve = true;
                    else y++;
                    break;

                case OUEST:
                    if (pos.equals(new Point2D(x, y))) trouve = true;
                    else x--;
                    break;

                case EST:
                    if (pos.equals(new Point2D(x, y))) trouve = true;
                    else x++;
                    break;
            }

            if (trouve) break;
        }
        return trouve;

    }

    public boolean collisionBoat(Bateau bateau){

        boolean trouve = false;

        int x = bateau.getPosition().getX();
        int y = bateau.getPosition().getY();

        for (int i = 0; i < size; i++) {

            switch (bateau.getOrientation()) {
                case NORD:
                    if (detectBoat(new Point2D(x, y))) trouve = true;
                    else y--;
                    break;
                case SUD:
                    if (detectBoat(new Point2D(x, y))) trouve = true;
                    else y++;
                    break;

                case OUEST:
                    if (detectBoat(new Point2D(x, y))) trouve = true;
                    else x--;
                    break;

                case EST:
                    if (detectBoat(new Point2D(x, y))) trouve = true;
                    else x++;
                    break;
            }

            if (trouve) break;
        }
        return trouve;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bateau)) return false;
        Bateau bateau = (Bateau) o;
        return getHP() == bateau.getHP() &&
                Float.compare(bateau.getPrecision(), getPrecision()) == 0 &&
                getDegat() == bateau.getDegat() &&
                getPortee() == bateau.getPortee() &&
                getMunitions() == bateau.getMunitions() &&
                getSize() == bateau.getSize() &&
                Objects.equals(getPosition(), bateau.getPosition()) &&
                Objects.equals(getPointsTouches(), bateau.getPointsTouches()) &&
                getOrientation() == bateau.getOrientation();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getHP(), getPrecision(), getDegat(), getPortee(), getPosition(), getMunitions(), getPointsTouches(), getOrientation(), getSize());
    }
}
