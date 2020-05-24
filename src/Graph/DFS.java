package Graph;

import java.util.Iterator;
import java.util.LinkedList;

/*
What is BFS: Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree (See method 2 of this post).
    The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.

    In this implementation, to avoid processing a node more than once, we use a boolean visited array.
    For simplicity, it is assumed that all vertices are reachable from the starting vertex.

 */
public class DFS {
    Graph graph;
    DFS(Graph graph) {
        this.graph = graph;
    }
    // Recursive action performed on each Node
    void DFSUtil(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s+"  ");
        LinkedList<Integer> adjNodes = graph.adj[s];
        Iterator<Integer> i = adjNodes.listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    void DFS(int s) {
        boolean visited[] = new boolean[graph.V];

        DFSUtil(s, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 0);
        g.addEdge(3, 3);

        DFS dfs = new DFS(g);
        System.out.println("\nFollowing is Depth First Traversal (starting from vertex 2)");
        dfs.DFS(1);

        g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        dfs.graph = g;
        System.out.println("\nFollowing is Depth First Traversal (starting from vertex 2)");
        dfs.DFS(1);
    }
}
