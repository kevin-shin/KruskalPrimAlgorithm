package primTest;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.Arrays;
import java.util.PriorityQueue;

public class primTest {
    public static void main(String[] args){
        Graph graph = new Graph();
        Vertex vertex = new Vertex("A",0,0);
        Vertex vertex2 = new Vertex("B",3,0);
        Vertex vertex3 = new Vertex("C",0,4);
        Vertex vertex4 = new Vertex("D",0,5);
        Vertex vertex5 = new Vertex("E",6,6);

        graph.addVertex(vertex,vertex2,vertex3,vertex4,vertex5);

        graph.addEdge(vertex,vertex2);
        graph.addEdge(vertex2,vertex3);
        graph.addEdge(vertex,vertex3);
        graph.addEdge(vertex3,vertex4);
        graph.addEdge(vertex5,vertex2);
        graph.addEdge(vertex4,vertex5);



//        Graph graph = new Graph();
//        Vertex vertex = new Vertex("A",0,0);
//        Vertex vertex2 = new Vertex("B",3,0);
//        Vertex vertex3 = new Vertex("C",0,4);
//        Vertex vertex4 = new Vertex("D",5,1);
//
//        graph.addVertex(vertex,vertex2,vertex3);
//        graph.addEdge(vertex,vertex2);
//        graph.addEdge(vertex,vertex3);
//        Edge edge = new Edge(6.0,vertex2,vertex4);
//
//        System.out.println(graph.willFormCycle(edge,graph));
    }




}
