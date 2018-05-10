package exercises;

import java.util.*;

public class CableNetwork {

    static Map<Integer, List<Edge>> graph;
    static Set<Integer> spannigTree;
    static int totalBudget;
    static int usedBudget = 0;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        totalBudget = Integer.parseInt(scr.nextLine().split(" ")[1]);
        int nodes = Integer.parseInt(scr.nextLine().split(" ")[1]);
        int edges = Integer.parseInt(scr.nextLine().split(" ")[1]);
        graph = new HashMap<>();
        spannigTree = new HashSet<>();

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
            if(edgeParts.length > 3){
                spannigTree.add(edge.first);
                spannigTree.add(edge.second);
            }
        }

        prim();
        System.out.println("Budget used: " + usedBudget);
    }

    public static void prim(){
        Queue<Edge> pQueue = new PriorityQueue<>(100, (a, b) -> a.cost-b.cost);
        spannigTree.forEach(e -> pQueue.addAll(graph.get(e)));

        while (pQueue.size() > 0){
            Edge min = pQueue.poll();
            Integer nonTreeNode = -1;

            if(spannigTree.contains(min.first) && !spannigTree.contains(min.second)){
                nonTreeNode = min.second;
            }
            if(!spannigTree.contains(min.first) && spannigTree.contains(min.second)){
                nonTreeNode = min.first;
            }

            if(nonTreeNode == -1){
                continue;
            }

            if(totalBudget >= min.cost){
                totalBudget -= min.cost;
                usedBudget += min.cost;
            }else{
                break;
            }

            spannigTree.add(nonTreeNode);
            pQueue.addAll(graph.get(nonTreeNode));
        }
    }
}

class Edge{
    public int first;
    public int second;
    public int cost;

    public Edge(int first, int second, int cost) {
        this.first = first;
        this.second = second;
        this.cost = cost;
    }
}
