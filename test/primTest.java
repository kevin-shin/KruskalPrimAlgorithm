import graph.Graph;
import graph.Vertex;


public class primTest {

    public void testwillFormCycle(){
        Graph graph = new Graph();
        Vertex vertex = new Vertex("A",0,0);
        Vertex vertex2 = new Vertex("B",3,0);
        Vertex vertex3 = new Vertex("C",0,4);
        graph.addVertex(vertex,vertex2,vertex3);
        graph.addEdge(vertex,vertex2);
        graph.addEdge(vertex,vertex3);



    }


}
