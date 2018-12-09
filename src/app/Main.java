package app;


import graph.Graph;
import graph.Vertex;

public class Main {

    public static void main(String args[]) {
        //Testing
        Graph graph = new Graph();
        Vertex vertex = new Vertex("A",10,10);
        Vertex vertex2 = new Vertex("B",60,10);
        Vertex vertex3 = new Vertex("C",10,80);
        Vertex vertex4 = new Vertex("D",10,100);
        Vertex vertex5 = new Vertex("E",120,120);

        graph.addVertex(vertex,vertex2,vertex3,vertex4,vertex5);

        graph.addEdge(vertex,vertex2);
        graph.addEdge(vertex2,vertex3);
        graph.addEdge(vertex,vertex3);
        graph.addEdge(vertex3,vertex4);
        graph.addEdge(vertex5,vertex2);
        graph.addEdge(vertex4,vertex5);

        Window window = new Window(new Graph());

    }
}
