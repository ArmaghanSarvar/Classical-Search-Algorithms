package problem1;

import problem1.graph.Node;
import java.util.Vector;

public class UniformCost extends Problem1{
    private Vector<Node> frontierQueue = new Vector<>();
    private Vector<Node> visited = new Vector<>();
    private Vector<Node> explored = new Vector<>();
    private int maxMemUsed = 0;

    public UniformCost(boolean isGraph) {
        Node init = initialState();
        init.parent = null;
        init.pathcost = 0;
        frontierQueue.add(init);
        if (isGraph)
            graphUCSearch();
        else
            treeUCSearch();
        printInformation();
    }

    private void graphUCSearch() {
        Node current;
        while (!frontierQueue.isEmpty())
        {
            int k = explored.size() + frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            printEX();
            current = frontierQueue.get(lowestCostIndex());
            expanded++;
            frontierQueue.remove(lowestCostIndex());
            explored.add(current);
            if (current.isGoal()){
                printPath(current);
                return;
            }
            Vector<Node> childNodes = actions(current);
            if(!childNodes.isEmpty()) {
                for (int i = 0; i < childNodes.size(); i++) {    // search in actions
                    Node child = childNodes.get(i);
                    child.parent = current;
                    double cost = getCost(current, child);
                    child.pathcost = current.pathcost + cost;
                    if (!exploredhas(child) && !frontierhas(child)) {
                        frontierQueue.add(child);
                        if (!visited.contains(child))
                            visited.add(child);
                    } else if (frontierhas(child) ) {
                        if (repeatedInF(child).pathcost > child.pathcost){
                            frontierQueue.remove(repeatedInF(child));
                            frontierQueue.add(child);
                            if (!visited.contains(child))
                                visited.add(child);
                        }
                    }
                }
            }
        }
    }
    private Node repeatedInF(Node child){
        for (Node f : frontierQueue) {
            if (child.getValue().equals(f.getValue())){
                return f;
            }
        }
        return null;
    }

    private void treeUCSearch() {
        Node current;
        while (!frontierQueue.isEmpty())
        {
            int k = explored.size() + frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            current = frontierQueue.get(lowestCostIndex());
            expanded++;
            frontierQueue.remove(lowestCostIndex());
            if (current.isGoal()){
                printPath(current);
                return;
            }
            Vector<Node> childNodes = actions(current);
            for (int i = 0; i < childNodes.size(); i++) {    // search in actions
                Node child = childNodes.get(i);
                child.parent = current;
                double cost = getCost(current, child);
                child.pathcost = current.pathcost + cost;
                if (!frontierhas(child)) {
                    frontierQueue.add(child);
                    if (!visited.contains(child))
                        visited.add(child);
                }else if (frontierhas(child) ){
                    if (repeatedInF(child).pathcost > child.pathcost){
                        frontierQueue.remove(repeatedInF(child));
                        frontierQueue.add(child);
                    }
                }
            }
        }
    }

    private void printEX() {
        System.out.print("explored :");
        for (int i = 0; i < explored.size(); i++) {
            System.out.print(explored.get(i).getValue() + explored.get(i).pathcost + " , ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");
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

    private void printF(){
        System.out.print("frontier : ");
        for (int i = 0; i < frontierQueue.size(); i++) {
            System.out.print(frontierQueue.get(i).getValue() + frontierQueue.get(i).pathcost + " , ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");

    }

    private int lowestCostIndex(){
        double minCost = frontierQueue.get(0).pathcost;
        int minIndex = 0;
        for (int i = 1; i <frontierQueue.size() ; i++) {
            if (frontierQueue.get(i).pathcost< minCost) {
                minCost = frontierQueue.get(i).pathcost;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void printInformation(){
        System.out.println("number of visited nodes = "+ visited.size() );
        System.out.println("number of expanded nodes = " + expanded);  //generated nodes
        System.out.println("maximum memory used = " + maxMemUsed);
    }
}