package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private MyShape shape;
    private Point2D firstPoint;
    private Model model;

    public ActionMove(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point2D point) {
        // Ищем фигуру, на которую было совершено нажатие
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);

        firstPoint = point;
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (shape == null) {
            return;
        }

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double(
                shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY
        );

        Point2D newShapeSecondPoint = new Point2D.Double(
                shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY
        );

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }
}