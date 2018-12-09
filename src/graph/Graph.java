package graph;

import java.util.*;

public class Graph {

    private HashMap<Vertex, TreeSet<Edge>> adjListEdges;
    private Vertex root;

    public Graph() {
        adjListEdges = new HashMap<>();
    }

    public Edge addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1, v2);
        Edge e = new Edge(weight, v1, v2);
        adjListEdges.get(v1).add(e);
        adjListEdges.get(v2).add(e);

        return e;
    }

    public Edge addEdge(Edge e) {
        return addEdge(e.getVertex1(), e.getVertex2());
    }

    public void addVertex(Vertex... v) {
        if (adjListEdges.isEmpty()) {
            root = v[0];
        }
        for (Vertex w : v) {
            adjListEdges.putIfAbsent(w, new TreeSet<>());
        }
    }

    public boolean containsVertex(Vertex v) {
        return this.getAdjListEdges().containsKey(v);
    }

    public Graph kruskal(){
       Graph minSpannning = new Graph();
       for (Vertex vertex: this.adjListEdges.keySet()) {
            minSpannning.addVertex(vertex);
       }
       TreeSet<Edge> edges = getAllEdges();
       minSpannning.addEdge(edges.first());
       while (minSpannning.getAllEdges().size()<this.adjListEdges.size()-1){
           minSpannning.addEdge(cheapestEdgetoTake(minSpannning));
       }
       return minSpannning;
    }

    public Edge cheapestEdgetoTake(Graph minSpanning){
        TreeSet<Edge> edges = this.getAllEdges();
        for (Edge edge: edges) {
            if (!willFormCycle(edge,minSpanning)){
                return edge;
            }
        }
        return null;
    }


    public boolean willFormCycle(Edge edge, Graph graph){
        List<Vertex> visited = new ArrayList<>();
        Deque<Vertex> stack = new ArrayDeque<>();
        stack.add(edge.getVertex1());
        while (stack.size()>0){
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)){
                visited.add(vertex);
                for (Edge graphEdge: graph.getAdjListEdges().get(vertex)) {
                    if (vertex.equals(graphEdge.getVertex1())){
                        stack.push(graphEdge.getVertex2());
                    }
                    else {
                        stack.push(graphEdge.getVertex1());
                    }
                }
            }
            if (visited.contains(edge.getVertex2())){
                return true;
            }
        }
        return false;
    }



    public Graph prim(Vertex root) {
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);
        Edge minEdge = this.adjListEdges.get(root).first();
        TreeSet<Edge> allEdges = new TreeSet<>(this.adjListEdges.get(root));

        while (minSpanning.getAdjListEdges().size() < this.adjListEdges.size()) {
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
            allEdges.addAll(this.adjListEdges.get(added));
        }
        return minSpanning;
    }

    public Graph prim() {
        return prim(this.root);

    }

    public HashMap<Vertex, TreeSet<Edge>> getAdjListEdges() {
        return adjListEdges;
    }

    public TreeSet<Edge> getAllEdges() {
        TreeSet<Edge> edges = new TreeSet<>();
        for (Vertex v: this.adjListEdges.keySet()) {
            edges.addAll(this.adjListEdges.get(v));
        }
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder vertices = new StringBuilder("Graph:{\n\tVertices:");
        vertices.append(adjListEdges.keySet().toString());

        StringBuilder edgeStr = new StringBuilder("\tEdges:");
        TreeSet<Edge> edge = new TreeSet<>();
        for (Vertex vertex : adjListEdges.keySet()) {
            edge.addAll(adjListEdges.get(vertex));
        }
        edgeStr.append(edge.toString());
        return vertices.append("\n").append(edgeStr.toString()).append("\n}").toString();
    }
}
