package dk.sdu.group.one.map;

public class Edge {
    Edge(double weight, Node node) {
        this.weight = weight;
        this.node = node;
    }

    public double weight;
    public Node node;
}