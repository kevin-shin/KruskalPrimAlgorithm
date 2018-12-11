package graph;

import java.util.*;

public class Graph {

    private HashMap<Vertex, TreeSet<Edge>> adjList;
    private Vertex root;
    private TreeSet<Edge> Edges;
    private ArrayList<Edge> kruskalEdgeOrder; // Edges in order according to Kruskal's
    private ArrayList<Edge> primEdgeOrder; // Edges in order according to Prim's

    public Graph() {
        adjList = new HashMap<>();
        Edges = new TreeSet<>();
        kruskalEdgeOrder = new ArrayList<>();
        primEdgeOrder = new ArrayList<>();
    }

    /**
     * Finds a minimum spanning tree by Prim's, and records the primEdgeOrder for use in GraphVisualizer
     *
     * @return a Graph minimum spanning tree
     */
    private Graph prim(Vertex root) {
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

    /**
     * Finds a minimum spanning tree by Kruskal's, and records the kruskalEdgeOrder
     * for use in GraphVisualizer
     *
     * @return a Graph minimum spanning tree
     */
    public Graph kruskal() {
        Graph minSpanning = new Graph();
        for (Vertex vertex : this.adjList.keySet()) {
            minSpanning.addVertex(vertex);
        }
        minSpanning.addEdge(Edges.first());
        this.kruskalEdgeOrder.add(Edges.first());
        while (minSpanning.getAllEdges().size() < this.adjList.size() - 1) {
            Edge cheapest = cheapestEdgetoTake(minSpanning);
            minSpanning.addEdge(cheapest);
            this.kruskalEdgeOrder.add(cheapest);

        }
        return minSpanning;
    }

    private Edge cheapestEdgetoTake(Graph minSpanning) {
        for (Edge edge : Edges) {
            if (!willFormCycle(edge, minSpanning)) {
                return edge;
            }
        }
        return null;
    }

    private boolean willFormCycle(Edge edge, Graph graph) {
        List<Vertex> visited = new ArrayList<>();
        Deque<Vertex> stack = new ArrayDeque<>();
        stack.add(edge.getVertex1());
        while (stack.size() > 0) {
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Edge graphEdge : graph.getAdjList().get(vertex)) {
                    if (vertex.equals(graphEdge.getVertex1())) {
                        stack.push(graphEdge.getVertex2());
                    } else {
                        stack.push(graphEdge.getVertex1());
                    }
                }
            }
            if (visited.contains(edge.getVertex2())) {
                return true;
            }
        }
        return false;
    }

    //Helper Methods

    public void addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1, v2);
        Edge e = new Edge(weight, v1, v2);
        adjList.get(v1).add(e);
        adjList.get(v2).add(e);
        Edges.add(e);
    }

    public void addEdge(Edge e) {
       addEdge(e.getVertex1(), e.getVertex2());
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

    public HashMap<Vertex, TreeSet<Edge>> getAdjList() {
        return adjList;
    }

    public TreeSet<Edge> getAllEdges() {
        return Edges;
    }

    public ArrayList<Edge> getKruskalEdgeOrder() {
        return kruskalEdgeOrder;
    }

    public ArrayList<Edge> getPrimEdgeOrder() {
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
