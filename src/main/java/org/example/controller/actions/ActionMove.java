package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D lastPoint;
    private Model model;
    private Point2D originalPosition;

    public ActionMove(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point2D point) {
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);

        firstPoint = point;
        if (shape != null) {
            originalPosition = new Point2D.Double(
                    shape.getShape().getCenterX(),
                    shape.getShape().getCenterY()
            );
        }
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (shape == null) {
            return;
        }

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        // Исправленный метод установки рамки
        double newX = shape.getShape().getX() + deltaX;
        double newY = shape.getShape().getY() + deltaY;
        double width = shape.getShape().getWidth();
        double height = shape.getShape().getHeight();

        shape.getShape().setFrame(newX, newY, width, height);

        lastPoint = point;
        firstPoint = point;
        model.update();
    }

    @Override
    public void execute() {
        if (shape != null && lastPoint != null) {
            double deltaX = lastPoint.getX() - firstPoint.getX();
            double deltaY = lastPoint.getY() - firstPoint.getY();

            double newX = shape.getShape().getX() + deltaX;
            double newY = shape.getShape().getY() + deltaY;
            double width = shape.getShape().getWidth();
            double height = shape.getShape().getHeight();

            shape.getShape().setFrame(newX, newY, width, height);
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (shape != null && originalPosition != null) {
            // Возвращаем фигуру в исходное положение
            double width = shape.getShape().getWidth();
            double height = shape.getShape().getHeight();

            double newX = originalPosition.getX() - width / 2;
            double newY = originalPosition.getY() - height / 2;

            shape.getShape().setFrame(newX, newY, width, height);
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove clone = new ActionMove(model);
        clone.shape = this.shape;
        clone.firstPoint = this.firstPoint != null ? (Point2D) this.firstPoint.clone() : null;
        clone.lastPoint = this.lastPoint != null ? (Point2D) this.lastPoint.clone() : null;
        clone.originalPosition = this.originalPosition != null ? (Point2D) this.originalPosition.clone() : null;
        return clone;
    }
}