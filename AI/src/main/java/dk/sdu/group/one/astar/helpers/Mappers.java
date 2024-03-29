package dk.sdu.group.one.astar.helpers;

import dk.sdu.group.one.astar.Node;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Mappers {
    public static Node[][] GenerateNodes(MapService mapService) {
        Node[][] grid = new Node[mapService.getWidth()][mapService.getHeight()];
        for (Coordinate coordinate : mapService.grid) {
            grid[coordinate.getX()][coordinate.getY()] = new Node(coordinate.getIsObstacle(), coordinate.getX(), coordinate.getY());
        }
        return grid;
    }

        public static Node coordinateToNode (Coordinate coordinate){
            return new Node(coordinate.getIsObstacle(), coordinate.getX(), coordinate.getY());
        }
    }
