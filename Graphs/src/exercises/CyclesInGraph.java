package exercises;

import java.util.*;
import java.util.stream.Collectors;

public class CyclesInGraph{
    static HashMap<Character, List<Character>> graph;
    static HashSet<Character> visited;
    static boolean hasCycles = false;

    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        graph = new HashMap<>();
        visited = new HashSet<>();
        String line = scr.nextLine();
        while (!line.equals("")){
            String[] nodes = line.split("–");
            if (!graph.containsKey(nodes[0].charAt(0))){
                graph.put(nodes[0].charAt(0), new ArrayList<Character>());
            }
            graph.get(nodes[0].charAt(0)).add(nodes[1].charAt(0));
            try{
                line = scr.nextLine();
            }catch(Exception e){
                break;
            }
        }
        hasCycles();
        System.out.printf("Acyclic: %s", (hasCycles ? "No" : "Yes"));
    }


    private static void hasCycles(){
        for (Character vertex : graph.keySet()){
            if (!visited.contains(vertex)){
                DFS(vertex);
            }
        }
    }

    private static void DFS(char vertex){
        visited.add(vertex);
        if (graph.containsKey(vertex)){

            for (Character child : graph.get(vertex)){
                if (visited.contains(child)){
                    hasCycles = true;
                    break;
                }

                DFS(child);
            }
            for (Map.Entry<Character, List<Character>> stringListEntry : graph.entrySet()) {
                stringListEntry.setValue(stringListEntry.getValue().stream()
                        .filter(a -> !a.equals(vertex))
                        .collect(Collectors.toList()));
            }
        }
    }


}