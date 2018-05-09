package kruskal;

import java.util.ArrayList;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        edges.sort(Edge::compareTo);
        int[] parent = new int[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++){
            parent[i] = i;
        }
        List<Edge> spannigTree = new ArrayList<>();
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
