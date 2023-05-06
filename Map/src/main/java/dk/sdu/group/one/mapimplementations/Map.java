package dk.sdu.group.one.mapimplementations;

import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Map extends MapService {
    int width = 30;
    int height = 30;


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Map(){
        HashSet<Coordinate> obstaclePositions = new HashSet<>();
        String[][] mapArray = new String[30][30];
        for (int i = 0; i < 30 ; i++) {
            for (int j = 0; j < 30; j++) {
                mapArray[i][j] = "MapSprits/floor_plain.png";
            }
        }
        super.setMapAsset(mapArray);
        grid = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                if(Math.random()<0.2){
                    grid.add(new Coordinate(i, j, true));
                    obstaclePositions.add(new Coordinate(i,j, true));
                    continue;
                }
                grid.add(new Coordinate(i, j));
            }
        }
        super.setObstaclePositions(obstaclePositions);
    }

    /*
    public Map(String folderPath){
        HashSet<Coordinate> obstaclePositions = new HashSet<>();
        super.setMapAsset("map.jpg");
        grid = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                if(Math.random()<0.2){
                    grid.add(new Coordinate(i, j, true));
                    obstaclePositions.add(new Coordinate(i,j, true));
                    continue;
                }
                grid.add(new Coordinate(i, j));
            }
        }
        super.setObstaclePositions(obstaclePositions);
    }
    */

    //public static void main(String[] args) {
        //Map map = new Map();
        //map.printMap();
    //}
}
