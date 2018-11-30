package graph;

import javafx.util.Pair;

import java.util.*;

public class Graph {

    private HashMap<Vertex, TreeSet<Edge>> adjList;
    private Vertex root;

    public Graph() {
        adjList = new HashMap<>();
    }

    public Edge addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1, v2);
        Edge e = new Edge(weight, v1, v2);
        adjList.get(v1).add(e);
        adjList.get(v2).add(e);

        return e;
    }

    public Edge addEdge(Edge e) {
        return addEdge(e.getVertex1(), e.getVertex2());
    }

    public void addVertex(Vertex... v) {
        if (adjList.isEmpty()) {
            root = v[0];
        }
        for (Vertex w : v) {
            adjList.putIfAbsent(w, new TreeSet<>());
        }
    }

    public boolean containsVertex(Vertex v) {
        return this.getAdjList().containsKey(v);
    }

    public Graph prim(Vertex root) {
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);
        Edge minEdge = this.adjList.get(root).first();
        TreeSet<Edge> allEdges = new TreeSet<>(this.adjList.get(root));

        while (minSpanning.getAdjList().size() < this.adjList.size()) {
            for (Edge edge : allEdges) {
                Vertex vertex1 = edge.getVertex1();
                Vertex vertex2 = edge.getVertex2();
                if (minSpanning.containsVertex(vertex1) ^ minSpanning.containsVertex(vertex2)) {
                    minEdge = edge;
                    break;
                }
            }

            Vertex added = minSpanning.containsVertex(minEdge.getVertex1()) ?
                    minEdge.getVertex2() : minEdge.getVertex1();
            minSpanning.addVertex(added);
            minSpanning.addEdge(minEdge);
            allEdges.addAll(this.adjList.get(added));
        }
        return minSpanning;
    }

    public Graph prim() {
        return prim(this.root);

    }

    public HashMap<Vertex, TreeSet<Edge>> getAdjList() {
        return adjList;
    }

    @Override
    public String toString() {
        StringBuilder vertices = new StringBuilder("Graph:{\n\tVertices:");
        vertices.append(adjList.keySet().toString());

        StringBuilder edges = new StringBuilder("\tEdges:");
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Vertex vertex : adjList.keySet()) {
            for (Edge e : adjList.get(vertex)) {
                if (!pq.contains(e)) {
                    pq.add(e);
                }
            }
        }
        edges.append(pq.toString());
        return vertices.append("\n").append(edges.toString()).append("\n}").toString();
    }
}
