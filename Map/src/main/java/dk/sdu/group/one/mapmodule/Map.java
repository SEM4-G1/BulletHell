package dk.sdu.group.one.mapmodule;

import dk.sdu.group.one.map.MapService;
import dk.sdu.group.one.map.Node;


public class Map extends MapService{
    int width = 30;
    int height = 30;

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
    }
}
