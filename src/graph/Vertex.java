package graph;

public class Vertex {

    private String label;
    private double x;
    private double y;

    public Vertex(String label, double x, double y) {
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
}
