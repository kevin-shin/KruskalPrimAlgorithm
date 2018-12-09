package app;

import graph.Graph;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private GraphVisualizer panel;


    public Window(Graph g) {
        super("Test");
        this.panel = new GraphVisualizer(g);
        this.setPreferredSize(new Dimension(620, 317));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
    }

}
