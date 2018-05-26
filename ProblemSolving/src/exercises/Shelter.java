package exercises;

import java.util.*;

public class Shelter {
    static int[] work;
    static int[] bfsDist;
    static int capacity;
    static int endNode;
    static List<Integer>[] edges;
    static int[][] capacities;
    static double[][] distanceMatrix;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] lineArgs = scr.nextLine().split(" ");
        int soldierCount = Integer.parseInt(lineArgs[0]);
        int bunkerCount = Integer.parseInt(lineArgs[1]);
        capacity = Integer.parseInt(lineArgs[2]);
        Point[] soldiers = new Point[soldierCount+1];
        List<Double> distances = new ArrayList<>();

        for (int i = 1; i <= soldierCount; i++) {
            lineArgs = scr.nextLine().split(" ");
            int x = Integer.parseInt(lineArgs[0]);
            int y = Integer.parseInt(lineArgs[1]);
            soldiers[i] = new Point(x, y);
        }

        distanceMatrix = new double[bunkerCount+1][];
        for (int i = 1; i <= bunkerCount; i++) {
            lineArgs = scr.nextLine().split(" ");
            distanceMatrix[i] = new double[soldierCount+1];
            for (int j = 1; j <= soldierCount; j++) {
                int x = Integer.parseInt(lineArgs[0]);
                int y = Integer.parseInt(lineArgs[1]);
                int distanceX = x - soldiers[j].x;
                int distanceY = y - soldiers[j].y;
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                distances.add(distance);
                distanceMatrix[i][j] = distance;
            }
        }

        endNode = soldierCount + bunkerCount + 1;
        work = new int[endNode+1];
        bfsDist = new int[endNode+1];
        capacities = new int[endNode+1][];
        for (int i = 0; i <= endNode; i++)
        {
            capacities[i] = new int[endNode + 1];
        }
        Collections.sort(distances);

        double bestDistance = distances.get(distances.size()-1);
        int low = 0;
        int high = distances.size() - 1;
        while (low <= high){
            int mid = (low + high)/2;
            int res = dinicConstrained(distances.get(mid), soldierCount, bunkerCount);
            if(res == soldierCount){
                bestDistance = Math.min(bestDistance, distances.get(mid));
                high = mid - 1;
            }else{
                low = mid+1;
            }
        }

        System.out.printf("%.6f", bestDistance);
    }

    private static int dinicConstrained(Double maxWeight, int soldierCount, int bunkerCount) {
        edges = new ArrayList[endNode + 1];
        edges[0] = new ArrayList<>();
        for (int i = 1; i <= soldierCount; i++) {
            edges[i] = new ArrayList<>();
            edges[0].add(i);
            edges[i].add(0);
            capacities[0][i] = 1;
            capacities[i][0] = 0;
        }

        edges[endNode] = new ArrayList<>();
        for (int i = 1; i <= bunkerCount; i++) {
            //Bunkers will be numbered N + 1 to N + M
            edges[soldierCount + i] = new ArrayList<>();
            edges[soldierCount + i].add(endNode);
            edges[endNode].add(soldierCount + i);
            capacities[soldierCount + i][endNode] = capacity;
            capacities[endNode][soldierCount + i] = 0;

            for (int j = 1; j <= soldierCount; j++) {
                if (distanceMatrix[i][j] <= maxWeight) {
                    edges[j].add(soldierCount + i);
                    edges[soldierCount + i].add(j);
                    capacities[j][soldierCount + i] = 1;
                    capacities[soldierCount + i][j] = 0;
                }
            }
        }

        return dinic(0, endNode);
    }

    private static int dinic(int source, int destination) {
        int result = 0;
        while (bfs(source, destination)){
            for (int i = 0; i < work.length; i++) {
                work[i] = 0;
            }
            int delta = 0;
            do{
                delta = dfs(source, Integer.MAX_VALUE);
                result += delta;
            }while (delta != 0);
        }
        return result;
    }

    static boolean bfs(int src, int dest) {
        for (int i = 0; i < bfsDist.length; i++) {
            bfsDist[i] = -1;
        }
        bfsDist[src] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        while (queue.size() > 0){
            int currentNode = queue.poll();
            for (int i = 0; i < edges[currentNode].size(); i++){
                int child = edges[currentNode].get(i);
                if (bfsDist[child] < 0 && capacities[currentNode][child] > 0) {
                    bfsDist[child] = bfsDist[currentNode] + 1;
                    queue.add(child);
                }
            }
        }
        return bfsDist[dest] >= 0;
    }

    static int dfs(int source, int flow){
        if (source == endNode){
            return flow;
        }
        for (int i = work[source]; i < edges[source].size(); i++, work[source]++){
            int child = edges[source].get(i);
            if (capacities[source][child] <= 0) {
                continue;
            }
            if (bfsDist[child] == bfsDist[source] + 1){
                int augmentationPathFlow = dfs(child, Math.min(flow, capacities[source][child]));
                if (augmentationPathFlow > 0){
                    capacities[source][child] -= augmentationPathFlow;
                    capacities[child][source] += augmentationPathFlow;
                    return augmentationPathFlow;
                }
            }
        }
        return 0;
    }
}

class Point{
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
