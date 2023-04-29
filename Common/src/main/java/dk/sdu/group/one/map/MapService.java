package dk.sdu.group.one.map;

import java.util.List;

public abstract class MapService {
    private String mapAsset;

    private List<int[]> obstaclePositions;
    public String getMapAsset(){
        return mapAsset;
    }

    public void setMapAsset(String mapAsset){
        this.mapAsset = mapAsset;
    }
    public List<int[]> getObstaclePositions(){
        return obstaclePositions;
    }
    public void setObstaclePositions(List<int[]> obstaclePositions){
        this.obstaclePositions = obstaclePositions;
    }
    public String texture = new String("test.jpg");
    public Node[][] grid;

}
