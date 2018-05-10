package exercises;

import kruskal.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModifiedKruskalAlgorithm {
    static List<Edge> graphEdges;
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int nodes = Integer.parseInt(scr.nextLine().split(" ")[1]);
        int edges = Integer.parseInt(scr.nextLine().split(" ")[1]);
        graphEdges = new ArrayList<>();
        for(int i = 0; i < edges; i++) {
            String[] edgeParts = scr.nextLine().split(" ");
            kruskal.Edge edge = new kruskal.Edge(Integer.parseInt(edgeParts[0]),
                    Integer.parseInt(edgeParts[1]),
                    Integer.parseInt(edgeParts[2]));
            graphEdges.add(edge);
        }

        List<Edge> kruskal = kruskal(nodes, graphEdges);
        int weight = kruskal.stream().map(a -> a.getWeight()).reduce((a, b) -> a + b).get();
        System.out.println("Minimum spanning forest weight: " + weight);
        for (Edge edge : kruskal) {
            System.out.println(edge);
        }
    }

    public static List<kruskal.Edge> kruskal(int numberOfVertices, List<kruskal.Edge> edges) {
        edges.sort(kruskal.Edge::compareTo);
        int[] parent = new int[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++){
            parent[i] = i;
        }
        List<kruskal.Edge> spannigTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.getStartNode(), parent);
            int rootEndNode = findRoot(edge.getEndNode(), parent);
            if(rootStartNode != rootEndNode){
                spannigTree.add(edge);
                parent[rootStartNode] = rootEndNode;
            }
        }

        return spannigTree;
    }

    public static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root){
            root = parent[root];
        }

        while (node != root){
            int oldParent = parent[node];
            parent[node] = root;
            node = oldParent;
        }

        return root;
    }
}


