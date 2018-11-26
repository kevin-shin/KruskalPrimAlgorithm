package graph;

public class Edge {

    private Vertex start;
    private Vertex end;
    private double weight;

    public Edge(Vertex start, Vertex end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.weight = Math.sqrt(Math.pow(end.getX()-start.getX(),2)+Math.pow(end.getY()-start.getY(),2));
    }

    @Override
    public String toString() {
        return "("+this.start.getLabel()+","+this.end.getLabel()+","+this.weight+")";
    }
}
