package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape;
    private MyShape currentShape;
    private Point2D startPoint;
    private Model model;

    public ActionDraw(Model model, MyShape sampleShape) {
        this.model = model;
        this.sampleShape = sampleShape;
    }

    public void startDrawing(Point2D point) {
        startPoint = point;
        // Создаем копию образца фигуры
        currentShape = sampleShape.clone();
        model.createCurrentShape(currentShape);
        currentShape.setFrame(startPoint, startPoint);
    }

    public void updateDrawing(Point2D point) {
        if (currentShape != null) {
            currentShape.setFrame(startPoint, point);
        }
    }
}
