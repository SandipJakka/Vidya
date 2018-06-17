package com.nomura.sandeep.chronicle.elements.graphs;

import com.google.common.base.Preconditions;

import java.util.LinkedList;

public class Graph {
    public final int numberOfVertices;
    public final LinkedList<Integer>[] adjencyList;
    public final LinkedList<Edge>[] adjacencyListWithWeight;

    Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        adjencyList = new LinkedList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjencyList[i] = new LinkedList<>();
        }
        adjacencyListWithWeight = new LinkedList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyListWithWeight[i] = new LinkedList<>();

        }
    }

    public void withEdge(int from, int to) {
        Preconditions.checkArgument(from < numberOfVertices && to < numberOfVertices);
        adjencyList[from].add(to);
    }

    public void withWeight(int from, int to, double weight) {
        Preconditions.checkArgument(from < numberOfVertices && to < numberOfVertices);
        Edge edge = new Edge(from, to, weight);
        adjacencyListWithWeight[from].add(edge);
    }

    public void withWeightAndEdgeDistance(int from, int to, double weight, int edgeDistance) {
        Preconditions.checkArgument(from < numberOfVertices && to < numberOfVertices);
        Edge edge = new Edge(from, to, weight, edgeDistance);
        adjacencyListWithWeight[from].add(edge);
    }

    static class Edge {
        public final int from;
        public final int to;
        public final double weight;

        public int edgeDistance;

        Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        Edge(int from, int to, double weight, int edgeDistance) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.edgeDistance = edgeDistance;
        }
    }
}