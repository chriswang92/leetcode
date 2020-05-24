package Graph;

import java.util.*;

// This class represents a directed graph using adjacency list representation
class Graph {
    // No. of vertices
    int V;

    // Array of lists for Adjacency List Representation
    LinkedList[] adj;

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
}