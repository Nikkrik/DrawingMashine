package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    private List<MyShape> shapes = new ArrayList<>();

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        shapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }


    public void changeShape(Point2D x, Point2D y) {
        if(currentShape!=null)
            currentShape.setFrame(x, y);
    }


    public void draw(Graphics2D g) {
        for (MyShape shape : shapes) {
            shape.draw(g);
        }
    }
}
