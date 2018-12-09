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
import java.io.File;
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
            map = ImageIO.read(new File("res/MacalesterMap.png"));
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

    public Graph getGraph() {
        return graph;
    }

    public void setKruskalEdgeOrder(ArrayList<Edge> kruskalEdgeOrder) {
        this.kruskalEdgeOrder = kruskalEdgeOrder;
    }

    public void setPrimEdgeOrder(ArrayList<Edge> primEdgeOrder) {
        this.primEdgeOrder = primEdgeOrder;
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(map, 0, 0, null);

        g2.setStroke(new BasicStroke(EDGE_WIDTH));

        g2.setColor(Color.BLACK);
        if (lastPressX >= 0 && lastPressY >= 0) {
            g2.draw(new Line2D.Double(lastPressX, lastPressY, currentX, currentY));
        }
        for (Edge e : graph.getAllEdges()) {
            g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                    e.getVertex2().getX(), e.getVertex2().getY()));
        }
        for (Vertex v : this.graph.getAdjList().keySet()) {
            Ellipse2D node = new Ellipse2D.Double(v.getX() - VERTEX_WIDTH / 2, v.getY() - VERTEX_WIDTH / 2,
                    VERTEX_WIDTH, VERTEX_WIDTH);
            g2.fill(node);
            g2.draw(node);
        }

        g2.setColor(Color.PINK);
        if (kruskalToDraw != null) {
            for (Edge e : this.kruskalToDraw) {
                g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                        e.getVertex2().getX(), e.getVertex2().getY()));
            }
        }

        g2.setColor(Color.CYAN);
        if (primToDraw != null) {
            for (Edge e : this.primToDraw) {
                g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                        e.getVertex2().getX(), e.getVertex2().getY()));
            }
        }

    }

    public void startKruskalAnimation() {
        kruskalToDraw = new ArrayList<>();
        primToDraw = new ArrayList<>();
        kruskalTimer.start();
    }

    public void startPrimAnimation() {
        primToDraw = new ArrayList<>();
        kruskalToDraw = new ArrayList<>();
        primTimer.start();
    }


    public void stepKruskal() {
        this.kruskalToDraw.add(kruskalEdgeOrder.remove(0));
        repaint();
        if (kruskalEdgeOrder.size() == 0) {
            kruskalTimer.stop();
        }
    }
    public void stepPrim() {
        this.primToDraw.add(primEdgeOrder.remove(0));
        repaint();
        if (primEdgeOrder.size() == 0) {
            primTimer.stop();
        }
    }


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
            if (e.getSource().equals(kruskalTimer)) {
                stepKruskal();
            }

            if (e.getSource().equals(primTimer)) {
                stepPrim();
            }
        }
    }
}
