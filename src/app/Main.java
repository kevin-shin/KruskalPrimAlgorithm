package app;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Main {

    public static void main(String args[]) {
        //Testing
        Graph g = new Graph();
        Vertex v1 = new Vertex("A",0,0);
        Vertex v2 = new Vertex("B",4,4);
        Vertex v3 = new Vertex("C",5,5);
        Edge e2 = new Edge(v2,v3,16);
        Edge e1 = new Edge(v1,v2);
        g.addVertex(v1,v2,v3);
        g.addEdge(e1,e2);

        System.out.println(g);
    }
}
