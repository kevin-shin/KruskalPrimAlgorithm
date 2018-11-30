package graph;

import javafx.util.Pair;

import java.util.*;

public class Graph {

    public HashMap<Vertex, PriorityQueue<Edge>> adjList;
    private Vertex root;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1, v2);
        Edge e = new Edge(weight, v1, v2);
        if (!adjList.get(v1).contains(e)) {
            adjList.get(v1).add(e);
        }
        if (!adjList.get(v2).contains(e)) {
            adjList.get(v2).add(e);
        }

    }

    public void addVertex(Vertex v) {
        if (adjList.isEmpty()) {
            root = v;
        }
        adjList.putIfAbsent(v, new PriorityQueue<>());
    }

    public Graph prim(Vertex root) {
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);
        Edge minEdge = adjList.get(root).peek();
        PriorityQueue<Edge> allEdges = new PriorityQueue<>();
        for (PriorityQueue<Edge> edges : this.adjList.values()) {
            allEdges.addAll(edges);
        }
        while (minSpanning.adjList.size() < this.adjList.size()) {
            for (Edge edge : allEdges) {
                if (minSpanning.adjList.containsKey(edge.getVertex1()) ^
                        minSpanning.adjList.containsKey(edge.getVertex2())) {
                    minEdge = edge;
                    break;
                }
            }
            minSpanning.addVertex(minEdge.getVertex1());
            minSpanning.addVertex(minEdge.getVertex2());
            minSpanning.addEdge(minEdge.getVertex1(), minEdge.getVertex2());
        }
        return minSpanning;
    }

    public Graph prim() {
        return prim(this.root);

    }

    @Override
    public String toString() {
        String string = "Vertices: [";
        for (Vertex vertex : adjList.keySet()
                ) {
            string += vertex.toString();
        }
        String string2 = "Edges: [";
        for (Vertex vertex : adjList.keySet()) {
            for (Edge e : adjList.get(vertex)) {

                string2 += e.toString() + " ";
            }
        }
        return string + "\n" + string2;
    }
}
