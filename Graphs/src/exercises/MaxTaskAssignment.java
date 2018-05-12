package exercises;

import java.util.*;

public class MaxTaskAssignment {

    static int[][] graph;
    static int[] parents;

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int people = Integer.parseInt(scr.nextLine().split(" ")[1]);
        int tasks = Integer.parseInt(scr.nextLine().split(" ")[1]);

        int nodes = people + tasks + 2;

        graph = new int[nodes][];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new int[nodes];
        }

        for (int i = 0; i < people; i++) {
            graph[0][i + 1] = 1;
        }

        for (int i = 0; i < tasks; i++) {
            graph[i + people + 1][graph.length - 1] = 1;
        }

        for (int person = 0; person < people; person++) {
            String currentLine = scr.nextLine();
            for (int task = 0; task < tasks; task++) {
                if (currentLine.charAt(task) == 'Y') {
                    graph[person + 1][task + people + 1] = 1;
                }
            }
        }

        parents = new int[graph.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }

        int start = 0;
        int end = graph.length - 1;
        while (bfs(start, end)) {
            int currentNode = end;
            while (currentNode != start) {
                int prevNode = parents[currentNode];
                graph[prevNode][currentNode] = 0;
                graph[currentNode][prevNode] = 1;
                currentNode = prevNode;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<String> result = new TreeSet<>();
        boolean[] visited = new boolean[graph.length];
        visited[end] = true;
        queue.add(end);
        while (queue.size() > 0) {
            Integer node = queue.poll();
            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.add(child);
                    visited[child] = true;

                    if (node != end && node != start
                            && child != end && child != start) {
                        result.add((char)((char) child - 1 + 'A') + "-" + (node - people));
                    }
                }
            }
        }
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (queue.size() > 0) {
            Integer node = queue.poll();
            for (int child = 0; child < graph.length; child++) {
                if (!visited[child] && graph[node][child] > 0) {
                    queue.add(child);
                    visited[child] = true;
                    parents[child] = node;
                }
            }
        }
        return visited[end];
    }
}
