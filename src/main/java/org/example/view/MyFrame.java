package org.example.view;

import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }
    public void setPanel(MyPanel panel) {
        add(panel);
    }
}