package exercises;

import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles{
    static Map<String, List<String>> graph;

    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        graph = new TreeMap<>();
        String line = scr.nextLine();
        while(!line.equals("")){
            String[] vertexArgs = line.split(" -> ");
            graph.put(vertexArgs[0], new ArrayList<>());
            String[] children = vertexArgs[1].split(" ");
            for(String child : children){
                graph.get(vertexArgs[0]).add(child);
            }
            try{
                line = scr.nextLine();
            }catch(Exception e){
                break;
            }
        }

        List<String> result = new ArrayList<>();

        for(Map.Entry<String, List<String>> entry : graph.entrySet()){
            String start = entry.getKey();
            for(String end : graph.get(start).stream().
                    sorted().collect(Collectors.toList())){
                graph.get(start).remove(end);
                graph.get(end).remove(start);
                if(hasPath(start, end)){
                    result.add(String.format("%s - %s", start, end));
                }else{
                    graph.get(start).add(end);
                    graph.get(end).add(start);
                }

            }
        }
        System.out.println("Edges to remove: " + result.size());
        for(String r : result){
            System.out.println(r);
        }
    }

    public static boolean hasPath(String start, String end){
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while(queue.size() > 0){
            String currentVertex = queue.poll();
            for(String child : graph.get(currentVertex)){
                if(!visited.contains(child)){
                    visited.add(child);
                    queue.add(child);

                    if(child.equals(end)){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}