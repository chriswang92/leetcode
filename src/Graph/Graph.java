package Graph;


import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class Graph {
    // No. of vertices
    private int V;

    // Array of lists for Adjacency List Representation
    private LinkedList[] adj;

    Graph(int v) {
        this.V = v;
        this.adj = new LinkedList[v];
        for (int i=0;i<v;++i) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // Iterative action doing BFS from start node s
    void BFSUtil(int s, boolean[] visited, LinkedList<Integer> queue) {
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s+ "  ");

            // Iterate over all adjacent nodes of current node
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void BFS(int s) {
        boolean visited[] = new boolean[V];
        // Create a queue to store adjacent nodes of current node
        LinkedList<Integer> queue = new LinkedList<>();

        BFSUtil(s, visited, queue);
    }

    // Recursive action performed on each Node
    void DFSUtil(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s+"  ");
        LinkedList<Integer> adjNodes = adj[s];
        Iterator<Integer> i = adjNodes.listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    void DFS(int s) {
        boolean visited[] = new boolean[V];

        DFSUtil(s, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);

        g.addEdge(2, 0);
//        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("\ng1\nFollowing is Breadth First Traversal (starting from vertex 2)");
        g.BFS(2);
        System.out.println("\nFollowing is Depth First Traversal (starting from vertex 2)");
        g.DFS(2);

        Graph g2 = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);

        g.addEdge(2, 3);
//        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("\ng2\nFollowing is Breadth First Traversal (starting from vertex 2)");
        g.BFS(2);
        System.out.println("\nFollowing is Depth First Traversal (starting from vertex 2)");
        g.DFS(1);
    }
}