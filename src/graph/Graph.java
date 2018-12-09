package graph;

import java.util.*;

public class Graph {

    private HashMap<Vertex, TreeSet<Edge>> adjList;
    private Vertex root;
    private ArrayList<Edge> kruskalEdgeOrder;
    private ArrayList<Edge> primEdgeOrder;

    public Graph() {
        adjList = new HashMap<>();
        kruskalEdgeOrder = new ArrayList<>();
        primEdgeOrder = new ArrayList<>();
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

    public Graph kruskal(){
       Graph minSpannning = new Graph();
       for (Vertex vertex: this.adjList.keySet()) {
            minSpannning.addVertex(vertex);
       }
       TreeSet<Edge> edges = getAllEdges();
       minSpannning.addEdge(edges.first());
       this.kruskalEdgeOrder.add(edges.first());
       while (minSpannning.getAllEdges().size()<this.adjList.size()-1){
           Edge cheapest = cheapestEdgetoTake(minSpannning);
           minSpannning.addEdge(cheapest);
           this.kruskalEdgeOrder.add(cheapest);

       }
       System.out.println("Kruskal algorithm edge size: " + this.kruskalEdgeOrder.size());
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
                for (Edge graphEdge: graph.getAdjList().get(vertex)) {
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
            primEdgeOrder.add(minEdge);
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

    public TreeSet<Edge> getAllEdges() {
        TreeSet<Edge> edges = new TreeSet<>();
        for (Vertex v: this.adjList.keySet()) {
            edges.addAll(this.adjList.get(v));
        }
        return edges;
    }

    public ArrayList<Edge> getKruskalEdgeOrder(){
        return kruskalEdgeOrder;
    }

    public ArrayList<Edge> getPrimEdgeOrder(){
        return primEdgeOrder;
    }

    @Override
    public String toString() {
        StringBuilder vertices = new StringBuilder("Graph:{\n\tVertices:");
        vertices.append(adjList.keySet().toString());

        StringBuilder edgeStr = new StringBuilder("\tEdges:");
        TreeSet<Edge> edge = new TreeSet<>();
        for (Vertex vertex : adjList.keySet()) {
            edge.addAll(adjList.get(vertex));
        }
        edgeStr.append(edge.toString());
        return vertices.append("\n").append(edgeStr.toString()).append("\n}").toString();
    }


}
