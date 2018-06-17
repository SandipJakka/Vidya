package com.nomura.sandeep.chronicle.elements.graphs;

import java.util.Arrays;
import java.util.List;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
//@formatter:off
/**
 *
 *
 * Used to identify the negative cycles.
 * Time Complexity : O(VE)
 * Application : Medium to large graphs
 *              Identifies single shortest path ( SSP )
 *              Doesn't identify All path shortest paths
 *
 */
//@formatter:on

public class BellmanFord {

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.withWeight(0, 1, 5.0);
        graph.withWeight(1, 2, 20.0);
        graph.withWeight(1, 5, 30.0);

        graph.withWeight(1, 6, 60.0);
        graph.withWeight(2, 3, 10.0);
        graph.withWeight(2, 4, 75.0);
        graph.withWeight(3, 2, -15.0);
        graph.withWeight(5, 6, 5.0);
        graph.withWeight(5, 8, 50.0);
        graph.withWeight(5, 4, 25.0);
        graph.withWeight(6, 7, -50.0);
        graph.withWeight(7, 8, -10.0);
        graph.withWeight(4, 9, 100.0);

        BellmanFord b = new BellmanFord();
        b.printResult(b.bellmanFord(graph, 0), 0);
    }

    public double[] bellmanFord(Graph graph, int startNode) {
        int v = graph.numberOfVertices;
        double[] distancesFromStartNode = new double[v];
        Arrays.fill(distancesFromStartNode, POSITIVE_INFINITY);

        distancesFromStartNode[startNode] = 0;

        //calculate optimal path
        for (int i = 0; i < v; i++) {
            List<Graph.Edge> edges = graph.adjacencyListWithWeight[i];
            for (Graph.Edge e : edges) {
                if (distancesFromStartNode[e.from] + e.weight < distancesFromStartNode[e.to]) {
                    distancesFromStartNode[e.to] = distancesFromStartNode[e.from] + e.weight;
                }
            }
        }


        printResult(distancesFromStartNode, startNode);
        System.out.printf("======================\n");

        //marks the negative cycles.
        for (int i = 0; i < v; i++) {
            List<Graph.Edge> edges = graph.adjacencyListWithWeight[i];
            for (Graph.Edge e : edges) {
                if (distancesFromStartNode[e.from] + e.weight < distancesFromStartNode[e.to]) {
                    distancesFromStartNode[e.to] = NEGATIVE_INFINITY;
                    //since we already have calculated the optimal path...any better path occouring is the evidence for
                    // negative cycle.
                }
            }
        }
        return distancesFromStartNode;
    }

    void printResult(double[] distances, int startNode) {
        for (int index = 0; index < distances.length; index++) {
            if (distances[index] == POSITIVE_INFINITY) {
                System.out.printf("No path exists from %d->%d \n", startNode, index);
            } else if (distances[index] == NEGATIVE_INFINITY) {
                System.out.printf("Negative cycle exists with %d->%d \n", startNode, index);
            } else {
                System.out.printf("Path between %d -> %d is %f \n", startNode, index, distances[index]);
            }
        }
    }

}