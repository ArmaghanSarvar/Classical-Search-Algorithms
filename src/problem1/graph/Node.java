package problem1.graph;

import java.util.Vector;

public class Node {
    private String value;
    public Node parent;
    public double pathcost;
    public double heuristic;
    public double fScore;
    public Vector<Edge> adjNodes;
    public int depth;

    public Node(String value) {
        this.value = value;
        adjNodes = new Vector<>();
    }

    public String getValue() {
        return value;
    }

    public boolean isGoal() {
        return this.value.equals("Bucharest");
    }

}
