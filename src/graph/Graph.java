package graph;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class Graph {

    private HashMap<Vertex,ArrayList<Pair<Vertex,Double>>> adjList;
    private ArrayList<Vertex> V;
    private ArrayList<Edge> E;
    private Vertex root;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1,v2);
        Edge edge = new Edge(weight,v1,v2);
        adjList.get(v1).add(new Pair<>(v2,weight));
        adjList.get(v2).add(new Pair<>(v1,weight));
        E.add(edge);
    }

    public void addEdge(Edge edge){
        E.add(edge);
    }

    public void addVertex(Vertex v) {
        if (adjList.isEmpty()) {
            root = v;
        }
        adjList.put(v,new ArrayList<>());
        V.add(v);
    }

    public boolean hasVertex(Vertex sampleVertex) {
        for (Vertex vertex: adjList.keySet()
             ) {
            if (sampleVertex.equals(vertex)){
                return true;
            }
        }
        return false;
    }

    public Graph prim(Vertex root) {
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);

        HashMap<Vertex,Double> fringe = new HashMap<>();

        for (Vertex vertex: V
             ) {
            fringe.put(vertex, Double.MAX_VALUE);
        }
        return minSpanning;
    }
    
    public Graph prim() {
        return prim(this.root);

    }

    @Override
    public String toString() {
        String string = "Vertices: " + V.toString() + "\n" + "Edges: " + E.toString();
    }
}
