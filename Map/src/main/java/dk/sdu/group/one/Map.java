package dk.sdu.group.one;

import dk.sdu.group.one.map.MapService;
import java.util.Arrays;

import static dk.sdu.group.one.Node.printPath;

public class Map extends MapService{
    int width = 15;
    int height = 15;

    Node[][] grid;
    public Map(){
        grid = new Node[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                if(Math.random()<0.2){
                    grid[i][j]=new Node(true, i, j);
                }
                else {
                    grid[i][j]=new Node(false, i, j);
                }
            }
        }
        Node start = grid[0][0];
        Node target = grid[width-1][height-1];
        Node res = Node.aStar(start, target, grid);
        printPath(res, grid);
    }

    public static void main(String[] args) {
        Map map = new Map();
        //map.printMap();
    }


}
