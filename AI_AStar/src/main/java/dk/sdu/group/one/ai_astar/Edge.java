package dk.sdu.group.one.ai_astar;

public class Edge {
    public Edge(double weight, Node node) {
        this.weight = weight;
        this.node = node;
    }

    public double weight;
    public Node node;
}