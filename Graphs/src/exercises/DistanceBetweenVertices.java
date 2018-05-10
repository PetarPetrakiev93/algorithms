package exercises;

import java.util.*;

public class DistanceBetweenVertices{
    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = Integer.parseInt(scr.nextLine());
        int p = Integer.parseInt(scr.nextLine());
        for(int i = 0; i < n; i++){
            String lineArgs = scr.nextLine();
            Integer parent = Integer.parseInt(lineArgs.split(":")[0]);
            if(lineArgs.split(":").length != 1) {
                String[] nodesArgs = lineArgs.split(":")[1].split(" ");

                List<Integer> nodes = new ArrayList<>();
                for (String nodesArg : nodesArgs) {
                    nodes.add(Integer.parseInt(nodesArg));
                }
                graph.put(parent, nodes);
            }else{
                graph.put(parent, new ArrayList<>());
            }

        }
        for(int i = 0; i < p; i++){
            String[] pairs = scr.nextLine().split("-");
            System.out.printf("{%d, %d} -> %d%n", Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1]),
                    findDistanceBetweenVertices(graph, Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1])));
        }
    }

    private static Integer findDistanceBetweenVertices(Map<Integer, List<Integer>> graph, Integer start, Integer destination){
        Map<Integer, Integer> distanceFromRoot = new HashMap<>();
        for(Integer vertex : graph.keySet()){
            distanceFromRoot.put(vertex, -1);
        }
        distanceFromRoot.put(start, (distanceFromRoot.get(start) + 1));
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while(queue.size() != 0){
            Integer currentVertex = queue.poll();
            for(Integer child : graph.get(currentVertex)){
                if(distanceFromRoot.get(child) == -1){
                    distanceFromRoot.put(child, (distanceFromRoot.get(currentVertex) + 1));
                    queue.add(child);
                }
                if(child == destination){
                    break;
                }
            }
        }
        return  distanceFromRoot.get(destination);
    }
}
