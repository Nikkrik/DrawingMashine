package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private MyShape sampleShape;
    private MyShape currentShape;
    private Point2D startPoint;
    private Model model;

    public ActionDraw(Model model, MyShape sampleShape) {
        this.model = model;
        this.sampleShape = sampleShape;
    }

    // Метод для обновления образца фигуры
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void mousePressed(Point2D point) {
        startPoint = point;
        // Создаем копию образца фигуры с текущими настройками
        currentShape = sampleShape.clone();
        model.createCurrentShape(currentShape);
        currentShape.setFrame(startPoint, startPoint);
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (currentShape != null) {
            currentShape.setFrame(startPoint, point);
        }
    }
}