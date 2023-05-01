package dk.sdu.group.one.map;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;
    boolean isObstacle = false;

    public Coordinate(int x, int y, boolean isObstacle){
        this.x = x;
        this.y = y;
        this.isObstacle = isObstacle;
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }
        return this.x == ((Coordinate) o).getX() && this.y == ((Coordinate) o).getY();
    }

    @Override
    public int hashCode() {
        return 100*x+y;
    }

    @Override
    public String toString(){
        return "x: "+x+ ", y: "+ y+ ", hash: "+hashCode();
    }

}
