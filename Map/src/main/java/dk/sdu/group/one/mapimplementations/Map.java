package dk.sdu.group.one.mapimplementations;

import dk.sdu.group.one.map.MapService;

import java.util.ArrayList;
import java.util.List;

import static dk.sdu.group.one.mapimplementations.Node.printPath;

public class Map extends MapService {
    int width = 30;
    int height = 30;

    Node[][] grid;
    public Map(){
        List<int[]> obstaclePositions = new ArrayList<int[]>();
        super.setMapAsset("map.jpg");
        grid = new Node[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                if(Math.random()<0.2){
                    grid[i][j]=new Node(true, i, j);
                    obstaclePositions.add(new int[]{i,j});
                }
                else {
                    grid[i][j]=new Node(false, i, j);
                }
            }
        }
        super.setObstaclePositions(obstaclePositions);
        Node start = grid[0][0];
        Node target = grid[width-1][height-1];
        Node res = Node.aStar(start, target, grid);
        printPath(res, grid);
    }

    //public static void main(String[] args) {
        //Map map = new Map();
        //map.printMap();
    //}


}
