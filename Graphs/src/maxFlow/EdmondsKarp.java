package maxFlow;

import java.util.ArrayDeque;
import java.util.Queue;

public class EdmondsKarp {
    private static int[][] graph;
    private static int[] parents;

    public static int findMaxFlow(int[][]targetGraph) {
        graph = targetGraph;
        parents = new int[graph.length];

        for(int i = 0; i < graph.length; i++){
            parents[i] = -1;
        }
        int maxFlow = 0;
        int start = 0;
        int end = graph.length - 1;

        while (BFS(start, end)){
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = end;

            while (currentNode != start){
                int prevNode = parents[currentNode];
                int currentFlow = graph[prevNode][currentNode];
                if(currentFlow > 0 && currentFlow < pathFlow){
                    pathFlow = currentFlow;
                }
                currentNode = prevNode;
            }

            maxFlow += pathFlow;

            currentNode = end;
            while (currentNode != start){
                int prevNode = parents[currentNode];
                graph[prevNode][currentNode] -= pathFlow;
                graph[currentNode][prevNode] += pathFlow;

                currentNode = prevNode;
            }
        }

        return maxFlow;
    }

    private static boolean BFS(int start, int end){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (queue.size() > 0){
            Integer node = queue.poll();
            for(int child = 0; child < graph[node].length; child++){
                if(graph[node][child] > 0 && !visited[child]){
                    queue.add(child);
                    parents[child] = node;
                    visited[child] = true;
                }
            }
        }
        return visited[end];
    }
}
