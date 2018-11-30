package graph;

import javafx.util.Pair;

import java.util.*;

public class Graph {

    private HashMap<Vertex, PriorityQueue<Edge>> adjList;
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

    public void addEdge(Edge e) {
        addEdge(e.getVertex1(),e.getVertex2());
    }

    public void addVertex(Vertex... v) {
        if (adjList.isEmpty()) {
            root = v[0];
        }
        for (Vertex w : v) {
            adjList.putIfAbsent(w, new PriorityQueue<>());
        }
    }

    public Graph prim(Vertex root) {
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);
        Edge minEdge = this.adjList.get(root).peek();
        PriorityQueue<Edge> allEdges = new PriorityQueue<>(this.adjList.get(root));

        while (minSpanning.getAdjList().size() < this.adjList.size()) {
            for (Edge edge : allEdges) {
                if (minSpanning.getAdjList().containsKey(edge.getVertex1()) ^
                        minSpanning.getAdjList().containsKey(edge.getVertex2())) {
                    minEdge = edge;
                    break;
                }
            }

            Vertex added = minSpanning.getAdjList().containsKey(minEdge.getVertex1())
                    ? minEdge.getVertex2() : minEdge.getVertex2();
            minSpanning.addVertex(added);
            minSpanning.addEdge(minEdge);

            for (Edge e : this.adjList.get(added)) {
                if (!allEdges.contains(e)) allEdges.add(e);
            }
        }
        return minSpanning;
    }

    public Graph prim() {
        return prim(this.root);

    }

    public HashMap<Vertex, PriorityQueue<Edge>> getAdjList() {
        return adjList;
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
