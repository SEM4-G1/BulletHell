package dk.sdu.group.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
    // Id for readability of result purposes
    private static int idCounter = 0;
    public int id;

    public boolean isObstacle;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    // Hardcoded heuristic
    public double h;
    int xPos;
    int yPos;

    Node(boolean isObstacle, int xPos, int yPos){
        this.yPos = yPos;
        this.xPos = xPos;
        this.isObstacle = isObstacle;
        this.h = 0;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(int weight, Node node){
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target){
        return Math.sqrt(Math.pow(Math.abs(this.xPos-target.xPos),2)+Math.pow(Math.abs(this.yPos-target.yPos),2));
    }
    public void calculateEdges(Node[][] grid){
        int row = this.xPos;
        int col = this.yPos;

        // check the top-left, top, top-right, left, right, bottom-left, bottom, and bottom-right positions
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && (i != row || j != col)) {
                    if(!grid[i][j].isObstacle){
                        addBranch(1, grid[i][j]);
                    }
                }
            }
        }
    }

    public static Node aStar(Node start, Node target, Node[][] grid){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }
            n.calculateEdges(grid);
            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(Node target, Node[][] grid){
        
        Node n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }

        System.out.println("");
        for (int i = 0; i < grid.length; i++) {
            System.out.println("");
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j].isObstacle){
                    System.out.print("\033[0;30m" + " O ");
                    System.out.print("\033[0m");
                }
                else if(ids.contains(grid[i][j].id)){
                    System.out.print("\033[0;32m" + " O ");
                    System.out.print("\033[0m");
                }
                else {
                    System.out.print("\033[0;34m" + " O ");
                    System.out.print("\033[0m");
                }
            }
        }
    }
}
