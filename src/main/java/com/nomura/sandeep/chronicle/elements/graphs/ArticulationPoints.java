package com.nomura.sandeep.chronicle.elements.graphs;

import java.util.List;

import static java.lang.Math.min;

public class ArticulationPoints {

    int id = 0;
    int rootNodeOutGoingEdges = 0;

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

        ArticulationPoints bridges = new ArticulationPoints();
        bridges.findArticulationPoints(graph);
    }

    void findArticulationPoints(Graph graph) {
        int numberOfVertices = graph.numberOfVertices;
        boolean[] visited = new boolean[numberOfVertices];
        boolean[] articulationPoints = new boolean[numberOfVertices];
        int[] low = new int[numberOfVertices];
        int[] ids = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i]) {
                rootNodeOutGoingEdges = 0;
                dfs(i, -1, i, low, ids, visited, graph, articulationPoints);
                articulationPoints[i] = (articulationPoints[i] && rootNodeOutGoingEdges > 1);
            }
        }

        for (int index = 0; index < articulationPoints.length; index++) {
            if (articulationPoints[index]) {
                System.out.printf("Articulation point = %d \n", index);
            }
        }
    }

    private void dfs(int from, int parent, int root, int[] low, int[] ids, boolean[] visited, Graph graph, boolean[] articulationPoints) {
        System.out.printf("from = %d, parent = %d, root = %d \n", from, parent, root);
        if (root == parent) {
            rootNodeOutGoingEdges++;
        }
        visited[from] = true;
        low[from] = ids[from] = id++;
        List<Integer> neighbors = graph.adjencyList[from];
        for (int to : neighbors) {
            if (to == parent) {
                continue;
            }
            if (!visited[to]) {
                dfs(to, from, root, low, ids, visited, graph, articulationPoints);
                low[from] = min(low[from], low[to]);
                /**
                 * id[from] < low [to] ==> Bridge
                 * id[from] = low[to] ==> Circle
                 */
                if (ids[from] <= low[to]) {
                    articulationPoints[to] = true;
                }
            } else {
                low[from] = min(low[from], ids[to]);
            }
        }
    }
}