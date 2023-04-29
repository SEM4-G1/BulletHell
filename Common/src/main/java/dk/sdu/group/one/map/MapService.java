package dk.sdu.group.one.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MapService {
    private String mapAsset;

    int height;
    int width;

    private Set<Coordinate> obstaclePositions;
    public String getMapAsset(){
        return mapAsset;
    }

    public void setMapAsset(String mapAsset){
        this.mapAsset = mapAsset;
    }

    public Set<Coordinate> getObstaclePositions(){
        return obstaclePositions;
    }
    public void setObstaclePositions(Set<Coordinate> obstaclePositions){
        this.obstaclePositions = obstaclePositions;
    }
    public String texture = new String("test.jpg");
    public Set<Coordinate> grid;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}