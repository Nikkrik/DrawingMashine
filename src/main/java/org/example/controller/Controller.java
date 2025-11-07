package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.model.shape.ShapeType;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;



public class Controller {
    private static Controller inctance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
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

        MyShape sampleShape = MyShapeFactory.createShape(ShapeType.RECTANGLE);

        actionDraw = new ActionDraw(model, sampleShape);

        panel = new MyPanel(this);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void setShapeType(ShapeType type){
        MyShape sampleshape = MyShapeFactory.createShape(type);
        actionDraw = new ActionDraw(model, sampleshape);
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
