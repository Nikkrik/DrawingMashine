package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeCreator;

import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private MyShape sampleShape;
    private MyShape currentShape;
    private Point2D startPoint;
    private Model model;
    private MyShape drawableShape;
    private ShapeCreator factory;

    public ActionDraw(Model model, MyShape sampleShape) {
        this.model = model;
        this.sampleShape = sampleShape;
    }

    public ActionDraw(Model model) {
        this.model = model;
    }

    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void mousePressed(Point2D point) {
        startPoint = point;
        // Создаем копию образца фигуры с текущими настройками
        currentShape = sampleShape.clone();
        drawableShape = currentShape;
        model.createCurrentShape(currentShape);
        currentShape.setFrame(startPoint, startPoint);
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (currentShape != null) {
            currentShape.setFrame(startPoint, point);
            if (drawableShape != null) {
                drawableShape.setFrame(startPoint, point);
            }
        }
    }

    @Override
    public void execute() {
        model.addCurrentShape(drawableShape);
        model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model);
        actionDraw.sampleShape = this.sampleShape.clone();
        actionDraw.drawableShape = this.drawableShape;
        return actionDraw;
    }
}