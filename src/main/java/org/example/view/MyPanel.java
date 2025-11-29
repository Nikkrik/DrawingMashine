package org.example.view;

import org.example.controller.Controller;
import org.example.model.ModelObserver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements ModelObserver {
    private final Controller controller;
    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.startDrawing(arg0.getPoint());
            }
            public void mouseReleased(MouseEvent arg0) {
                // Завершаем действие при отпускании мыши
                controller.finishDrawing(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                controller.updateDrawing(arg0.getPoint());
                repaint();

            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.draw(g2);
    }
    @Override
    public void onModelChanged(){
        repaint(); // Вызывается при изменении модели
    }

}
