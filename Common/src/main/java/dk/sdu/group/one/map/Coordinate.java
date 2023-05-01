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
        if (o instanceof Coordinate coordinate) {
            System.out.println("Comparing: "+ this +" with: "+ coordinate);
            return this.x == coordinate.getX() && this.y == coordinate.getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int a = x;
        int b = y;
        return 100*a+b;
    }

    @Override
    public String toString(){
        return "x: "+x+ ", y: "+ y+ ", hash: "+hashCode();
    }

}
