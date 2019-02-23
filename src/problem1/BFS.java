package problem1;

import problem1.graph.Node;

import java.util.Vector;

public class BFS extends Problem1 {
    private Vector<Node> frontierQueue = new Vector<>();
    private Vector<Node> explored = new Vector<>();
    private Vector<Node> visited = new Vector<>();
    private int maxMemUsed = 0;

    public BFS(boolean isGraph) {
        frontierQueue.add(initialState());
        visited.add(initialState());
        if (isGraph)
            graphBfsSearch();
        else
            treeBfsSearch();
        printInformation();
    }

    private void graphBfsSearch() {
        Node current;
        while (!frontierQueue.isEmpty())
        {
            int k = explored.size() + frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            printEX();
            current = frontierQueue.lastElement();
            frontierQueue.remove(frontierQueue.lastElement());
            expanded++;
            explored.add(current);
            Vector<Node> acts = actions(current);
            for (int i = 0; i < acts.size(); i++) {
                Node child = acts.get(i);
                child.parent = current;
                if (!exploredhas(child) && !frontierhas(child)) {
                    if (child.isGoal()){
                        printPath(child);
                        return;
                    }
                    frontierQueue.add(child);
                    if (!visited.contains(child))
                        visited.add(child);
                }
            }
        }
    }

    private void treeBfsSearch(){
        Node current;
        while (!frontierQueue.isEmpty())
        {
            int k = explored.size() + frontierQueue.size();
            if (k > maxMemUsed)
                maxMemUsed = k;            printF();
            current = frontierQueue.lastElement();
            expanded++;
            frontierQueue.remove(frontierQueue.lastElement());
            Vector<Node> acts = actions(current);
            for (int i = 0; i < acts.size(); i++) {
                Node child = acts.get(i);
                child.parent = current;
                if (!frontierhas(child)) {
                    if (child.isGoal()){
                        printPath(child);
                        return;
                    }
                    frontierQueue.add(child);
                    if (!visited.contains(child))
                        visited.add(child);
                }
            }
        }
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
            System.out.print(frontierQueue.get(i).getValue() + " , ");
        }
        System.out.println();
    }

    private void printEX(){
        System.out.print("explored :");
        for (int i = 0; i < explored.size(); i++) {
            System.out.print(explored.get(i).getValue() + " , ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");
    }

    private void printInformation(){
        System.out.println("number of visited nodes = "+ visited.size() );
        System.out.println("number of expanded nodes = " + expanded);  //generated nodes
        System.out.println("maximum memory used = " + maxMemUsed);
    }
}