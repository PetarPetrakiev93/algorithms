package stronglyConnectedComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponents {

    private static int size;
    private static boolean[] visited;
    private static List<Integer>[] graph;
    private static List<List<Integer>> stronglyConnectedComponents;
    private static Stack<Integer> dfsNodesStack;
    private static List<Integer>[] reverseGraph;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        graph = targetGraph;
        size = graph.length;
        visited = new boolean[size];
        dfsNodesStack = new Stack<>();
        buildReverseGraph();

        for(int node = 0; node < size; node++){
            if(!visited[node]) {
                DFS(node);
            }
        }

        visited = new boolean[size];

        while (dfsNodesStack.size() > 0){
            Integer node = dfsNodesStack.pop();
            if(!visited[node]){
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDFS(node);
            }
        }
        Collections.reverse(stronglyConnectedComponents);
        return stronglyConnectedComponents;
    }

    private static void reverseDFS(Integer node) {
        if(!visited[node]){
            visited[node] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size()-1).add(node);
            for(Integer child : reverseGraph[node]){
                reverseDFS(child);
            }
        }
    }

    private static void buildReverseGraph() {
        reverseGraph = new ArrayList[size];
        for(int node = 0; node < size; node++){
            reverseGraph[node] = new ArrayList<>();
        }

        for(int node = 0; node < size; node++){
            for(Integer child : graph[node]){
                reverseGraph[child].add(node);
            }
        }
    }

    private static void DFS(Integer node){
        if(!visited[node]){
            visited[node] = true;
            for(Integer child : graph[node]){
                DFS(child);
            }
            dfsNodesStack.push(node);
        }
    }
}
