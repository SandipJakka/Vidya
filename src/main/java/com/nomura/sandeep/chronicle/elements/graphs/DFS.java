package com.nomura.sandeep.chronicle.elements.graphs;

import com.google.common.base.Preconditions;

import java.util.*;

public class DFS {
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        DFS d = new DFS();

        Graph g1 = new Graph(5);  // Total 5 vertices in graph
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(1, 4);

        System.out.println("DFS- graph -1 :" + d.dfs(2, g));
        d.dfsRecursive(2, g);

        System.out.println("DFS: graph -2" + d.dfs(0, g1));
        d.dfsRecursive(0, g1);


        // System.out.println("BFS:" + d.bfs(2, g));
    }

    List<Integer> dfs(int start, Graph g) {
        boolean[] visited = new boolean[g.numberOfVertices];
        Stack<Integer> stack = new Stack<>();
        List<Integer> visitedNodes = new ArrayList<>();

        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            visitedNodes.add(v);
            LinkedList<Integer> edges = g.adjencyList[v];
            Iterator<Integer> iter = edges.iterator();

            while (iter.hasNext()) {
                int v1 = iter.next();
                if (!visited[v1]) {
                    visited[v1] = true;
                    stack.push(v1);
                }
            }
        }
        return visitedNodes;

    }


    void dfsRecursive(int start, Graph graph) {
        boolean[] visited = new boolean[graph.numberOfVertices];
        List<Integer> path = new ArrayList<>(graph.numberOfVertices);
        path = _dfs(start, visited, graph, path);
        path.forEach(e -> System.out.println(e));
    }

    private List<Integer> _dfs(int node, boolean[] visited, Graph graph, List<Integer> path) {
        visited[node] = true;
        path.add(node);
        LinkedList<Integer> neighbors = graph.adjencyList[node];
        for (int e : neighbors) {
            if (!visited[e]) {
                path = _dfs(e, visited, graph, path);
            }
        }

        return path;
    }

    List<Integer> bfs(int startNode, Graph g) {
        // All vertices are not-visited by default.
        boolean[] visited = new boolean[g.numberOfVertices];

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();
        queue.add(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            Integer visitedNode = queue.poll();
            visitedNodes.add(visitedNode);
            LinkedList<Integer> neighbors = g.adjencyList[visitedNode];
            Iterator<Integer> iter = neighbors.iterator();
            while (iter.hasNext()) {
                int node = iter.next();
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
        return visitedNodes;
    }

    static class Graph {
        private final int numberOfVertices;
        private final LinkedList<Integer>[] adjencyList;

        Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            adjencyList = new LinkedList[numberOfVertices];
            for (int i = 0; i < numberOfVertices; i++) {
                adjencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int from, int to) {
            Preconditions.checkArgument(from < numberOfVertices && to < numberOfVertices);
            adjencyList[from].add(to);
        }
    }
}
