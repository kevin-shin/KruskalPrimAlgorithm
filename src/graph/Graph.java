package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;

    public Graph() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(String label) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(label)) {
                return v;
            }
        }
        return null;
    }

    public void addEdge(Edge... e) {
        this.edges.addAll(Arrays.asList(e));
    }

    public void addVertex(Vertex... v) {
        this.vertices.addAll(Arrays.asList(v));
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }
}
