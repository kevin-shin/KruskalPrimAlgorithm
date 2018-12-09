package app;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphVisualizer extends JPanel {

    private Graph graph;
    private static BufferedImage map;
    public static final int VERTEX_WIDTH=10;
    public static final int EDGE_WIDTH=2;
    private double lastPressX;
    private double lastPressY;
    private double currentX;
    private double currentY;

    static {
        //load in the background image
        map = null;
        try {
            map = ImageIO.read(new File("res/MacalesterMap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GraphVisualizer(Graph g) {
        super();
        this.graph = g;
        this.addMouseListener(new ClickListener());
        this.addMouseMotionListener(new ClickListener());
    }

    public Graph getGraph() {
        return graph;
    }

    private Vertex vertexAt(double x, double y) {
        for (Vertex v: graph.getAdjList().keySet()) {
            if (v.getX()>=x-VERTEX_WIDTH/2 && v.getX()<=x+VERTEX_WIDTH/2 &&
                    v.getY()>=y-VERTEX_WIDTH/2 && v.getY()<=y+VERTEX_WIDTH) {
                return v;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(map,0,0,null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(EDGE_WIDTH));

        if (lastPressX>=0 && lastPressY>=0) {
            g2.draw(new Line2D.Double(lastPressX,lastPressY,currentX,currentY));
        }
        for (Edge e : graph.getAllEdges()) {
            g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                    e.getVertex2().getX(), e.getVertex2().getY()));
        }
        for (Vertex v : this.graph.getAdjList().keySet()) {
            Ellipse2D node = new Ellipse2D.Double(v.getX() - VERTEX_WIDTH/2, v.getY() - VERTEX_WIDTH/2,
                    VERTEX_WIDTH, VERTEX_WIDTH);
            g2.fill(node);
            g2.draw(node);

        }

    }

    private class ClickListener implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (vertexAt(e.getX(),e.getY()) == null) {
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
            Vertex start = vertexAt(lastPressX,lastPressY);
            Vertex end = vertexAt(e.getX(),e.getY());

            if (start != null && end !=null && !start.equals(end)) {
                graph.addEdge(start,end);
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
    }
}
