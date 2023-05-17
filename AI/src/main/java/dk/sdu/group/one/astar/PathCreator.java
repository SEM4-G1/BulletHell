package dk.sdu.group.one.ai_astar;

import dk.sdu.group.one.ai_astar.helpers.Mappers;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.one.services.LoggingService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PathCreator implements AIservice {
    //vars for logging runtime
    static BigDecimal totalTime = BigDecimal.ZERO;
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
            BigDecimal startTime = BigDecimal.valueOf(System.nanoTime());

            ArrayList<Coordinate> coordinates = (ArrayList<Coordinate>) Node.aStar(currentCoordinate.clone(), playerCoordinate.clone(), Mappers.GenerateNodes(mapService));

            BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
            totalMeasurements += 1;
            BigDecimal time = endTime.subtract(startTime);
            totalTime = totalTime.add(time);
            BigDecimal average = totalTime.divide(BigDecimal.valueOf(totalMeasurements), RoundingMode.HALF_UP);
            BigDecimal deviationPercent = BigDecimal.ONE.subtract(time.divide(average, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100));
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
