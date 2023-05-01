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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }
        return x == ((Coordinate) o).x && y == ((Coordinate) o).y;
    }

    @Override
    public int hashCode() {
        return 100*x+y;
    }
}
