package dk.sdu.group.one.astar;

public class Edge {
    public Edge(double moveCost, Node node) {
        this.moveCost = moveCost;
        this.node = node;
    }

    public double moveCost;
    public Node node;
}