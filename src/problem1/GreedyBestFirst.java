package problem1;

import problem1.graph.Node;

import java.util.Vector;

public class GreedyBestFirst extends Problem1 {
    private Vector<Node> frontierQueue = new Vector<>();
    private Vector<Node> visited = new Vector<>();
    private Vector<Node> explored = new Vector<>();
    private int maxMemUsed = 0;

    public GreedyBestFirst(boolean isGraph) {
        readHeuristics();
        Node init = initialState();
        frontierQueue.add(init);
        if (isGraph)
            graphGreedyBFS();
        else
            treeGreedyBFS();
        printInformation();

    }

    private void graphGreedyBFS() {
        Node current;
        while (!frontierQueue.isEmpty()) {
            int k = explored.size() + frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            printEX();
            current = frontierQueue.get(lowestHeuIndex());
            expanded++;
            frontierQueue.remove(lowestHeuIndex());
            explored.add(current);
            if (current.isGoal()) {
                printPath(current);
                return;
            }

            Vector<Node> childNodes = actions(current);
            if (!childNodes.isEmpty()) {
                for (int i = 0; i < childNodes.size(); i++) {    // search in actions
                    Node child = childNodes.get(i);
                    if (!exploredhas(child) && !frontierhas(child)) {
                        child.parent = current;
                        frontierQueue.add(child);
                        if (!visited.contains(child))
                            visited.add(child);
                    }
                }
            }
        }
    }

    private void treeGreedyBFS() {
        Node current;
        while (!frontierQueue.isEmpty()) {
            int k = frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            current = frontierQueue.get(lowestHeuIndex());
            expanded++;
            frontierQueue.remove(lowestHeuIndex());
            if (current.isGoal()) {
                printPath(current);
                return;
            }

            Vector<Node> childNodes = actions(current);
            if (!childNodes.isEmpty()) {
                for (int i = 0; i < childNodes.size(); i++) {    // search in actions
                    Node child = childNodes.get(i);
                    if (!frontierhas(child)) {
                        child.parent = current;
                        frontierQueue.add(child);
                        if (!visited.contains(child))
                            visited.add(child);
                    }
                }
            }
        }
    }

    private int lowestHeuIndex(){
        double minHeu = frontierQueue.get(0).heuristic;
        int minIndex = 0;
        for (int i = 1; i <frontierQueue.size() ; i++) {
            if (frontierQueue.get(i).heuristic< minHeu) {
                minHeu = frontierQueue.get(i).heuristic;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private boolean exploredhas(Node n){
        for (Node anExplored : explored) {
            if (n.getValue().equals(anExplored.getValue()))
                return true;
        }
        return false;
    }

    private boolean frontierhas(Node n){
        for (Node f : frontierQueue) {
            if (n.getValue().equals(f.getValue()))
                return true;
        }
        return false;
    }

    private void printEX() {
        System.out.print("explored :");
        for (int i = 0; i < explored.size(); i++) {
            System.out.print(explored.get(i).getValue() + explored.get(i).heuristic + " , ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");
    }
    private void printF(){
        System.out.print("frontier : ");
        for (int i = 0; i < frontierQueue.size(); i++) {
            System.out.print(frontierQueue.get(i).getValue() + frontierQueue.get(i).heuristic + " , ");
        }
        System.out.println();
    }

    private void printInformation(){
        System.out.println("number of visited nodes = "+ visited.size() );
        System.out.println("number of expanded nodes = " + expanded);  //generated nodes
        System.out.println("maximum memory used = " + maxMemUsed);
    }
}

