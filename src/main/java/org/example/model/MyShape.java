package org.example.model;

import org.example.model.shape.fill.FillBehavior;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable{
    private final Color color;
    private RectangularShape shape;
    private final FillBehavior fb;

    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.shape = shape;
        this.color = color;
        this.fb = fb;
        this.fb.setColor(color);
        this.fb.setShape(shape);
    }

    public MyShape clone() {
        try {
            RectangularShape shapeCopy = (RectangularShape) this.shape.clone();
            FillBehavior fbCopy = this.fb.copy();
            fbCopy.setColor(this.color);
            fbCopy.setShape(shapeCopy);

            return new MyShape(this.color, shapeCopy, fbCopy);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public RectangularShape getShape() {
        return shape;
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }
}
