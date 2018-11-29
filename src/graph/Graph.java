package graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private HashMap<Vertex,ArrayList<Pair<Vertex,Double>>> adjList;
    private Vertex root;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(Vertex v1, Vertex v2) {
        double weight = Vertex.distance(v1,v2);
        adjList.get(v1).add(new Pair<>(v2,weight));
        adjList.get(v2).add(new Pair<>(v1,weight));

    }

    public void addVertex(Vertex v) {
        if (adjList.isEmpty()) {
            root = v;
        }
        adjList.put(v,new ArrayList<>());
    }

    public Graph prim(Vertex root) {
        ArrayList<Vertex> visited = new ArrayList<>();
        Graph minSpanning = new Graph();
        minSpanning.addVertex(root);
        visited.add(root);

        while (minSpanning.adjList.size()<this.adjList.size()) {
            double minValue = Double.MAX_VALUE;
            Pair<Vertex,Double> minPair = new Pair<>(new Vertex("dummy",0,0),0.0);
            for (Vertex v : visited) {
                for (Pair<Vertex,Double> pair : adjList.get(v)) {
                    if (pair.getValue()<minValue) {
                        minValue = pair.getValue();
                        minPair = pair;
                    }
                }
                minSpanning.addVertex(minPair.getKey());
                minSpanning.addEdge(v,minPair.getKey());
            }

        }
        return minSpanning;
    }

    public Graph prim() {
        return prim(this.root);

    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjList=" + adjList +
                '}';
    }
}
