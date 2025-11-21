package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Model extends RenameObservers {
    private MyShape currentShape;
    private List<MyShape> shapes = new ArrayList<>();

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        shapes.add(shape);
        notifyObservers();
    }

    public void changeShape(Point2D x, Point2D y) {
        if (currentShape != null) {
            currentShape.setFrame(x, y);
            notifyObservers();
        }
    }

    public List<MyShape> getShapeList() {
        return shapes;
    }

    public void update() {
        notifyObservers();
    }

    public void draw(Graphics2D g) {
        for (MyShape shape : shapes) {
            shape.draw(g);
        }
    }
}