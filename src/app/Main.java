package app;


import graph.Graph;
import graph.Vertex;

public class Main {

    public static void main(String args[]) {
        //Testing
        Graph g = new Graph();
        Vertex v1 = new Vertex("A", 30, 30);
        Vertex v2 = new Vertex("B", 50, 85);
        Vertex v3 = new Vertex("C",100,100);
        g.addVertex(v1, v2, v3);
        g.addEdge(v1, v2);
        g.addEdge(v1,v3);

        Window window = new Window(g);

    }
}
