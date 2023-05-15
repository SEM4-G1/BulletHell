package dk.sdu.group.one.ai_astar;

import dk.sdu.group.one.ai_astar.helpers.Mappers;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.ArrayList;
import java.util.List;

public class PathCreator implements AIservice {
    MapService mapService;

    @Override
    public List<Path> getPath(Coordinate currentCoordinate, Coordinate playerCoordinate) {
        List<Path> path = new ArrayList<>();
        try{
            ArrayList<Coordinate> coordinates = (ArrayList<Coordinate>) Node.aStar(currentCoordinate.clone(), playerCoordinate.clone(), Mappers.GenerateNodes(mapService));
            for (int i = 0; i < coordinates.size()-1; i++) {
                path.add(new Path(coordinates.get(i), new Vector2(coordinates.get(i).getX()-coordinates.get(i+1).getX(),
                        coordinates.get(i).getY()-coordinates.get(i+1).getY())));
            }
        } catch (NullPointerException e){
            return path;
        }
        return path;
    }

    @Override
    public void changeMap(MapService mapService) {
        this.mapService = mapService;
    }
}
