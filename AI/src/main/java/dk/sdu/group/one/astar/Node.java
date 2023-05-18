package dk.sdu.group.one.astar;

import dk.sdu.group.one.astar.helpers.Mappers;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.services.LoggingService;

import java.util.*;

public class Node implements Comparable<Node>{
    // Id for readability of result purposes
    private static int idCounter = 0;
    private static LoggingService loggingService = ServiceLoader.load(LoggingService.class).findFirst().get();
    public int id;

    public boolean isObstacle;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    //total cost of node
    public double totalCost = 0;
    //cost of the path to a node
    public double moveCost = 0;
    //the heuristic of a node
    public double heuristicCost;
    int xPos;
    int yPos;

    @Override
    public String toString(){
        return "x: " + this.xPos + ", y: " + this.yPos + ", is obstacle: " + isObstacle;
    }

    public Node(boolean isObstacle, int xPos, int yPos) {
        this.yPos = yPos;
        this.xPos = xPos;
        this.isObstacle = isObstacle;
        this.heuristicCost = 0;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    public void addBranch(int weight, Node node) {
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }


    @Override
    public int compareTo(Node n) {
        return Double.compare(this.totalCost, n.totalCost);
    }


    public void addBranch(double weight, Node node) {
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target) {
        this.heuristicCost = Math.sqrt(Math.pow(Math.abs(this.xPos - target.xPos), 2) + Math.pow(Math.abs(this.yPos - target.yPos), 2));
        return this.heuristicCost;
    }

    public void calculateEdges(Node[][] grid) {

        int row = this.xPos;
        int col = this.yPos;

        // Check the top position
        if (row > 0 && !grid[row - 1][col].isObstacle) {
            addBranch(1, grid[row - 1][col]);
        }

        // Check the right position
        if (col < grid[0].length - 1 && !grid[row][col + 1].isObstacle) {
            addBranch(1, grid[row][col + 1]);
        }

        // Check the bottom position
        if (row < grid.length - 1 && !grid[row + 1][col].isObstacle) {
            addBranch(1, grid[row + 1][col]);
        }

        // Check the left position
        if (col > 0 && !grid[row][col - 1].isObstacle) {
            addBranch(1, grid[row][col - 1]);
        }

        // Check the top-left position
        if (row > 0 && col > 0 && !grid[row - 1][col - 1].isObstacle) {
            if (!grid[row - 1][col].isObstacle && !grid[row][col - 1].isObstacle) {
                addBranch(1.4, grid[row - 1][col - 1]);
            }
        }

        // Check the top-right position
        if (row > 0 && col < grid[0].length - 1 && !grid[row - 1][col + 1].isObstacle) {
            if (!grid[row - 1][col].isObstacle && !grid[row][col + 1].isObstacle)
                addBranch(1.4, grid[row - 1][col + 1]);
        }

        // Check the bottom-left position
        if (row < grid.length - 1 && col > 0 && !grid[row + 1][col - 1].isObstacle) {
            if (!grid[row + 1][col].isObstacle && !grid[row][col - 1].isObstacle) {
                addBranch(1.4, grid[row + 1][col - 1]);
            }
        }

        // Check the bottom-right position
        if (row < grid.length - 1 && col < grid[0].length - 1 && !grid[row + 1][col + 1].isObstacle) {
            if (!grid[row + 1][col].isObstacle && !grid[row][col + 1].isObstacle) {
                addBranch(1.4, grid[row + 1][col + 1]);
            }
        }
    }

    public static List<Coordinate> aStar(Coordinate startCoordinate, Coordinate targetCoordinate, Node[][] grid) {
        Node start = Mappers.coordinateToNode(startCoordinate);
        Node target = Mappers.coordinateToNode(targetCoordinate);

        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        List<Coordinate> coordinates = new ArrayList<>();

        start.totalCost = start.moveCost + start.calculateHeuristic(target);
        openList.add(start);


        while (!openList.isEmpty()) {
            Node currentNode = openList.peek();
            if (currentNode.xPos == target.xPos && currentNode.yPos == target.yPos) {
                while (currentNode.parent != null) {
                    coordinates.add(new Coordinate(currentNode.xPos, currentNode.yPos));
                    currentNode = currentNode.parent;
                }
                Collections.reverse(coordinates);


                return coordinates;

            }

            currentNode.calculateEdges(grid);
            for (Edge edge : currentNode.neighbors) {
                Node neighbourNode = edge.node;
                double totalWeight = currentNode.moveCost + edge.moveCost;

                if (!openList.contains(neighbourNode) && !closedList.contains(neighbourNode)) {
                    neighbourNode.parent = currentNode;
                    neighbourNode.moveCost = totalWeight;
                    neighbourNode.totalCost = neighbourNode.moveCost + neighbourNode.calculateHeuristic(target);
                    openList.add(neighbourNode);
                } else {
                    if (totalWeight < neighbourNode.moveCost) {
                        neighbourNode.parent = currentNode;
                        neighbourNode.moveCost = totalWeight;
                        neighbourNode.totalCost = neighbourNode.moveCost + neighbourNode.calculateHeuristic(target);

                        if (closedList.contains(neighbourNode)) {
                            closedList.remove(neighbourNode);
                            openList.add(neighbourNode);
                        }
                    }
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);
        }
        return null;
    }



    public static void printPath(Node target, Node[][] grid) {

        Node n = target;

        if (n == null)
            return;

        List<Integer> ids = new ArrayList<>();

        while (n.parent != null) {
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);
    }
}


