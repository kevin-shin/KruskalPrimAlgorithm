package graph;

import java.util.HashMap;

public class Vertex {

    private String label;
    private double x;
    private double y;
    private HashMap<Vertex,Double> neighbors;

    public Vertex(String label, double x, double y) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.neighbors = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public HashMap<Vertex, Double> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "{" + label + ", x=" + x + ", y=" + y + '}';
    }

    public static double distance(Vertex v1, Vertex v2) {
        return Math.sqrt((Math.pow(v1.getY()-v2.getY(),2))+(Math.pow(v1.getX()-v2.getX(),2)));
    }
}
