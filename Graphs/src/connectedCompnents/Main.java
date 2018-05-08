package connectedCompnents;

import java.util.*;

public class Main {
    static boolean[] visited;
    static List<List<Integer>> graph;
    static Deque<Integer> components;

    public static void main(String[] args) {
        graph = readGraph();
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.println(connectedComponent);
        }
    }

    private static List<List<Integer>> readGraph() {
        Scanner in = new Scanner(System.in);

        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();

            String line = in.nextLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");

            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }

            graph.add(connectedComponents);
        }
        return graph;
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        visited = new boolean[graph.size()];
        for(int startNode = 0; startNode < graph.size(); startNode++){
            if(!visited[startNode]){
                //System.out.print("Connected component:");
                components = new ArrayDeque<>();
                DFS(startNode, graph);
                connectedComponents.add(components);
                //System.out.println();
            }
        }
        return connectedComponents;
    }

    private static void DFS(Integer vertex, List<List<Integer>> graph){
        if(!visited[vertex]){
            visited[vertex] = true;
            for (Integer child : graph.get(vertex)) {
                DFS(child, graph);
            }
            components.add(vertex);
            //System.out.print(" " + vertex);
        }
    }
}
