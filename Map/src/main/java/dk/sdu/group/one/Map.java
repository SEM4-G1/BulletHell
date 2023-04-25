package dk.sdu.group.one;

import dk.sdu.group.one.map.MapService;
import java.util.Arrays;

public class Map extends MapService{
    int width = 15;
    int height = 15;
    Cell[][] grid;
    public Map(){
        grid = new Cell[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j <30; j++) {
                if(Math.random()<0.2){
                    grid[i][j]=new Cell(true);
                }
                else {
                    grid[i][j]=new Cell(false);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map map = new Map();
        map.printMap();
    }
    
    void printMap(){
        for (int i = 0; i < width; i++) {
            System.out.println("");
            for (int j = 0; j < height; j++) {
                if(grid[i][j].isObstacle){
                    System.out.print(" # ");
                }
                else{
                    System.out.print(" . ");
                }
            }
        }
    }
}
