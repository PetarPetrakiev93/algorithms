package topologicalSort;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Map<String, Integer> predecessorCount;

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        Map<String, List<String>> graph = new HashMap<>();
        String line = scr.nextLine();
        while (!line.equals("")){
            String parent = line.split(" -> ")[0];
            parent = parent.substring(1, parent.length()-1);
            if(line.split(" -> ").length != 1) {
                String[] nodesArgs = line.split(" -> ")[1].split(", ");

                List<String> nodes = new ArrayList<>();
                for (String nodesArg : nodesArgs) {
                    nodes.add(nodesArg.substring(1, nodesArg.length() - 1));
                }
                graph.put(parent, nodes);
            }else{
                graph.put(parent, new ArrayList<>());
            }

            line = scr.nextLine();
        }

        List<String> sorted = (List<String>) topSort(graph);
        String test = "";
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        getPredecessotCount(graph);
        List<String> sorted = new ArrayList<>();
        while (true){
            Optional<String> nodeToRemove = predecessorCount.keySet().stream()
                    .filter(x -> predecessorCount.get(x) == 0)
                    .findFirst();
            if(!nodeToRemove.isPresent()){
                break;
            }
            for (Map.Entry<String, List<String>> stringListEntry : graph.entrySet()) {
                stringListEntry.setValue(stringListEntry.getValue().stream()
                        .filter(a -> !a.equals(nodeToRemove.get()))
                        .collect(Collectors.toList()));
            }
            graph.remove(nodeToRemove.get());
            sorted.add(nodeToRemove.get());
            getPredecessotCount(graph);
        }
        if(graph.size() > 0){
            throw new IllegalArgumentException();
        }
        return sorted;
    }

    private static void getPredecessotCount(Map<String, List<String>> graph){
        predecessorCount = new HashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            if(!predecessorCount.containsKey(node.getKey())){
                predecessorCount.put(node.getKey(), 0);
            }
            for (String child : node.getValue()) {
                if(!predecessorCount.containsKey(child)){
                    predecessorCount.put(child, 0);
                }

                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }
    }
}
