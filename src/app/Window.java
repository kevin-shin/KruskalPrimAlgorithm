package app;


import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {

    private GraphVisualizer graphPanel;
    private JPanel panel;
    private JButton kruskalButton;
    private JButton completeButton;
    private JButton primButton;
    private JButton clearButton;
    private JButton toggleEdges;

    public Window(Graph g) {
        super("Prim's and Kruskal's Visualizer");
        //set up UI
        kruskalButton = new JButton("Run Kruskal's");
        completeButton = new JButton("Complete Graph");
        primButton = new JButton("Run Prim's");
        clearButton = new JButton("Clear Graph");
        toggleEdges = new JButton("Toggle Edge Visibility");

        this.graphPanel = new GraphVisualizer(g);
        this.panel = new JPanel();

        this.panel.add(graphPanel);
        this.panel.add(kruskalButton);
        this.panel.add(primButton);
        this.panel.add(completeButton);
        this.panel.add(clearButton);
        this.panel.add(toggleEdges);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        graphPanel.setPreferredSize(new Dimension(1200, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);

        kruskalButton.addActionListener(new ButtonListener());
        completeButton.addActionListener(new ButtonListener());
        primButton.addActionListener(new ButtonListener());
        clearButton.addActionListener(new ButtonListener());
        toggleEdges.addActionListener(new ButtonListener());

        this.pack();
        this.setVisible(true);
    }

    /**
     * A class for listening to button presses
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == kruskalButton) {
                // Generate the graph, get the edges in order, start animation
                graphPanel.getGraph().kruskal();
                graphPanel.setKruskalEdgeOrder(graphPanel.getGraph().getKruskalEdgeOrder());
                graphPanel.startKruskalAnimation();
            } else if (e.getSource() == primButton) {
                graphPanel.getGraph().prim();
                graphPanel.setPrimEdgeOrder(graphPanel.getGraph().getPrimEdgeOrder());
                graphPanel.startPrimAnimation();
            } else if (e.getSource() == clearButton) {
                graphPanel.setGraph(new Graph());
            } else if (e.getSource() == toggleEdges) {
                graphPanel.setEdgesHidden(!graphPanel.isEdgesHidden());
            } else if (e.getSource() == completeButton) {
                graphPanel.completeGraph();
            }
        }
    }
}
