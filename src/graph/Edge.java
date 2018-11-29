package graph;

public class Edge {
    private Vertex vertex1;
    private Vertex vertex2;
    private double weight;


    public Edge(double weight, Vertex vertex1, Vertex vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Edge) {
            Edge edge = (Edge) obj;
            return this.vertex1.equals(edge.vertex1) && this.vertex2.equals(edge.vertex2);
        }
        return false;
    }

    public String toString(){
        return this.vertex1.getLabel() + "->" + this.vertex2.getLabel() + "(" + weight + ")";
    }

}
