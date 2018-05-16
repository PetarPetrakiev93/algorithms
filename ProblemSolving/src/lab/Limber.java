package lab;

import java.util.*;

public class Limber{
    public static boolean[] visited;

    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        String[] lineArgs = scr.nextLine().split(" ");
        int numberOfLogs = Integer.parseInt(lineArgs[0]);
        int queries = Integer.parseInt(lineArgs[1]);
        List<Log> logs = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[numberOfLogs+1];

        for(int i = 0; i < numberOfLogs; i++){
            lineArgs = scr.nextLine().split(" ");
            Log newLog = new Log(i+1, Integer.parseInt(lineArgs[0]),
                    Integer.parseInt(lineArgs[1]),
                    Integer.parseInt(lineArgs[2]),
                    Integer.parseInt(lineArgs[3]));

            graph[newLog.id] = new ArrayList<>();

            for(Log log : logs){
                if(log.intersect(newLog)){
                    graph[log.id].add(newLog.id);
                    graph[newLog.id].add(log.id);
                }
            }
            logs.add(newLog);
        }

        int[] components = assignNodesToComponents(numberOfLogs, graph);

        //for(int component: components){
        //    System.out.println(component);
        //}

        for(int i = 0; i < queries; i++){
            lineArgs = scr.nextLine().split(" ");
            int first = Integer.parseInt(lineArgs[0]);
            int second = Integer.parseInt(lineArgs[1]);
            if(components[first] == components[second]){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    private static int[] assignNodesToComponents(int logsCount, List<Integer>[] graph){
        visited = new boolean[logsCount + 1];
        int[] components = new int[logsCount + 1]; // node (logId) => componentId
        int componentId = 0;

        for (int node = 1; node <= logsCount; node++){
            if (!visited[node]){
                dfsTraversal(node, graph, components, componentId);
                componentId++;
            }
        }
        return components;
    }

    private static void dfsTraversal(int node, List<Integer>[] graph, int[] components, int componentId){
        visited[node] = true;
        components[node] = componentId;
        for(Integer child : graph[node]){
            if (!visited[child]){
                dfsTraversal(child, graph, components, componentId);
            }
        }
    }
}

class Log{
    int id;
    int xA;
    int yA;
    int xB;
    int yB;

    Log(int id, int xA, int yA, int xB, int yB){
        this.id = id;
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
    }

    public boolean intersect(Log other){
        return (this.xA <= other.xB && this.xB >= other.xA && this.yA >= other.yB && this.yB <= other.yA);
    }
}
