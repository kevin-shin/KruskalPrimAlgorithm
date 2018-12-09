package app;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TimerTask;

public class Window extends JFrame {

    private GraphVisualizer graphPanel;
    private JPanel panel;
    private JButton kruskalButton;
    private JButton completeButton;
    private JButton primButton;


    public Window(Graph g) {
        super("Test");

        kruskalButton = new JButton("Kruskal");
        completeButton = new JButton("Complete Graph");
        primButton = new JButton("Prim");


        this.graphPanel = new GraphVisualizer(g);
        this.panel = new JPanel();

        this.panel.add(graphPanel);
        this.panel.add(kruskalButton);
        this.panel.add(completeButton);
        this.panel.add(primButton);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        graphPanel.setPreferredSize(new Dimension(1200, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);

        kruskalButton.addActionListener(new ButtonListener());
        completeButton.addActionListener(new completeGraphListener());

        kruskalButton.addActionListener(new ButtonListener());
        primButton.addActionListener(new ButtonListener());
        this.pack();
        this.setVisible(true);


    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == kruskalButton){
                graphPanel.getGraph().kruskal();
                graphPanel.setKruskalEdgeOrder(graphPanel.getGraph().getKruskalEdgeOrder());
                graphPanel.startKruskalAnimation();
            }

            if (e.getSource() == primButton) {
                graphPanel.getGraph().prim();
                graphPanel.setPrimEdgeOrder(graphPanel.getGraph().getPrimEdgeOrder());
                graphPanel.startPrimAnimation();
            }
        }
    }

    private class completeGraphListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == completeButton) {
                graphPanel.completeGraph();
            }
            }
        }
    }
