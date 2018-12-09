package app;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GraphVisualizer extends JPanel {

    private Graph graph;

    public GraphVisualizer(Graph g) {
        super();
        this.graph = g;
        this.addMouseListener(new ClickListener());
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        for (Edge e : graph.getAllEdges()) {
            g2.setStroke(new BasicStroke(2));
            g2.draw(new Line2D.Double(e.getVertex1().getX(), e.getVertex1().getY(),
                    e.getVertex2().getX(), e.getVertex2().getY()));
        }
        for (Vertex v : this.graph.getAdjList().keySet()) {
            Ellipse2D node = new Ellipse2D.Double(v.getX() - 5, v.getY() - 5, 10, 10);
            g2.fill(node);
            g2.draw(node);

        }

    }

    private class ClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(graph);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
