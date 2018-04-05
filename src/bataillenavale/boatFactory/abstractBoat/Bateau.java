package bataillenavale.boatFactory.abstractBoat;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 05/04/18.
 */
public abstract class Bateau {

    public enum Orientation{NORD, SUD, EST, OUEST}
    private int HP;
    private float precision;
    private int degat;
    private int portee;
    private Point2D position;
    private int munitions;
    private List<Point2D> pointsTouches;
    private Orientation orientation;

    public Bateau(int HP, float precision, int degat, int portee, Point2D position, int munitions){
        setHP(HP);
        setPrecision(precision);
        setDegat(degat);
        setPortee(portee);
        setPosition(position);
        setMunitions(munitions);

        pointsTouches = new ArrayList<>();
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


    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }


}
