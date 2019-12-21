package agh.cs.project;

import javax.swing.*;


class LifeSimulation extends JFrame {
    JPanel pane = new JPanel();
    JTextArea t = new JTextArea("");

    LifeSimulation() {
        super("Evolution simulation");
    }

    void setT(JTextArea t) {
        this.t = t;
    }
    void display(){
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane.add(t);
        add(pane);
        pack();
        setVisible(true);
    }
}
