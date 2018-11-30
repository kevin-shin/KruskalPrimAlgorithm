package primTest;
import graph.Graph;
import graph.Vertex;

public class primTest {
    public static void main(String[] args){
        Graph graph = new Graph();
        Vertex vertex = new Vertex("A",0,0);
        Vertex vertex2 = new Vertex("B",3,0);
        Vertex vertex3 = new Vertex("C",0,4);
        Vertex vertex4 = new Vertex("D",0,5);

        graph.addVertex(vertex,vertex2,vertex3,vertex4);

        graph.addEdge(vertex,vertex2);
        graph.addEdge(vertex2,vertex3);
        graph.addEdge(vertex,vertex3);
        graph.addEdge(vertex3,vertex4);


        System.out.println(graph.prim().toString());
    }




}
