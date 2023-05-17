package dk.sdu.group.one.ai_astar;

import dk.sdu.group.one.ai_astar.helpers.Mappers;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.one.services.LoggingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PathCreator implements AIservice {
    //vars for logging runtime
    static int totalTime = 0;
    static int totalMeasurements = 0;
    MapService mapService;
    LoggingService loggingService;
    public PathCreator() {
        this.loggingService = ServiceLoader.load(LoggingService.class).findFirst().get();
    }

    @Override
    public List<Path> getPath(Coordinate currentCoordinate, Coordinate playerCoordinate) {
        List<Path> path = new ArrayList<>();
        try{
            long startTime = System.nanoTime();

            ArrayList<Coordinate> coordinates = (ArrayList<Coordinate>) Node.aStar(currentCoordinate.clone(), playerCoordinate.clone(), Mappers.GenerateNodes(mapService));

            long endTime = System.nanoTime();
            totalMeasurements += 1;
            long time = (endTime - startTime);
            totalTime += time;
            double average = totalTime*1.0/totalMeasurements;
            double deviationPercent = (1-(time*1.0/average))*100;
            loggingService.log(this.getClass(), "new measurement: " + time + "ns - deviance from average: " + deviationPercent + "%");
            loggingService.log(this.getClass(), "Total logs: " + totalMeasurements + ", average runtime: "
                    + average + "ns");

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
