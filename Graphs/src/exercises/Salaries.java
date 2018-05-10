package exercises;

import java.util.*;

public class Salaries{
    static HashMap<Integer, List<Integer>> graph;
    static HashSet<Integer> visited;
    static HashMap<Integer, Integer> salaries;


    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        graph = new HashMap<>();
        visited = new HashSet<>();
        salaries = new HashMap<>();

        for (int index = 0; index < n; index++){
            graph.put(index, new ArrayList<>());
            char[] line = scr.nextLine().toCharArray();
            for (int ch = 0; ch < line.length; ch++){
                if (line[ch] == 'Y'){
                    graph.get(index).add(ch);
                }
            }
        }
        System.out.println(DFS());
    }

    private static int DFS(){
        int totalSalaries = 0;
        for (int vertex = 0; vertex < graph.size(); vertex++){
            if (!visited.contains(vertex)){
                DFSVisit(vertex);
            }
            totalSalaries += salaries.get(vertex);
        }
        return totalSalaries;
    }

    private static void DFSVisit(int vertex){
        visited.add(vertex);
        if (graph.get(vertex).size() == 0){
            salaries.put(vertex, 1);
        }else{
            int vertexSalary = 0;
            for (Integer child : graph.get(vertex)){
                if (!visited.contains(child)){
                    DFSVisit(child);
                }
                vertexSalary += salaries.get(child);
            }

            salaries.put(vertex, vertexSalary);
        }
    }

}