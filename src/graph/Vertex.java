package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Vertex {

    private String label;
    private double x;
    private double y;
    private ArrayList<Edge> neighbors;

    public Vertex(String label, double x, double y) {
        this.neighbors = new ArrayList<>();
        this.label = label;
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "{" + label + ", x=" + x + ", y=" + y + '}';
    }

    public static double distance(Vertex v1, Vertex v2) {
        return Math.sqrt((Math.pow(v1.getY()-v2.getY(),2))+(Math.pow(v1.getX()-v2.getX(),2)));
    }

    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Vertex) {
            Vertex vobj = (Vertex) obj;
            return label.equals(vobj.label) && (this.x == vobj.x) && (this.y == vobj.y);
        }
        return false;
    }


}
