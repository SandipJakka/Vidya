package com.nomura.sandeep.chronicle.elements.graphs;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Integer.MIN_VALUE;

public class DjikstrasApplication {

    private MapWithPriorityQueue<Integer, Node> mapWithPriorityQueue = new MapWithPriorityQueue<>((o1, o2) -> {
        if (o1.totalWeight > o2.totalWeight) {
            return 1;
        } else if (o1.totalWeight < o2.totalWeight) {
            return -1;
        } else {
            //if (o1.totalWeight == o2.totalWeight)
            return (o1.edgeDistance < o2.edgeDistance) ? -1 : ((o1.edgeDistance == o2.edgeDistance) ? 0 : 1);
        }
    });

    //private final PriorityQueue<Node> maxQueue = new PriorityQueue<>();

    public static void main(String[] args) {
        Graph graph = new Graph(12);
        graph.withWeightAndEdgeDistance(0, 1, 3, 1);
        graph.withWeightAndEdgeDistance(0, 2, 2, 1);
        graph.withWeightAndEdgeDistance(1, 0, 4, 1);
        graph.withWeightAndEdgeDistance(1, 10, 1, 1);
        graph.withWeightAndEdgeDistance(2, 4, 8, 1);
        graph.withWeightAndEdgeDistance(10, 8, 1, 1);
        graph.withWeightAndEdgeDistance(8, 9, 6, 1);
        graph.withWeightAndEdgeDistance(9, 11, 7, 1);
        graph.withWeightAndEdgeDistance(9, 5, 1, 1);
        graph.withWeightAndEdgeDistance(5, 6, 6, 1);
        graph.withWeightAndEdgeDistance(6, 5, 7, 1);
        graph.withWeightAndEdgeDistance(6, 7, 4, 1);
        graph.withWeightAndEdgeDistance(11, 8, 9, 1);
        graph.withWeightAndEdgeDistance(4, 3, 7, 1);
        graph.withWeightAndEdgeDistance(3, 2, 5, 1);
        graph.withWeightAndEdgeDistance(3, 7, 5, 1);

        DjikstrasApplication application = new DjikstrasApplication();
        application.findShortestPathWithMinNumberOfVertices(graph, 0, 7);
    }

    /**
     * Finds the shortest path with the minimum number of vertices in the path .
     *
     * @param graph
     * @param startNode
     * @param endNode
     */
    public void findShortestPathWithMinNumberOfVertices(Graph graph, int startNode, int endNode) {
        //contains the child ( node ) <- parent ( node that fetched the node )
        Map<Integer, Integer> childParentMap = new HashMap<>();

        //contains the vertex and the max distances
        Map<Integer, Node> vertexToMaxDistances = new HashMap<>();


        //initialize the priorityQ with all the distances marked from starting node as infinity;
        for (int i = 0; i < graph.numberOfVertices; i++) {
            mapWithPriorityQueue.insertOrUpdate(i, new Node(i, POSITIVE_INFINITY, MIN_VALUE));
        }

        // set the value of the startNode to be 0
        mapWithPriorityQueue.insertOrUpdate(0, new Node(startNode, 0, 0));

        LinkedList<Graph.Edge> adjacencyList[] = graph.adjacencyListWithWeight;
        childParentMap.put(startNode, MIN_VALUE);

        while (!mapWithPriorityQueue.isEmpty()) {
            Node currentMinNode = mapWithPriorityQueue.getMinimum();
            // System.out.printf("Minimum : %s \n", currentMinNode);
            // mapWithPriorityQueue.print();
            int fromId = currentMinNode.id;

            //
            vertexToMaxDistances.put(fromId, currentMinNode);

            for (Graph.Edge edge : adjacencyList[fromId]) {
                int toId = edge.to;
                if (mapWithPriorityQueue.isPresent(toId)) {
                    Node node = mapWithPriorityQueue.get(toId);

                    // relaxation step
                    if ((edge.weight + vertexToMaxDistances.get(fromId).totalWeight < node.totalWeight) ||
                            ((edge.weight + vertexToMaxDistances.get(fromId).totalWeight) == node.totalWeight)
                                    && (1 + vertexToMaxDistances.get(fromId).edgeDistance) < node.edgeDistance) {
                        node.totalWeight = edge.weight + vertexToMaxDistances.get(fromId).totalWeight;
                        node.edgeDistance = 1 + vertexToMaxDistances.get(fromId).edgeDistance;
                        mapWithPriorityQueue.insertOrUpdate(node.id, node);

                        //add or update
                        childParentMap.put(node.id, fromId);
                    }
                }
            }
            // mapWithPriorityQueue.print();
        }

        int current = endNode;

        System.out.printf("Shortest path with minimum nodes as as below: ");
        //assuming that there is a path //TODO FIX ...BIG BAD assumption...
        while (current != MIN_VALUE) { // sentinel value that indicates we finished the path
            System.out.printf("[%d] <--", current);
            current = childParentMap.get(current);
        }
        System.out.println("");
        System.out.printf("Weight : %s \n", vertexToMaxDistances.get(endNode));

        System.out.println("=============================================");

        childParentMap.forEach((k, v) -> System.out.printf("%d -> %d \n", k, v));
        vertexToMaxDistances.forEach((k, v) -> System.out.printf("%d -> %s \n", k, v));

    }

    static class Node {
        int id;
        double totalWeight;
        int edgeDistance;

        public Node(int id, double weight, int edgeDistance) {
            this.id = id;
            this.totalWeight = weight;
            this.edgeDistance = edgeDistance;
        }

        public Node(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node that = (Node) obj;
                return that.id == this.id;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "id = " + id + ", weight = " + totalWeight + ", edgeDistance = " + edgeDistance;
        }
    }

}

