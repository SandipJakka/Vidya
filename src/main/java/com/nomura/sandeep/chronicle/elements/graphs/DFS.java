package com.nomura.sandeep.chronicle.elements.graphs;

import com.google.common.base.Preconditions;

import java.util.*;

import static java.util.stream.Collectors.toList;

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
        System.out.printf("Path exists between : %d and %d = %s \n", 1, 3, d.checkIfPathExists(1, 3, g));
        System.out.printf("Path exists between : %d and %d = %s \n", 3, 1, d.checkIfPathExists(3, 1, g));

        System.out.println(d.findComponents(g));

        System.out.println("DFS: graph -2" + d.dfs(0, g1));
        d.dfsRecursive(0, g1);

        System.out.println(d.findComponents(g1));

        // System.out.println("BFS:" + d.bfs(2, g));
    }

    /**
     * One of the actual application of DFS. This is not the shortest path, but just a check whether a
     * path exists or not...
     */
    private boolean checkIfPathExists(int from, int to, Graph graph) {
        List<Integer> path = dfs(from, graph);
        return path.contains(to);
    }


    /**
     * One more application of DFS in which we are finding the connected components..
     * Idea is :
     * <p>
     * 1) Start DFS at every node ( unless visited )
     * 2) Mark all the reachable nodes as being part of same group/component ( specifically with the sameId ).
     */
    private List<Integer> findComponents(Graph graph) {
        boolean[] visited = new boolean[graph.numberOfVertices];
        int count = 0;
        int[] components = new int[graph.numberOfVertices];

        for (int indx = 0; indx < graph.numberOfVertices; indx++) {
            if (!visited[indx]) {
                count++;
                components[indx] = count;
                visited[indx] = true;
                components = _modifiedDfs(indx, graph, visited, count, components);
            }
        }
        return Arrays.stream(components).boxed().
                collect(toList());
    }

    private int[] _modifiedDfs(int indx, Graph graph, boolean[] visited, int count, int[] components) {
        visited[indx] = true;
        components[indx] = count;
        for (int node : graph.adjencyList[indx]) {
            if (!visited[node]) {
                components[node] = count;
                components = _modifiedDfs(node, graph, visited, count, components);
            }
        }
        return components;
    }


    private List<Integer> dfs(int start, Graph g) {
        boolean[] visited = new boolean[g.numberOfVertices];
        Stack<Integer> stack = new Stack<>();
        List<Integer> visitedNodes = new ArrayList<>();

        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            visitedNodes.add(v);
            LinkedList<Integer> edges = g.adjencyList[v];
            for (Integer v1 : edges) {
                if (!visited[v1]) {
                    visited[v1] = true;
                    stack.push(v1);
                }
            }
        }
        return visitedNodes;
    }


    private void dfsRecursive(int start, Graph graph) {
        boolean[] visited = new boolean[graph.numberOfVertices];
        List<Integer> path = new ArrayList<>(graph.numberOfVertices);
        path = _dfs(start, visited, graph, path);
        path.forEach(System.out::println);
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
            for (Integer node : neighbors) {
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
        return visitedNodes;
    }

    private static class Graph {
        private final int numberOfVertices;
        //array of linked lists
        private final LinkedList<Integer>[] adjencyList;

        Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            //noinspection unchecked
            adjencyList = new LinkedList[numberOfVertices];
            for (int i = 0; i < numberOfVertices; i++) {
                adjencyList[i] = new LinkedList<>();
            }
        }

        void addEdge(int from, int to) {
            Preconditions.checkArgument(from < numberOfVertices && to < numberOfVertices);
            adjencyList[from].add(to);
        }
    }
}