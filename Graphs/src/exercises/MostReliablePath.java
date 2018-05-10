package exercises;

import java.util.*;
import java.util.stream.Collectors;

public class MostReliablePath {
    static Map<Integer, List<Edge>> graph;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int nodes = Integer.parseInt(scr.nextLine().split(" ")[1]);
        String[] path = scr.nextLine().split(" ");
        int startPoint = Integer.parseInt(path[1]);
        int endPoint = Integer.parseInt(path[3]);
        int edges = Integer.parseInt(scr.nextLine().split(" ")[1]);
        graph = new HashMap<>();

        for(int i = 0; i < edges; i++){
            String[] edgeParts = scr.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(edgeParts[0]),
                    Integer.parseInt(edgeParts[1]),
                    Integer.parseInt(edgeParts[2]));
            if(!graph.containsKey(edge.first)){
                graph.put(edge.first, new ArrayList<>());
            }
            if(!graph.containsKey(edge.second)){
                graph.put(edge.second, new ArrayList<>());
            }
            graph.get(edge.first).add(edge);
            graph.get(edge.second).add(edge);
        }

        double[] percentages = new double[graph.size()];
        for (int i = 0; i < percentages.length; i++) {
            percentages[i] = -1;
        }

        percentages[startPoint] = 100;
        boolean[] visited = new boolean[graph.size()];
        visited[startPoint] = true;
        int[] prev = new int[graph.size()];
        prev[startPoint] = -1;
        Queue<Integer> queue = new PriorityQueue<>(
                100, (a,b) -> (int)(percentages[b] - percentages[a]));
        queue.add(startPoint);

        while (queue.size() > 0){
            Integer min = queue.poll();
            if(percentages[min] == -1){
                break;
            }
            for(Edge child : graph.get(min)) {
                int otherNode = child.first == min ? child.second : child.first;
                if (!visited[otherNode]) {
                    visited[otherNode] = true;
                    queue.add(otherNode);
                }

                double newPerc = percentages[min] / 100 * child.cost;
                if(percentages[otherNode] < newPerc){
                    prev[otherNode] = min;
                    percentages[otherNode] = newPerc;
                    queue.add(queue.poll());
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        int index = endPoint;
        while (true){
            result.add(index);
            index = prev[index];
            if(index == -1){
                break;
            }
        }

        Collections.reverse(result);
        System.out.printf("Most reliable path reliability: %.2f%%%n", percentages[endPoint]);
        System.out.println(String.join(" -> ", result.stream().map(a -> a + "").collect(Collectors.toList())));
    }
}

