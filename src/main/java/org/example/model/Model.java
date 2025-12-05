package org.example.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Model extends RenameObservers {
    private final List<MyShape> shapeList = new ArrayList<>();

    public void createCurrentShape(MyShape shape) {
        shapeList.add(shape);
        notifyObservers();
    }

    public void addCurrentShape(MyShape shape) {
        shapeList.add(shape);
        notifyObservers();
    }

    public MyShape getLastShape() {
        if (!shapeList.isEmpty()) {
            return shapeList.get(shapeList.size() - 1);
        }
        return null;
    }

    public void removeLastShape() {
        if (!shapeList.isEmpty()) {
            shapeList.remove(shapeList.size() - 1);
            notifyObservers();
        }
    }
    public List<MyShape> getShapeList() {
        return new ArrayList<>(shapeList);
    }

    public void update() {
        notifyObservers();
    }

    public void draw(Graphics2D g2) {
        for (MyShape shape : shapeList) {
            shape.draw(g2);
        }
    }
}