package exercises;


import java.util.*;
class Graph
{
    private int V, E;
    private LinkedList<Integer> adj[];
    static int count = 0, time = 0;

    class Edge
    {
        int u;
        int v;
        Edge(int u, int v)
        {
            this.u = u;
            this.v = v;
        }
    };

    Graph(int v)
    {
        V = v;
        E = 0;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v,int w)
    {
        adj[v].add(w);
        E++;
    }

    // A recursive function that finds and prints strongly connected
    // components using DFS traversal
    // u --> The vertex to be visited next
    // disc[] --> Stores discovery times of visited vertices
    // low[] -- >> earliest visited vertex (the vertex with minimum
    //             discovery time) that can be reached from subtree
    //             rooted with current vertex
    // *st -- >> To store visited edges
    void BCCUtil(int u, int disc[], int low[], LinkedList<Edge>st,
                 int parent[])
    {

        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;
        int children = 0;

        // Go through all vertices adjacent to this
        Iterator<Integer> it = adj[u].iterator();
        while (it.hasNext()){
            int v = it.next();  // v is current adjacent of 'u'

            // If v is not visited yet, then recur for it
            if (disc[v] == -1){
                children++;
                parent[v] = u;

                // store the edge in stack
                st.add(new Edge(u,v));
                BCCUtil(v, disc, low, st, parent);

                // Check if the subtree rooted with 'v' has a
                // connection to one of the ancestors of 'u'
                // Case 1 -- per Strongly Connected Components Article
                if (low[u] > low[v])
                    low[u] = low[v];

                // If u is an articulation point,
                // pop all edges from stack till u -- v
                if ( (disc[u] == 1 && children > 1) ||
                        (disc[u] > 1 && low[v] >= disc[u]) ){
                    while (st.getLast().u != u || st.getLast().v != v){
                        //System.out.print(st.getLast().u + "--" +
                        //        st.getLast().v + " ");
                        st.removeLast();
                    }
                   //System.out.println(st.getLast().u + "--" +
                   //        st.getLast().v + " ");
                    st.removeLast();
                    count++;
                }
            }

            // Update low value of 'u' only of 'v' is still in stack
            // (i.e. it's a back edge, not cross edge).
            // Case 2 -- per Strongly Connected Components Article
            else if (v != parent[u] && disc[v] < low[u]){
                if (low[u]>disc[v])
                    low[u]=disc[v];
                st.add(new Edge(u,v));
            }
        }
    }

    // The function to do DFS traversal. It uses BCCUtil()
    void BCC(){
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        LinkedList<Edge> st = new LinkedList<Edge>();

        // Initialize disc and low, and parent arrays
        for (int i = 0; i < V; i++){
            disc[i] = -1;
            low[i] = -1;
            parent[i] = -1;
        }

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                BCCUtil(i, disc, low, st, parent);
            int j = 0;

            // If stack is not empty, pop all edges from stack
            while (st.size() > 0){
                j = 1;
                //System.out.print(st.getLast().u + "--" +
                //        st.getLast().v + " ");
                st.removeLast();
            }
            if (j == 1){
                //System.out.println();
                count++;
            }
        }
    }

    public static void main(String args[]) {
        Scanner scr = new Scanner(System.in);
        int nodes = Integer.parseInt(scr.nextLine().split(" ")[1]);
        Graph g = new Graph(nodes);
        int edges = Integer.parseInt(scr.nextLine().split(" ")[1]);
        for (int i = 0; i < edges; i++) {
            String line = scr.nextLine();
            g.addEdge(Integer.parseInt(line.split(" ")[0]),
                    Integer.parseInt(line.split(" ")[1]));
            g.addEdge(Integer.parseInt(line.split(" ")[1]),
                    Integer.parseInt(line.split(" ")[0]));
        }

        g.BCC();

        System.out.println("Number of bi-connected components: " + g.count);
    }
}