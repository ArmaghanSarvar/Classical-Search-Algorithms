package problem1;

import problem1.graph.Node;

import java.util.Stack;
import java.util.Vector;

public class UnlimitedDFS extends Problem1 {
    Stack<Node> frontier =new Stack<Node>();
    private Vector<Node> visited = new Vector<>();
    private Vector<Node> explored = new Vector<>();
    private int maxMemUsed = 0;

    public UnlimitedDFS(boolean isGraph) {
        frontier.add(initialState());
        if (isGraph)
            graphDfsSearch();
        else
            treeDfsSearch();
        printInformation();
    }

    private void graphDfsSearch(){
        while(!frontier.isEmpty())
        {
            int k = explored.size() + frontier.size();
            if (k > maxMemUsed)
                maxMemUsed = k;
            printF();
            printEX();
            Node current = frontier.pop();
            expanded++;
            explored.add(current);
            // if destination has been reached
            Vector<Node> childNodes = actions(current);
            for(int i = 0; i < childNodes.size(); i++){
                 Node child = childNodes.get(i);
                 child.parent = current;
                 if(!exploredhas(child) && !frontierhas(child)){
                     if(child.isGoal()) {
                         printPath(child);
                         return;
                     }
                     frontier.push(child);
                     if (!visited.contains(child))
                         visited.add(child);
                 }
            }
        }
    }

    private void treeDfsSearch(){
        while(!frontier.isEmpty())
        {
            int k = explored.size() + frontier.size();
            if (k > maxMemUsed)
                maxMemUsed = k;            printF();
            Node current = frontier.pop();
            expanded++;
            Vector<Node> childNodes = actions(current);
            if(!childNodes.isEmpty()){
                for(int i = 0; i < childNodes.size(); i++){
                    Node child = childNodes.get(i);
                    child.parent = current;
                    if(!frontierhas(child)){
                        if(child.isGoal()) {
                            printPath(child);
                            return;
                        }
                        frontier.push(child);
                        if (!visited.contains(child))
                            visited.add(child);
                    }
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
        for (Node f : frontier) {
            if (n.getValue().equals(f.getValue()))
                return true;
        }
        return false;
    }
    private void printF(){
        System.out.print("frontier : ");

        for (int i = 0; i < frontier.size(); i++) {
            System.out.print(frontier.get(i).getValue() + " , ");
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
