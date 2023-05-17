package dk.sdu.group.one.enemy.AI;

import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.List;

public interface AIservice {
    public List<Path> getPath(Coordinate currentCoordinate, Coordinate playerCoordinate);
    public void changeMap(MapService mapService);
}
