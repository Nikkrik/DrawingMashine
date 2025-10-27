package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Controller {
    private static Controller inctance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private ActionDraw actionDraw;

    public static Controller getInctance(){
        synchronized (Controller.class){
            if (inctance == null){
                inctance = new Controller();
            }
            return inctance;
        }
    }
    private Controller() {
        model = new Model();
        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        sampleShape.setFb(new NoFill());

        actionDraw = new ActionDraw(model, sampleShape);

        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void startDrawing(Point2D p) {
        actionDraw.startDrawing(p);
    }

    public void updateDrawing(Point2D p) {
        actionDraw.updateDrawing(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
