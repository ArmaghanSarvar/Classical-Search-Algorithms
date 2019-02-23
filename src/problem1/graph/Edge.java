package problem1.graph;

public class Edge{
    public final double cost;
    public final Node target;

    public Edge(Node target, double cost){
        this.cost = cost;
        this.target = target;
    }
}