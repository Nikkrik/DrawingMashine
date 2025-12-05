package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ActionMove implements AppAction {
    private MyShape shape;
    private Point2D startPoint;
    private Point2D lastPoint;
    private final Model model;
    private Rectangle2D originalBounds;
    private double totalDeltaX;
    private double totalDeltaY;

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

        startPoint = point;
        if (shape != null) {
            originalBounds = shape.getShape().getBounds2D();
            totalDeltaX = 0;
            totalDeltaY = 0;
        }
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (shape == null) {
            return;
        }

        double deltaX = point.getX() - startPoint.getX();
        double deltaY = point.getY() - startPoint.getY();

        // Перемещаем фигуру
        double newX = shape.getShape().getX() + deltaX;
        double newY = shape.getShape().getY() + deltaY;
        double width = shape.getShape().getWidth();
        double height = shape.getShape().getHeight();

        shape.getShape().setFrame(newX, newY, width, height);

        totalDeltaX += deltaX;
        totalDeltaY += deltaY;

        lastPoint = point;
        startPoint = point;
        model.update();
    }

    @Override
    public void execute() {
        if (shape != null && originalBounds != null) {
            double newX = originalBounds.getX() + totalDeltaX;
            double newY = originalBounds.getY() + totalDeltaY;

            shape.getShape().setFrame(
                    newX,
                    newY,
                    originalBounds.getWidth(),
                    originalBounds.getHeight()
            );
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (shape != null && originalBounds != null) {
            shape.getShape().setFrame(
                    originalBounds.getX(),
                    originalBounds.getY(),
                    originalBounds.getWidth(),
                    originalBounds.getHeight()
            );
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove clone = new ActionMove(model);
        clone.shape = this.shape;
        clone.startPoint = this.startPoint != null ? new Point2D.Double(this.startPoint.getX(), this.startPoint.getY()) : null;
        clone.lastPoint = this.lastPoint != null ? new Point2D.Double(this.lastPoint.getX(), this.lastPoint.getY()) : null;
        clone.originalBounds = this.originalBounds != null ?
                new Rectangle2D.Double(
                        this.originalBounds.getX(),
                        this.originalBounds.getY(),
                        this.originalBounds.getWidth(),
                        this.originalBounds.getHeight()
                ) : null;
        clone.totalDeltaX = this.totalDeltaX;
        clone.totalDeltaY = this.totalDeltaY;
        return clone;
    }
}