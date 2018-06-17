package com.nomura.sandeep.chronicle.elements.graphs;


import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

public class BFS {

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");
        BFS bfs = new BFS();
//        System.out.println(bfs.bfs(2, g));
//        System.out.println(bfs.bfs(0, g));
//        System.out.println(bfs.bfs(1, g));
//        System.out.println(bfs.bfs(3, g));


        boolean[][] A3 = new boolean[][]
                {
                        {false, true, true, false},
                        {false, false, true, false},
                        {true, false, false, true},
                        {false, false, false, true}
                };

        List<Coordinate> list1 = bfs.bfs(new Coordinate(2, 0, null), A3);
        System.out.println(list1.stream().filter(c -> c.parent != null).map(c -> c.parent).distinct().collect(Collectors.toSet()));


        int[][] maze = new int[][]
                {
                        {0, 1, 0, 0, 1, 1},
                        {0, 1, 1, 1, 1, 1},
                        {0, 0, 1, 1, 1, 0},
                        {1, 1, 1, 0, 0, 0},
                        {1, 1, 0, 1, 1, 0}
                };

        // List<Coordinate> list = bfs.searchMaze(new Coordinate(4, 0, null), maze);
        //Coordinate path = bfs.searchMaze(new Coordinate(4, 0, null), new Coordinate(0, 5, null), maze);
        //print(path);

        Coordinate path1 = bfs.searchMaze(new Coordinate(4, 0, null), new Coordinate(0, 1, null), maze);
        print(path1);
        // list.forEach(c -> System.out.println(c.parent));
        // List<Coordinate> uniq = list.stream().filter(c -> c.parent != null).map(c -> c.parent).distinct().collect(Collectors.toList());

        // System.out.println(uniq);

    }

    private static void print(Coordinate path) {
        while (path != null && path.parent != null) {
            System.out.print(path);
            path = path.parent;
        }
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


    List<Coordinate> bfs(Coordinate startNode, boolean[][] adjacencyMatrix) {
        boolean[][] visited = new boolean[adjacencyMatrix[0].length][adjacencyMatrix.length];
        List<Coordinate> visitedNodes = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();

        visited[startNode.x][startNode.y] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Coordinate visitedNode = queue.poll();
            visitedNodes.add(visitedNode);
            List<Coordinate> neighbors = neighbors(visitedNode, adjacencyMatrix);
            Iterator<Coordinate> iterator = neighbors.iterator();

            while (iterator.hasNext()) {
                Coordinate node = iterator.next();

                if (!visited[node.x][node.y]) {
                    visited[node.x][node.y] = true;
                    queue.add(node);
                }

            }

        }
        return visitedNodes;
    }


    List<Coordinate> searchMaze(Coordinate startNode, int[][] maze) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<Coordinate> visitedNodes = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();

        visited[startNode.x][startNode.y] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Coordinate visitedNode = queue.remove();
            visitedNodes.add(visitedNode);
            List<Coordinate> neighbors = neighborsMaze(visitedNode, maze);
            Iterator<Coordinate> iterator = neighbors.iterator();

            while (iterator.hasNext()) {
                Coordinate node = iterator.next();

                if (!visited[node.x][node.y]) {
                    visited[node.x][node.y] = true;
                    queue.add(node);
                }

            }

        }
        return visitedNodes;
    }

    Coordinate searchMaze(Coordinate startNode, Coordinate endNode, int[][] maze) {

        /**
         * That pseudo code is representing the path through the maze as a path to the leaf of a tree.
         * Each spot in the maze is a node on the tree and each new spot you can go to from there is a child of that node.

         In order to do breadth first search, an algorithm first has to consider all paths through the tree of length one,
         then length two, etc. until it reaches the end, which will cause the algorithm to stop since the end has no children, resulting in an empty queue.

         The code keeps track of the nodes it needs to visit by using a queue (Q). It first sets the start of the maze to the root of the tree,
         visits it (checks if it is the end), then removes the root from the queue and repeats the process with each child. In this way, it visits
         the nodes in post-order i.e. root, (each child of root),
         (each child of first child), (each child of second child), etc. until it gets to the end.
         */
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<Coordinate> visitedNodes = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();

        visited[startNode.x][startNode.y] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Coordinate visitedNode = queue.remove();
            if (visitedNode.equals(endNode)) {
                System.out.println("Found exit");
                return visitedNode;
            }
            visitedNodes.add(visitedNode);
            List<Coordinate> neighbors = neighborsMaze(visitedNode, maze);
            Iterator<Coordinate> iterator = neighbors.iterator();

            while (iterator.hasNext()) {
                Coordinate node = iterator.next();

                if (!visited[node.x][node.y]) {
                    visited[node.x][node.y] = true;
                    queue.add(node);
                }

            }

        }
        System.out.println("returning null");
        return null;
    }

    private List<Coordinate> neighborsMaze(Coordinate node, int[][] adjMatrix) {
        /**
         * i, j
         *
         *
         * (1,1) ==> ( 0,1) (2,1) (1,0)(1,2)   ....(i-1,j) (i+1,j) (i,j-1)(i,j+1)
         */

        int cols = adjMatrix[0].length;
        int rows = adjMatrix.length;
        int i = node.x;
        int j = node.y;
        List<Coordinate> ne = new ArrayList<>();
        if ((i - 1 >= 0) && (i - 1 < rows) && (j < cols) && (adjMatrix[i - 1][j] == 1)) {
            ne.add(new Coordinate(i - 1, j, node));
            adjMatrix[i - 1][j] = 0;
        }

        if ((i + 1 < rows) && (j < cols) && adjMatrix[i + 1][j] == 1) {
            ne.add(new Coordinate(i + 1, j, node));
            adjMatrix[i + 1][j] = 0;

        }
        if ((i < rows) && (j - 1 >= 0) && (j - 1 < cols) && (adjMatrix[i][j - 1] == 1)) {
            ne.add(new Coordinate(i, j - 1, node));
            adjMatrix[i][j - 1] = 0;

        }

        if ((i < rows) && (j + 1 < cols) && (adjMatrix[i][j + 1] == 1)) {
            ne.add(new Coordinate(i, j + 1, node));
            adjMatrix[i][j + 1] = 0;
        }

        return ne;
    }

    private List<Coordinate> neighbors(Coordinate node, boolean[][] adjMatrix) {


//        int rows = adjMatrix[0].length;
        int cols = adjMatrix.length;
        int i = node.x;
        int j = node.y;
        List<Coordinate> ne = new ArrayList<>();

        for (int x = 0; x < cols; x++) {
            if (adjMatrix[j][x] && (x != i)) {
                ne.add(new Coordinate(j, x, node));
            }
        }
        return ne;
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

    static class Coordinate {
        final int x;
        final int y;
        final Coordinate parent;

        Coordinate(int x, int y, Coordinate parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (!Coordinate.class.isInstance(obj)) {
                return false;
            }
            Coordinate that = (Coordinate) obj;
            return (that.x == this.x) && (that.y == this.y);
        }
    }

}