package app;

import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class Window extends JFrame {

    private GraphVisualizer graphPanel;
    private JPanel panel;
    private JButton kruskalButton;


    public Window(Graph g) {
        super("Test");

        kruskalButton = new JButton("Kruskal");
        this.graphPanel = new GraphVisualizer(g);
        this.panel = new JPanel();

        this.panel.add(graphPanel);
        this.panel.add(kruskalButton);

        this.setPreferredSize(new Dimension(620, 350));
        graphPanel.setPreferredSize(new Dimension(620, 317));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);



        kruskalButton.addActionListener(new kruskalButtonListener());
        this.pack();
        this.setVisible(true);


    }

    private class kruskalTimer extends TimerTask {
        @Override
        public void run() {
            //paintKruskal();
        }
    }

    private class kruskalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == kruskalButton){
                graphPanel.paintKruskal();
            }
        }
    }

}
