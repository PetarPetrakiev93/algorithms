package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int n = graph[0].length;
        int[] distance = new int[n];
        for(int i = 0; i < n; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0;
        boolean[] used = new boolean[n];
        int[] previous = new int[n];
        while (true){
            int minNode = 0;
            int minDistance = Integer.MAX_VALUE;

            for(int node = 0; node < n; node++){
                if(!used[node] && distance[node] < minDistance){
                    minNode = node;
                    minDistance = distance[node];
                }
            }

            if(minDistance == Integer.MAX_VALUE){
                break;
            }

            used[minNode] = true;
            for (int node = 0; node < n; node++){
                if (graph[minNode][node] > 0){
                    int newDistance = distance[minNode] + graph[minNode][node];
                    if (newDistance < distance[node]){
                        distance[node] = newDistance;
                        previous[node] = minNode;
                    }
                }
            }
        }

        if(distance[destinationNode] == Integer.MAX_VALUE){
            return null;
        }
        List<Integer> path = new ArrayList<>();
        int currentNode = destinationNode;
        while (true){
            path.add(currentNode);
            currentNode = previous[currentNode];
            if(currentNode == 0){
                path.add(currentNode);
                break;
            }
        }

        Collections.reverse(path);

        return path;
    }
}
