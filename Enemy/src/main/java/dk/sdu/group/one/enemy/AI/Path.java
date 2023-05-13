package dk.sdu.group.one.enemy.AI;

import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.map.Coordinate;

public class Path {
    //current coordinate
    private Coordinate coordinate;

    //direction to go now
    private Vector2 vector;

    public Path(Coordinate coordinate, Vector2 vector) {
        this.coordinate = coordinate;
        this.vector = vector;
    }

    @Override
    public String toString(){
        return coordinate.toString()+ " direction: " + vector.toString();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Vector2 getVector() {
        return vector;
    }
}
