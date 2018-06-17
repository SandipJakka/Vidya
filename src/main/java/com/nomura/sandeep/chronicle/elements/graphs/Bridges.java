package com.nomura.sandeep.chronicle.elements.graphs;


import java.util.ArrayList;
import java.util.List;

public class Bridges {

    int id = 0;

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.withEdge(0, 1);
        graph.withEdge(1, 2);
        graph.withEdge(1, 3);
        graph.withEdge(1, 6);
        graph.withEdge(2, 0);
        graph.withEdge(5, 3);
        graph.withEdge(4, 5);
        graph.withEdge(4, 3);
        graph.withEdge(6, 7);

        Bridges bridges = new Bridges();
        bridges.findBridges(graph);
    }

    void findBridges(Graph graph) {
        List<Integer> bridges = new ArrayList<>();
        int numberOfVertices = graph.numberOfVertices;
        boolean[] visited = new boolean[numberOfVertices];
        int[] low = new int[numberOfVertices];
        int[] ids = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, low, ids, visited, bridges, graph);
            }
        }

        for (int i = 0; i < bridges.size(); i += 2) {
            System.out.printf("Bridges between : ( %d  and %d)", bridges.get(i), bridges.get(i + 1));
            System.out.println("");
        }
    }

    private void dfs(int node, int parent, int[] low, int[] ids, boolean[] visited, List<Integer> bridges, Graph graph) {
        visited[node] = true;
        low[node] = ids[node] = id++;

        List<Integer> neighbors = graph.adjencyList[node];

        for (int e : neighbors) {
            if (e == parent) {
                continue;
            }
            if (!visited[e]) {
                dfs(e, node, low, ids, visited, bridges, graph);
                low[node] = Math.min(low[node], low[e]);
                if (ids[node] < low[e]) {
                    bridges.add(node);
                    bridges.add(e);
                }
            } else {
                low[node] = Math.min(low[node], ids[e]);
            }
        }
    }
}
