package app;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GraphVisualizer extends JPanel {

    private static final int VERTEX_WIDTH = 10;
    private static final int EDGE_WIDTH = 2;
    private static BufferedImage map;

    static {
        //load in the background image
        map = null;
        try {
            map = ImageIO.read(GraphVisualizer.class.getResource("/MacalesterMapResized.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Graph graph;
    private double lastPressX;
    private double lastPressY;
    private double currentX;
    private double currentY;
    private ArrayList<Edge> kruskalToDraw;
    private ArrayList<Edge> kruskalEdgeOrder;
    private ArrayList<Edge> primToDraw;
    private ArrayList<Edge> primEdgeOrder;
    private Timer kruskalTimer;
    private Timer primTimer;
    private boolean edgesHidden;


    public GraphVisualizer(Graph g) {
        super();
        this.graph = g;
        this.addMouseListener(new ClickListener());
        this.addMouseMotionListener(new ClickListener());
        kruskalToDraw = new ArrayList<>();
        primToDraw = new ArrayList<>();
        kruskalTimer = new Timer(1000, new ClickListener());
        primTimer = new Timer(1000, new ClickListener());
    }

    // Draw everything
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(map, 0, 0, null);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(EDGE_WIDTH));
        g2.setColor(Color.BLACK);

        if (lastPressX >= 0 && lastPressY >= 0) {
            g2.draw(new Line2D.Double(lastPressX, lastPressY, currentX, currentY));
        }

        if (!edgesHidden) {
            for (Edge e : graph.getAllEdges()) {
                g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                        e.getVertex2().getX(), e.getVertex2().getY()));
            }
        }

        for (Vertex v : this.graph.getAdjList().keySet()) {
            Ellipse2D node = new Ellipse2D.Double(v.getX() - VERTEX_WIDTH / 2, v.getY() - VERTEX_WIDTH / 2,
                    VERTEX_WIDTH, VERTEX_WIDTH);
            g2.fill(node);
            g2.draw(node);
        }

        if (!kruskalToDraw.isEmpty()) {
            for (Edge e : this.kruskalToDraw) {
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(EDGE_WIDTH + 2));
                Line2D edge = new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                        e.getVertex2().getX(), e.getVertex2().getY());
                g2.draw(edge);
                g2.setColor(Color.MAGENTA);
                g2.setStroke(new BasicStroke(EDGE_WIDTH));
                g2.draw(edge);
            }
        } else if (!primToDraw.isEmpty()) {
            for (Edge e : this.primToDraw) {
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(EDGE_WIDTH + 2));
                Line2D edge = new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                        e.getVertex2().getX(), e.getVertex2().getY());
                g2.draw(edge);
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(EDGE_WIDTH));
                g2.draw(edge);

            }
        }
    }

    public void startKruskalAnimation() {
        kruskalToDraw = new ArrayList<>();
        primToDraw = new ArrayList<>();
        kruskalTimer.start();
    }

    public void startPrimAnimation() {
        kruskalToDraw = new ArrayList<>();
        primToDraw = new ArrayList<>();
        primTimer.start();
    }

    /**
     * Moves the next edge in the kruskalEdgeOrder into the list of things to draw.
     * Called every second by timer.
     */
    public void stepKruskal() {
        this.kruskalToDraw.add(kruskalEdgeOrder.remove(0));
        repaint();
        if (kruskalEdgeOrder.size() == 0) {
            kruskalTimer.stop();
            edgesHidden = true;

        }
    }

    /**
     * Moves the next edge in the primEdgeOrder into the list of things to draw.
     * Called every second by timer.
     */
    public void stepPrim() {
        this.primToDraw.add(primEdgeOrder.remove(0));
        repaint();
        if (primEdgeOrder.size() == 0) {
            primTimer.stop();
            edgesHidden = true;
        }
    }

    // Getters, setters, helper methods

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        kruskalToDraw = new ArrayList<>();
        primToDraw = new ArrayList<>();
        repaint();
    }

    public void setKruskalEdgeOrder(ArrayList<Edge> kruskalEdgeOrder) {
        this.kruskalEdgeOrder = kruskalEdgeOrder;
    }

    public void setPrimEdgeOrder(ArrayList<Edge> primEdgeOrder) {
        this.primEdgeOrder = primEdgeOrder;
    }

    public boolean isEdgesHidden() {
        return edgesHidden;
    }

    public void setEdgesHidden(boolean hideEdges) {
        this.edgesHidden = hideEdges;
        repaint();
    }

    private Vertex vertexAt(double x, double y) {
        for (Vertex v : graph.getAdjList().keySet()) {
            if (v.getX() >= x - VERTEX_WIDTH / 2 && v.getX() <= x + VERTEX_WIDTH / 2 &&
                    v.getY() >= y - VERTEX_WIDTH / 2 && v.getY() <= y + VERTEX_WIDTH) {
                return v;
            }
        }
        return null;
    }

    /**
     * generates a complete graph on the vertices currently on the screen.
     */
    public void completeGraph() {
        edgesHidden = false;
        Graph completegraph = new Graph();
        for (Vertex vertex : this.graph.getAdjList().keySet()) {
            completegraph.addVertex(vertex);
        }
        for (Vertex vertex : completegraph.getAdjList().keySet()) {
            for (Vertex otherVertex : completegraph.getAdjList().keySet()) {
                if (vertex != otherVertex) {
                    completegraph.addEdge(new Edge(Vertex.distance(vertex, otherVertex), vertex, otherVertex));
                }
            }
        }
        this.graph = completegraph;
        repaint();
    }

    /**
     * A class for listening to mouse clicks, movements and Timer actions
     */
    private class ClickListener implements MouseListener, MouseMotionListener, ActionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (vertexAt(e.getX(), e.getY()) == null) {
                graph.addVertex(new Vertex(Long.toHexString(System.currentTimeMillis()), e.getX(), e.getY()));
                repaint();

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            lastPressX = e.getX();
            lastPressY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Vertex start = vertexAt(lastPressX, lastPressY);
            Vertex end = vertexAt(e.getX(), e.getY());

            if (start != null && end != null && !start.equals(end)) {
                graph.addEdge(start, end);
            }
            lastPressX = -10;
            lastPressY = -10;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            currentX = e.getX();
            currentY = e.getY();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == kruskalTimer) {
                stepKruskal();
            }

            if (e.getSource() == primTimer) {
                stepPrim();
            }
        }
    }
}
