package problem1;

import problem1.graph.Node;
import java.util.Stack;
import java.util.Vector;

public class IterativeDFS extends Problem1{
    private Stack<problem1.graph.Node> frontier =new Stack<problem1.graph.Node>();
    private Vector<problem1.graph.Node> explored = new Vector<>();
    private Vector<problem1.graph.Node> visited = new Vector<>();
    private boolean cutOff = false;
    private int maxMemUsed = 0;

    public IterativeDFS(boolean isGraph) {
        Node init = initialState();
        frontier.add(init);
        IDSSearch(isGraph);
        printInformation();
    }

    private void IDSSearch(boolean isGraph) {
        if (isGraph) {
            int depth = 0;
            while (!cutOff) {
                graph_Depth_Limited_Search(depth);
                depth++;
            }
        }
        else {
            int depth = 0;
            while (!cutOff) {
                tree_Depth_Limited_Search(depth);
                depth++;
            }
        }
    }

    public void graph_Depth_Limited_Search(int depth_Limit) {
        while (true){
            printF();
            printEX();
            int k = explored.size() + frontier.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            if (frontier.empty()) {
                return;
            }
            if (!leftinF(depth_Limit)){
                return;
            }
            Node current = frontier.get(lowestDepth());
            frontier.remove(lowestDepth());
            expanded++;
            explored.add(current);
            if (current.getValue().equals("Arad"))
                current.depth = 0;
            for(int i = 0; i < actions(current).size() ; i++) {
                Node child = actions(current).get(i);
                child.parent = current;
                if(!exploredhas(child) && !frontierhas(child)) {
                    if (child.isGoal()){
                        cutOff = true;
                        printPath(child);
                        return;
                    }
                    child.depth = current.depth + 1;
                    frontier.push(child);
                    if (!visited.contains(child))
                        visited.add(child);
                }
            }
        }
    }

    public void tree_Depth_Limited_Search(int depth_Limit) {
        while (true){
        printF();
        int k = frontier.size();
        if (k > maxMemUsed)
            maxMemUsed = k;
        if (frontier.empty()) {
            return;
        }
        if (!leftinF(depth_Limit)){
            return;
        }
        Node current = frontier.get(lowestDepth());
        frontier.remove(lowestDepth());
        expanded++;
        if (current.getValue().equals("Arad"))
            current.depth = 0;
        for(int i = 0; i < actions(current).size() ; i++) {
            Node child = actions(current).get(i);
            child.parent = current;
            if(!frontierhas(child)) {
                if (child.isGoal()){
                    cutOff = true;
                    printPath(child);
                    return;
                }
                child.depth = current.depth + 1;
                frontier.push(child);
                if (!visited.contains(child))
                    visited.add(child);
            }
        }
    }
    }

    private void printEX() {
        System.out.print("explored :");
        for (int i = 0; i < explored.size(); i++) {
            System.out.print(explored.get(i).getValue() + " , ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");
    }
    private void printF(){
        System.out.print("frontier : ");
        for (int i = 0; i < frontier.size(); i++) {
            System.out.print(frontier.get(i).getValue() + " , ");
        }
        System.out.println();
    }

    private void printInformation(){
        System.out.println("number of visited nodes = "+ visited.size() );
        System.out.println("number of expanded nodes = " + expanded);  //generated nodes
        System.out.println("maximum memory used = " + maxMemUsed);
    }

    private int lowestDepth(){
        double minDepth = frontier.get(0).depth;
        int minIndex = 0;
        for (int i = frontier.size() - 1; i >=1 ; i--) {
            if (frontier.get(i).depth < minDepth) {
                minDepth = frontier.get(i).depth;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private boolean leftinF(int limit){
        for (int i = 0; i < frontier.size(); i++) {
            if (frontier.get(i).depth < limit) {
//                System.out.println(frontier.get(i).depth + " and " + limit);
                return true;
            }
        }
        return false;
    }

    private boolean exploredhas(Node n){
        for (Node anExplored : explored) {
            if (n.getValue().equals(anExplored.getValue()))
                return true;
        }
        return false;
    }

    private boolean frontierhas(Node n){
        for (Node f : frontier) {
            if (n.getValue().equals(f.getValue()))
                return true;
        }
        return false;
    }
}