package dk.sdu.group.one.enemy.AI;

import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.map.Coordinate;

public class Path {
    public Path(Coordinate coordinate, Vector2 vector) {
        this.coordinate = coordinate;
        this.vector = vector;
    }

    //current coordinate
    Coordinate coordinate;

    //direction to go now
    Vector2 vector;

    @Override
    public String toString(){
        return coordinate.toString()+ " direction: " + vector.toString();
    }
}
