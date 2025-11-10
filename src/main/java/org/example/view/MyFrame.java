package org.example.view;

import org.example.controller.MenuController;

import javax.swing.*;

public class MyFrame extends JFrame {
    private MyPanel panel;

    public MyFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
        add(panel);
    }

    public void setMenu() {
        MenuController menuController = MenuController.getInstance();
        setJMenuBar(menuController.createMenuBar());
    }
}