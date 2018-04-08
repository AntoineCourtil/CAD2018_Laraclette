package bataillenavale.modele;

import java.util.Objects;

public class Point2D {

    private int x;
    private int y;

    public Point2D(int x_, int y_){
        x = x_;
        y = y_;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return getX() == point2D.getX() &&
                getY() == point2D.getY();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getX(), getY());
    }

    public String toString() {
        return "" + x + " | " + y;
    }
}
