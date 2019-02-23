package problem1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.runAlgorithm();
    }

    private void runAlgorithm(){
        System.out.println("Enter the Search Algorithm and type");
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
        switch (search) {
            case "graph bfs":
                new BFS(true);
                break;
            case "tree bfs":
                new BFS(false);
                break;
            case "graph dfs":
                new UnlimitedDFS(true);
                break;
            case "tree dfs":
                new UnlimitedDFS(false);
                break;
            case "graph limited dfs":
                System.out.println("enter the depth");
                String k = scanner.nextLine();
                new DepthLimitedDFS(true,Integer.parseInt(k));
                break;
            case "tree limited dfs":
                System.out.println("enter the depth");
                String l = scanner.nextLine();
                new DepthLimitedDFS(false,Integer.parseInt(l));
                break;
            case "graph iterative dfs":
                new IterativeDFS(true);
                break;
            case "tree iterative dfs":
                new IterativeDFS(false);
                break;
            case "graph uc":
                new UniformCost(true);
                break;
            case "tree uc":
                new UniformCost(false);
                break;
            case "graph GBFS":
                new GreedyBestFirst(true);
                break;
            case "tree GBFS":
                new GreedyBestFirst(false);
                break;
            case "graph astar":
                new Astar(true);
                break;
            case "tree astar":
                new Astar(false);
                break;
        }
    }
}
