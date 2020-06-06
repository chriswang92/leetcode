package Graph;

import java.util.Iterator;
import java.util.LinkedList;

/*
What is DFS: Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
    One starts at the root (selecting some arbitrary node as the root in the case of a graph) and
    explores as far as possible along each branch before backtracking.

 */
public class BFS {
    Graph graph;
    BFS(Graph graph) {
        this.graph = graph;
    }

    // Iterative action doing BFS from start node s
    void BFSUtil(int s, boolean[] visited, LinkedList<Integer> queue) {
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s+ "  ");

            // Iterate over all adjacent nodes of current node
            Iterator<Integer> i = graph.adj[s].listIterator();
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
        boolean visited[] = new boolean[graph.V];
        // Create a queue to store adjacent nodes of current node
        LinkedList<Integer> queue = new LinkedList<>();

        BFSUtil(s, visited, queue);
    }
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 0);
        g.addEdge(3, 3);

        BFS bfs = new BFS(g);
        System.out.println("\ng1\nFollowing is Breadth First Traversal (starting from vertex 2)");
        bfs.BFS(2);

        g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        bfs.graph = g;
        System.out.println("\ng2\nFollowing is Breadth First Traversal (starting from vertex 2)");
        bfs.BFS(2);
    }
}
