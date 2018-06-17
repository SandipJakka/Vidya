package com.nomura.sandeep.chronicle.elements.graphs;

import java.util.Arrays;
import java.util.List;

public class TopSort {

    int index = 0;

    public static void main(String[] args) {
        Graph graph = new Graph(11);
        graph.withEdge(0, 1);
        graph.withEdge(0, 2);
        graph.withEdge(0, 2);
        graph.withEdge(1, 3);
        graph.withEdge(1, 4);
        graph.withEdge(2, 4);
        graph.withEdge(2, 5);
        graph.withEdge(3, 6);
        graph.withEdge(3, 7);
        graph.withEdge(4, 7);
        graph.withEdge(4, 8);
        graph.withEdge(5, 8);
        graph.withEdge(5, 9);
        graph.withEdge(6, 10);
        graph.withEdge(8, 10);
        graph.withEdge(9, 10);

        TopSort topSort = new TopSort();
        topSort.topSort(graph);
    }

    public void topSort(Graph graph) {
        int numberOfVertices = graph.numberOfVertices;
        boolean[] visited = new boolean[numberOfVertices];
        int[] order = new int[numberOfVertices];
        index = numberOfVertices;

        /** Since we need to visit "all" the nodes. **/
        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, order);
            }
        }
        Arrays.stream(order).forEach(n -> System.out.printf("%d -> ", n));
        System.out.println("");
    }

    private void dfs(int node, Graph graph, boolean[] visited, int[] order) {
        visited[node] = true;

        List<Integer> neighbors = graph.adjencyList[node];
        for (int e : neighbors) {
            if (!visited[e]) {
                dfs(e, graph, visited, order);
            }
        }
        index = index - 1;
        order[index] = node;
    }

}